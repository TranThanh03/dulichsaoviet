package com.websitesaoviet.WebsiteSaoViet.service;

import com.websitesaoviet.WebsiteSaoViet.dto.request.AssignmentCreationRequest;
import com.websitesaoviet.WebsiteSaoViet.dto.request.AssignmentUpdateRequest;
import com.websitesaoviet.WebsiteSaoViet.dto.response.user.AssignmentGuideResponse;
import com.websitesaoviet.WebsiteSaoViet.dto.response.common.AssignmentResponse;
import com.websitesaoviet.WebsiteSaoViet.dto.response.user.AssignmentTourGuideResponse;
import com.websitesaoviet.WebsiteSaoViet.dto.response.user.AssignmentTourResponse;
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
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AssignmentService {
    AssignmentRepository assignmentRepository;
    AssignmentMapper assignmentMapper;

    public AssignmentResponse createAssignment(AssignmentCreationRequest request) {
        LocalDate today = LocalDate.now();

        if (request.getStartDate().isBefore(today)) {
            throw new AppException(ErrorCode.STARTDATE_INVALID);
        }
        if (request.getEndDate().isBefore(request.getStartDate())) {
            throw new AppException(ErrorCode.ENDDATE_INVALID);
        }

        if(assignmentRepository.existsAssignmentByGuideId(request.getGuideId(), request.getStartDate(), request.getEndDate())) {
            throw new AppException(ErrorCode.GUIDE_ASSIGNED);
        }

        Assignment assignment = assignmentMapper.createAssignment(request);

        assignment.setAssignmentCode(String.valueOf(generateNextId()));
        assignment.setNumberOfPeople(0);
        assignment.setStatus("Đang diễn ra");

        return assignmentMapper.toAssignmentResponse(assignmentRepository.save(assignment));
    }

    public Page<AssignmentResponse> getAssignments(Pageable pageable) {
        return assignmentRepository.findAll(pageable).map(assignmentMapper::toAssignmentResponse);
    }

    public List<AssignmentTourResponse> getTourListByGuideId(String id) {
        return assignmentRepository.findTourListByGuideId(id);
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

    public AssignmentResponse updateAssignment(String id, AssignmentUpdateRequest request) {
        Assignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ASSIGNMENT_NOT_EXITED));

        if (assignment == null) {
            throw new AppException(ErrorCode.ASSIGNMENT_NOT_EXITED);
        }

        assignment.setStatus("Đã kết thúc");

        return assignmentMapper.toAssignmentResponse(assignmentRepository.save(assignment));
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

    public void deleteByGuideId(String guideId) {
        assignmentRepository.deleteByGuideId(guideId);
    }

    public String generateNextId() {
//        String maxId = assignmentRepository.findMaxId();
//        if (maxId == null) {
//            return "PC25000001";
//        }
//
//        int currentMax = Integer.parseInt(maxId.substring(2));
//        int nextId = currentMax + 1;
//        return "PC" + nextId;
        return "a";
    }
}