package com.websitesaoviet.WebsiteSaoViet.repository;

import com.websitesaoviet.WebsiteSaoViet.entity.Customer;
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

//    @Query("SELECT new com.websitesaoviet.WebsiteSaoViet.dto.response.admin.LatestCustomersResponse(" +
//            "c.code, c.fullName, c.registeredTime) " +
//            "FROM Customer c " +
//            "ORDER BY c.registeredTime DESC " +
//            "LIMIT 5")
//    List<LatestCustomersResponse> getLatestCustomers();
}