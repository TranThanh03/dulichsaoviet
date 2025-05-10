package com.websitesaoviet.WebsiteSaoViet.repository;

import com.websitesaoviet.WebsiteSaoViet.dto.response.admin.NewsListResponse;
import com.websitesaoviet.WebsiteSaoViet.entity.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface NewsRepository extends JpaRepository<News, String> {
    @Query("SELECT new com.websitesaoviet.WebsiteSaoViet.dto.response.admin.NewsListResponse(" +
            "n.id, n.code, n.title, n.image, n.type, n.viewCount, n.timeStamp) " +
            "FROM News n " +
            "WHERE " +
            "(:keyword IS NULL OR " +
            "  UPPER(n.code) LIKE CONCAT('%', UPPER(:keyword), '%') OR " +
            "  LOWER(n.title) LIKE CONCAT('%', LOWER(:keyword), '%')) " +
            "ORDER BY " +
            "CASE WHEN n.type = 'Nổi bật' THEN 0 ELSE 1 END, " +
            "n.timeStamp DESC")
    Page<NewsListResponse> findAllNews(@Param("keyword") String keyword, Pageable pageable);
}