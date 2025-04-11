package com.websitesaoviet.WebsiteSaoViet.repository;

import com.websitesaoviet.WebsiteSaoViet.entity.Checkout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CheckoutRepository extends JpaRepository<Checkout, String> {
    boolean existsCheckoutByCode(String code);

    Checkout findCheckoutByIdAndStatus(String id, String status);
}