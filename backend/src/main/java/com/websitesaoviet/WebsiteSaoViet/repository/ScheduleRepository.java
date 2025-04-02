package com.websitesaoviet.WebsiteSaoViet.repository;

import com.websitesaoviet.WebsiteSaoViet.dto.response.user.ScheduleSummaryResponse;
import com.websitesaoviet.WebsiteSaoViet.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, String> {
    List<ScheduleSummaryResponse> findSchedulesByTourIdAndStatus(String tourId, String status);

    boolean existsByIdAndStatus(String id, String status);

    boolean existsScheduleByTourId(String tourId);

    boolean existsScheduleByTourIdAndStatus(String tourId, String status);

    @Transactional
    @Modifying
    void deleteAllByTourId(String tourId);

//    @Query("SELECT COUNT(s) FROM")
//    boolean existsScheduleByIdAndPeople(String tourId, int people);
}