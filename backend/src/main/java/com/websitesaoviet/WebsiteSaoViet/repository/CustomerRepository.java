package com.websitesaoviet.WebsiteSaoViet.repository;

import com.websitesaoviet.WebsiteSaoViet.dto.response.admin.LatestCustomersResponse;
import com.websitesaoviet.WebsiteSaoViet.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
    boolean existsCustomerByPhone(String phone);
    boolean existsCustomerByEmail(String email);
    Optional<Customer> findCustomerByPhone(String phone);
    Optional<Customer> findCustomerByEmail(String email);

    @Query("SELECT COUNT(DISTINCT c) FROM Customer c JOIN c.roles r WHERE r = 'USER'")
    long countCustomers();

    @Query("SELECT new com.websitesaoviet.WebsiteSaoViet.dto.response.admin.LatestCustomersResponse(" +
            "c.code, c.fullName, c.registerTime) " +
            "FROM Customer c " +
            "JOIN c.roles r " +
            "WHERE r = 'USER' " +
            "ORDER BY c.registerTime DESC "+
            "LIMIT 5")
    List<LatestCustomersResponse> getLatestCustomers();

    @Query("SELECT c FROM Customer c JOIN c.roles r WHERE r = 'USER'")
    Page<Customer> findCustomersWithRoleCustomer(Pageable pageable);
}