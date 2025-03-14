package com.websitesaoviet.WebsiteSaoViet.repository;

import com.websitesaoviet.WebsiteSaoViet.dto.response.admin.LatestUsersResponse;
import com.websitesaoviet.WebsiteSaoViet.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Query("SELECT MAX(u.userId) FROM User u")
    String findMaxId();
    boolean existsUserByPhone(String phone);
    boolean existsUserByEmail(String email);
    Optional<User> findUserByPhone(String phone);
    Optional<User> findUserByEmail(String email);

    @Query("SELECT COUNT(DISTINCT u) FROM User u JOIN u.roles r WHERE r = 'USER'")
    long countCustomers();

    @Query("SELECT new com.websitesaoviet.WebsiteSaoViet.dto.response.admin.LatestUsersResponse(" +
            "u.userId, u.fullName, u.registerTime) " +
            "FROM User u " +
            "JOIN u.roles r " +
            "WHERE r = 'USER' " +
            "ORDER BY u.registerTime DESC "+
            "LIMIT 5")
    List<LatestUsersResponse> getLatestUsers();

    @Query("SELECT u FROM User u JOIN u.roles r WHERE r = 'USER'")
    Page<User> findUsersWithRoleUser(Pageable pageable);
}