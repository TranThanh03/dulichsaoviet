package com.websitesaoviet.WebsiteSaoViet.service;

import com.websitesaoviet.WebsiteSaoViet.dto.request.PasswordChangeRequest;
import com.websitesaoviet.WebsiteSaoViet.dto.request.UserCreationRequest;
import com.websitesaoviet.WebsiteSaoViet.dto.request.UserUpdateRequest;
import com.websitesaoviet.WebsiteSaoViet.dto.response.admin.LatestUsersResponse;
import com.websitesaoviet.WebsiteSaoViet.dto.response.UserResponse;
import com.websitesaoviet.WebsiteSaoViet.entity.User;
import com.websitesaoviet.WebsiteSaoViet.enums.Role;
import com.websitesaoviet.WebsiteSaoViet.exception.AppException;
import com.websitesaoviet.WebsiteSaoViet.exception.ErrorCode;
import com.websitesaoviet.WebsiteSaoViet.mapper.UserMapper;
import com.websitesaoviet.WebsiteSaoViet.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;

    public UserResponse createUser(UserCreationRequest request) {
        if(userRepository.existsUserByPhone(request.getPhone())) {
            throw new AppException(ErrorCode.PHONENUMBER_EXISTED);
        }
        else if (userRepository.existsUserByEmail(request.getEmail())) {
            throw new AppException(ErrorCode.EMAIL_EXISTED);
        }

        User user = userMapper.createUser(request);

        user.setUserId(String.valueOf(generateNextId()));
        user.setRegisterTime(LocalDateTime.now());

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());

        user.setRoles(roles);

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public Page<UserResponse> getUsers(Pageable pageable) {
        return userRepository.findUsersWithRoleUser(pageable).map(userMapper::toUserResponse);
    }

    public UserResponse getUserById(String id) {
        return  userMapper.toUserResponse(userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXITED)));
    }

    public UserResponse updateUser(String id, UserUpdateRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXITED));

        if(userRepository.existsUserByPhone(request.getPhone()) && !user.getPhone().equals(request.getPhone())) {
            throw new AppException(ErrorCode.PHONENUMBER_EXISTED);
        }
        else if (userRepository.existsUserByEmail(request.getEmail()) && !user.getEmail().equals(request.getEmail())) {
            throw new AppException(ErrorCode.EMAIL_EXISTED);
        }

        userMapper.updateUser(user, request);

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public void changePassword(String id, PasswordChangeRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXITED));

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new AppException(ErrorCode.INVALID_PASSWORD);
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }

    public void deleteUser(String id) {
        if (!userRepository.existsById(id)) {
            throw new AppException(ErrorCode.USER_NOT_EXITED);
        }

        userRepository.deleteById(id);
    }

    public long countUsers() {
        return userRepository.countCustomers();
    }

    public List<LatestUsersResponse> getLatestUsers() {
        return userRepository.getLatestUsers();
    }

    public User getUserByPhone(String phone) {
        return userRepository.findUserByPhone(phone).
                orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXITED));
    }

    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email).
                orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXITED));
    }

    public String generateNextId() {
        String maxId = userRepository.findMaxId();
        if (maxId == null) {
            return "KH250001";
        }

        int currentMax = Integer.parseInt(maxId.substring(2));
        int nextId = currentMax + 1;
        return "KH" + nextId;
    }
}