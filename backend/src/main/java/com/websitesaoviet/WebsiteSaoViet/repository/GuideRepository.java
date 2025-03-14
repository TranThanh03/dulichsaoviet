package com.websitesaoviet.WebsiteSaoViet.repository;

import com.websitesaoviet.WebsiteSaoViet.entity.Guide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuideRepository extends JpaRepository<Guide, String> {
    @Query("SELECT MAX(g.id) FROM Guide g")
    String findMaxId();

    List<Guide> getGuideByEvaluate(int evaluate);

    long count();
}
