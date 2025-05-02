package com.websitesaoviet.WebsiteSaoViet.repository;

import com.websitesaoviet.WebsiteSaoViet.dto.response.user.ScheduleSummaryResponse;
import com.websitesaoviet.WebsiteSaoViet.dto.response.user.ScheduleTourResponse;
import com.websitesaoviet.WebsiteSaoViet.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, String> {
    @Query("SELECT new com.websitesaoviet.WebsiteSaoViet.dto.response.user.ScheduleSummaryResponse(" +
            "s.id, s.startDate, s.endDate, s.adultPrice, s.childrenPrice, s.quantityPeople, s.totalPeople) " +
            "FROM Schedule s " +
            "WHERE s.tourId = :tourId AND s.status = 'Chưa diễn ra' AND s.quantityPeople < s.totalPeople " +
            "ORDER BY s.startDate ASC")
    List<ScheduleSummaryResponse> findSchedulesByTourId(@Param("tourId") String tourId);

    @Query("SELECT new com.websitesaoviet.WebsiteSaoViet.dto.response.user.ScheduleTourResponse(" +
            "t.code, t.name, s.startDate, s.endDate, t.quantityDay, s.totalPeople - s.quantityPeople, s.adultPrice, s.childrenPrice) " +
            "FROM Schedule s " +
            "INNER JOIN Tour t on s.tourId = t.id " +
            "WHERE s.id = :id AND s.status = 'Chưa diễn ra' AND s.quantityPeople < s.totalPeople")
    ScheduleTourResponse findScheduleTourById(@Param("id") String id);

    boolean existsByIdAndStatus(String id, String status);

    boolean existsScheduleByTourIdAndStatus(String tourId, String status);

    @Transactional
    @Modifying
    void deleteAllByTourId(String tourId);

    @Query("SELECT COUNT(s) > 0 FROM Schedule s WHERE s.id = :id AND s.status = 'Chưa diễn ra' AND s.quantityPeople + :people <= s.totalPeople")
    boolean existsScheduleByQuantityPeople(@Param("id") String id, @Param("people") int people);

    @Transactional
    @Modifying
    @Query("UPDATE Schedule s SET s.quantityPeople = s.quantityPeople + :people WHERE s.id = :id")
    void addQuantityPeople(@Param("id") String id, @Param("people") int people);

    @Transactional
    @Modifying
    @Query("UPDATE Schedule s SET s.quantityPeople = s.quantityPeople - :people WHERE s.id = :id")
    void minusQuantityPeople(@Param("id") String id, @Param("people") int people);

//    @Query("SELECT COUNT(s) FROM")
//    boolean existsScheduleByIdAndPeople(String tourId, int people);
}