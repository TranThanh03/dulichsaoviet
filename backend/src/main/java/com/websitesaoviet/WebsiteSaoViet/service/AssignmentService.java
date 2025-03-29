package com.websitesaoviet.WebsiteSaoViet.service;

import com.websitesaoviet.WebsiteSaoViet.dto.request.AssignmentCreationRequest;
import com.websitesaoviet.WebsiteSaoViet.dto.response.user.AssignmentGuideResponse;
import com.websitesaoviet.WebsiteSaoViet.dto.response.common.AssignmentResponse;
import com.websitesaoviet.WebsiteSaoViet.dto.response.user.AssignmentTourGuideResponse;
import com.websitesaoviet.WebsiteSaoViet.entity.Assignment;
import com.websitesaoviet.WebsiteSaoViet.exception.AppException;
import com.websitesaoviet.WebsiteSaoViet.exception.ErrorCode;
import com.websitesaoviet.WebsiteSaoViet.mapper.AssignmentMapper;
import com.websitesaoviet.WebsiteSaoViet.repository.AssignmentRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Year;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AssignmentService {
    AssignmentRepository assignmentRepository;
    AssignmentMapper assignmentMapper;
    SequenceService sequenceService;

    public AssignmentResponse createAssignment(AssignmentCreationRequest request) {
        LocalDate today = LocalDate.now();

        if (request.getStartDate().isBefore(today)) {
            throw new AppException(ErrorCode.STARTDATE_INVALID);
        }
        if (request.getEndDate().isBefore(request.getStartDate())) {
            throw new AppException(ErrorCode.ENDDATE_INVALID);
        }

        Assignment assignment = assignmentMapper.createAssignment(request);

        assignment.setCode(String.valueOf(generateNextCode("assignment")));
        assignment.setNumberOfPeoples(0);
        assignment.setStatus("Đang diễn ra");

        return assignmentMapper.toAssignmentResponse(assignmentRepository.save(assignment));
    }

    public Page<AssignmentResponse> getAssignments(Pageable pageable) {
        return assignmentRepository.findAll(pageable).map(assignmentMapper::toAssignmentResponse);
    }

    public Page<AssignmentGuideResponse> getGuideListByTourId(String id, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        return assignmentRepository.findGuideListByTourId(id, pageable);
    }

    public AssignmentTourGuideResponse getAssignmentById(String id) {
        return assignmentRepository.findAssignmentById(id);
    }

    public boolean existsAssignment(String id, int people) {
        return assignmentRepository.existsAssignmentByNumberPeople(id, people);
    }

    @Transactional
    public void addNumberOfPeople(String id, int people) {
        assignmentRepository.addNumberOfPeople(id, people);
    }

    @Transactional
    public void minusNumberOfPeople(String id, int people) {
        assignmentRepository.minusNumberOfPeople(id, people);
    }

    public void deleteAssignment(String id) {
        if (!assignmentRepository.existsById(id)) {
            throw new AppException(ErrorCode.ASSIGNMENT_NOT_EXITED);
        }

        assignmentRepository.deleteById(id);
    }

    public void deleteByTourId(String tourId) {
        assignmentRepository.deleteByTourId(tourId);
    }

    public String generateNextCode(String type) {
        int nextNumber = sequenceService.getNextNumber(type);

        return "PC" + Year.now().getValue() + String.format("%07d", nextNumber);
    }
}