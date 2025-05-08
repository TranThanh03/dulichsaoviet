package com.websitesaoviet.WebsiteSaoViet.service;

import com.websitesaoviet.WebsiteSaoViet.dto.request.common.PasswordChangeRequest;
import com.websitesaoviet.WebsiteSaoViet.dto.request.user.CustomerCreationRequest;
import com.websitesaoviet.WebsiteSaoViet.dto.request.user.CustomerUpdateRequest;
import com.websitesaoviet.WebsiteSaoViet.dto.response.common.CustomerResponse;
import com.websitesaoviet.WebsiteSaoViet.dto.response.user.CustomerCreateResponse;
import com.websitesaoviet.WebsiteSaoViet.entity.Customer;
import com.websitesaoviet.WebsiteSaoViet.enums.CustomerStatus;
import com.websitesaoviet.WebsiteSaoViet.enums.Role;
import com.websitesaoviet.WebsiteSaoViet.exception.AppException;
import com.websitesaoviet.WebsiteSaoViet.exception.ErrorCode;
import com.websitesaoviet.WebsiteSaoViet.mapper.CustomerMapper;
import com.websitesaoviet.WebsiteSaoViet.repository.CustomerRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.HashSet;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomerService {
    CustomerRepository customerRepository;
    CustomerMapper customerMapper;
    SequenceService sequenceService;
    MailService mailService;

    @NonFinal
    @Value("${base.url}")
    protected String BASE_URL;

    @Transactional
    public CustomerCreateResponse createCustomer(CustomerCreationRequest request) {
        if(customerRepository.existsCustomerByPhone(request.getPhone())) {
            throw new AppException(ErrorCode.PHONENUMBER_EXISTED);
        }
        else if (customerRepository.existsCustomerByEmail(request.getEmail())) {
            throw new AppException(ErrorCode.EMAIL_EXISTED);
        }

        Customer customer = customerMapper.createCustomer(request);

        customer.setCode(String.valueOf(getNextCode("customer")));
        customer.setRegisteredTime(LocalDateTime.now());

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        customer.setPassword(passwordEncoder.encode(request.getPassword()));

        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());
        customer.setRoles(roles);

        customer.setStatus(CustomerStatus.INACTIVATE.getValue());

        Customer savedCustomer = customerRepository.save(customer);

        String emailContent = String.format(
                "<html><body>" +
                        "<p>Xin chào,</p>" +
                        "<p>Vui lòng nhấn vào link dưới đây để kích hoạt tài khoản của bạn:</p>" +
                        "<a href='%s/customer/activate/%s'>Kích hoạt tài khoản</a>" +
                        "<p>Trân trọng, <b>Sao Việt - Vivu ba miền</b></p>" +
                        "</body></html>", BASE_URL, savedCustomer.getId()
        );

        mailService.sendToQueue(
            savedCustomer.getEmail(),
            "Kích hoạt tài khoản",
            emailContent
        );

        return customerMapper.toCustomerCreateResponse(savedCustomer);
    }

    public Page<CustomerResponse> getCustomers(Pageable pageable) {
        return customerRepository.findAll(pageable).map(customerMapper::toCustomerResponse);
    }

    public CustomerResponse getCustomerById(String id) {
        return customerMapper.toCustomerResponse(customerRepository.findById(id)
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

    public void deleteCustomer(String id) {
        if (!customerRepository.existsById(id)) {
            throw new AppException(ErrorCode.USER_NOT_EXITED);
        }

        customerRepository.deleteById(id);
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

    public void activateCustomer(String id) {
        Customer customer = customerRepository.findByIdAndStatus(id, CustomerStatus.INACTIVATE.getValue())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXITED));

        customer.setStatus(CustomerStatus.ACTIVATE.getValue());

        customerRepository.save(customer);
    }

    public void blockCustomer(String id) {
        Customer customer = customerRepository.findByIdAndStatus(id, CustomerStatus.ACTIVATE.getValue())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXITED));

        customer.setStatus(CustomerStatus.BLOCKED.getValue());

        customerRepository.save(customer);
    }

    public void unblockCustomer(String id) {
        Customer customer = customerRepository.findByIdAndStatus(id, CustomerStatus.BLOCKED.getValue())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXITED));

        customer.setStatus(CustomerStatus.ACTIVATE.getValue());

        customerRepository.save(customer);
    }

    public Customer getCustomerByPhone(String phone) {
        return customerRepository.findCustomerByPhone(phone).
                orElseThrow(() -> new AppException(ErrorCode.ACCOUNT_NOT_EXITED));
    }

    public Customer getCustomerByEmail(String email) {
        return customerRepository.findCustomerByEmail(email).
                orElseThrow(() -> new AppException(ErrorCode.ACCOUNT_NOT_EXITED));
    }

    public String getNextCode(String type) {
        int nextCode = sequenceService.getNextNumber(type.toLowerCase());

        return "KH" + Year.now().getValue() + String.format("%06d", nextCode);
    }

    public long getCount() {
        return customerRepository.count();
    }
}