package com.websitesaoviet.WebsiteSaoViet.repository;

import com.websitesaoviet.WebsiteSaoViet.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {
    boolean existsAdminByPhone(String phone);
    boolean existsAdminByEmail(String email);
    Optional<Admin> findAdminByPhone(String phone);
    Optional<Admin> findAdminByEmail(String email);
}