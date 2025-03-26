package com.websitesaoviet.WebsiteSaoViet.repository;

import com.websitesaoviet.WebsiteSaoViet.dto.response.user.AssignmentGuideResponse;
import com.websitesaoviet.WebsiteSaoViet.dto.response.user.AssignmentTourGuideResponse;
import com.websitesaoviet.WebsiteSaoViet.dto.response.user.AssignmentTourResponse;
import com.websitesaoviet.WebsiteSaoViet.entity.Assignment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, String> {
    @Query("SELECT COUNT(a) > 0 FROM Assignment a WHERE a.id = :id AND a.numberOfPeople + :people > a.totalPeople")
    boolean existsAssignmentByNumberPeople(@Param("id") String id, @Param("people") int people);

    @Query("SELECT COUNT(a) > 0 " +
            "FROM Assignment a " +
            "WHERE a.guideId = :guideId AND " +
            "(a.startDate BETWEEN :startDate AND :endDate OR " +
            "a.endDate BETWEEN :startDate AND :endDate OR " +
            "(a.startDate <= :startDate AND a.endDate >= :endDate))")
    boolean existsAssignmentByGuideId(@Param("guideId") String guideId,
                                      @Param("startDate") LocalDate startDate,
                                      @Param("endDate") LocalDate endDate);


    @Query("SELECT new com.websitesaoviet.WebsiteSaoViet.dto.response.user.AssignmentTourResponse(" +
            "a.id, a.tourId, t.name, t.image, t.price, " +
            "a.startDate, a.endDate, a.numberOfPeople, a.totalPeople, a.guidePrice) " +
            "FROM Assignment a " +
            "JOIN Tour t ON a.tourId = t.id " +
            "WHERE a.guideId = :guideId AND a.status = 'Đang diễn ra' " +
            "ORDER BY a.startDate ASC")
    List<AssignmentTourResponse> findTourListByGuideId(@Param("guideId") String guideId);

    @Query("SELECT new com.websitesaoviet.WebsiteSaoViet.dto.response.user.AssignmentGuideResponse(" +
            "a.id, a.guideId, g.fullName, g.avatar, g.evaluate, g.gender, g.dateOfBirth, " +
            "a.startDate, a.endDate, a.numberOfPeople, a.totalPeople, a.guidePrice) " +
            "FROM Assignment a " +
            "JOIN Guide g ON a.guideId = g.id " +
            "WHERE a.tourId = :tourId AND a.status = 'Đang diễn ra' AND a.numberOfPeople < a.totalPeople " +
            "ORDER BY g.evaluate DESC, a.startDate ASC")
    Page<AssignmentGuideResponse> findGuideListByTourId(@Param("tourId") String tourId, Pageable pageable);

    @Query("SELECT new com.websitesaoviet.WebsiteSaoViet.dto.response.user.AssignmentTourGuideResponse(" +
            "a.id, t.id, t.name, t.image, t.price, g.id, g.fullName, g.avatar, " +
            "a.startDate, a.endDate, a.numberOfPeople, a.totalPeople, a.guidePrice) " +
            "FROM Assignment a " +
            "JOIN Tour t ON a.tourId = t.id " +
            "JOIN Guide g ON a.guideId = g.id " +
            "WHERE a.id = :assignmentId AND a.status = 'Đang diễn ra' AND a.numberOfPeople < a.totalPeople")
    AssignmentTourGuideResponse findAssignmentById(@Param("assignmentId") String assignmentId);

    @Modifying
    @Query("UPDATE Assignment a SET a.numberOfPeople = a.numberOfPeople + :people WHERE a.id = :assignmentId")
    void addNumberOfPeople(@Param("assignmentId") String assignmentId, @Param("people") int people);

    @Modifying
    @Query("UPDATE Assignment a SET a.numberOfPeople = a.numberOfPeople - :people WHERE a.id = :assignmentId")
    void minusNumberOfPeople(@Param("assignmentId") String assignmentId, @Param("people") int people);

    @Modifying
    @Transactional
    void deleteByTourId(String tourId);

    @Modifying
    @Transactional
    void deleteByGuideId(String guideId);
}