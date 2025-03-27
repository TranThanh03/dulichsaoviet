package com.websitesaoviet.WebsiteSaoViet.service;

import com.websitesaoviet.WebsiteSaoViet.dto.request.CustomerCreationRequest;
import com.websitesaoviet.WebsiteSaoViet.dto.request.CustomerUpdateRequest;
import com.websitesaoviet.WebsiteSaoViet.dto.request.PasswordChangeRequest;
import com.websitesaoviet.WebsiteSaoViet.dto.response.common.CustomerResponse;
import com.websitesaoviet.WebsiteSaoViet.dto.response.admin.LatestCustomersResponse;
import com.websitesaoviet.WebsiteSaoViet.entity.Customer;
import com.websitesaoviet.WebsiteSaoViet.enums.Role;
import com.websitesaoviet.WebsiteSaoViet.exception.AppException;
import com.websitesaoviet.WebsiteSaoViet.exception.ErrorCode;
import com.websitesaoviet.WebsiteSaoViet.mapper.CustomerMapper;
import com.websitesaoviet.WebsiteSaoViet.repository.CustomerRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomerService {
    CustomerRepository customerRepository;
    CustomerMapper customerMapper;
    SequenceService sequenceService;

    public CustomerResponse createCustomer(CustomerCreationRequest request) {
        if(customerRepository.existsCustomerByPhone(request.getPhone())) {
            throw new AppException(ErrorCode.PHONENUMBER_EXISTED);
        }
        else if (customerRepository.existsCustomerByEmail(request.getEmail())) {
            throw new AppException(ErrorCode.EMAIL_EXISTED);
        }

        Customer customer = customerMapper.createCustomer(request);

        customer.setCustomerCode(String.valueOf(generateNextCode("customer")));
        customer.setRegisterTime(LocalDateTime.now());

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        customer.setPassword(passwordEncoder.encode(request.getPassword()));

        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());

        customer.setRoles(roles);

        return customerMapper.toCustomerResponse(customerRepository.save(customer));
    }

    public Page<CustomerResponse> getCustomers(Pageable pageable) {
        return customerRepository.findCustomersWithRoleCustomer(pageable).map(customerMapper::toCustomerResponse);
    }

    public CustomerResponse getCustomerById(String id) {
        return  customerMapper.toCustomerResponse(customerRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXITED)));
    }

    public CustomerResponse updateCustomer(String id, CustomerUpdateRequest request) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXITED));

        if(customerRepository.existsCustomerByPhone(request.getPhone()) && !customer.getPhone().equals(request.getPhone())) {
            throw new AppException(ErrorCode.PHONENUMBER_EXISTED);
        }
        else if (customerRepository.existsCustomerByEmail(request.getEmail()) && !customer.getEmail().equals(request.getEmail())) {
            throw new AppException(ErrorCode.EMAIL_EXISTED);
        }

        customerMapper.updateCustomer(customer, request);

        return customerMapper.toCustomerResponse(customerRepository.save(customer));
    }

    public void changePassword(String id, PasswordChangeRequest request) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXITED));

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

        if (!passwordEncoder.matches(request.getCurrentPassword(), customer.getPassword())) {
            throw new AppException(ErrorCode.INVALID_PASSWORD);
        }

        customer.setPassword(passwordEncoder.encode(request.getNewPassword()));
        customerRepository.save(customer);
    }

    public void deleteCustomer(String id) {
        if (!customerRepository.existsById(id)) {
            throw new AppException(ErrorCode.USER_NOT_EXITED);
        }

        customerRepository.deleteById(id);
    }

    public long countCustomers() {
        return customerRepository.countCustomers();
    }

    public List<LatestCustomersResponse> getLatestCustomers() {
        return customerRepository.getLatestCustomers();
    }

    public Customer getCustomerByPhone(String phone) {
        return customerRepository.findCustomerByPhone(phone).
                orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXITED));
    }

    public Customer getCustomerByEmail(String email) {
        return customerRepository.findCustomerByEmail(email).
                orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXITED));
    }

    public String generateNextCode(String type) {
        int nextNumber = sequenceService.getNextNumber(type);

        return "KH" + Year.now().getValue() + String.format("%06d", nextNumber);
    }
}