package com.websitesaoviet.WebsiteSaoViet.repository;

import com.websitesaoviet.WebsiteSaoViet.dto.response.user.ChatMessagesResponse;
import com.websitesaoviet.WebsiteSaoViet.entity.ChatSessions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ChatSessionsRepository extends JpaRepository<ChatSessions, String> {
    boolean existsChatSessionsByCustomerCode(String code);

    @Query("SELECT new com.websitesaoviet.WebsiteSaoViet.dto.response.user.ChatMessagesResponse(" +
            "c.id, m.content, m.senderType, m.createdAt) " +
            "FROM ChatSessions c " +
            "LEFT JOIN Messages m ON c.id = m.chatId " +
            "WHERE c.customerCode = :code " +
            "ORDER BY m.createdAt ASC")
    List<ChatMessagesResponse> findByCustomerCode(@Param("code") String code);

    @Transactional
    @Modifying
    @Query("UPDATE ChatSessions c SET c.updatedAt = :updatedAt WHERE c.id = :id")
    void update(@Param("id") String id, @Param("updatedAt") LocalDateTime updatedAt);
}