package com.websitesaoviet.WebsiteSaoViet.service;

import com.websitesaoviet.WebsiteSaoViet.dto.request.admin.ScheduleCreationRequest;
import com.websitesaoviet.WebsiteSaoViet.dto.response.common.CustomerResponse;
import com.websitesaoviet.WebsiteSaoViet.dto.response.common.ScheduleResponse;
import com.websitesaoviet.WebsiteSaoViet.dto.response.user.ScheduleSummaryResponse;
import com.websitesaoviet.WebsiteSaoViet.entity.Customer;
import com.websitesaoviet.WebsiteSaoViet.entity.Schedule;
import com.websitesaoviet.WebsiteSaoViet.enums.CommonStatus;
import com.websitesaoviet.WebsiteSaoViet.exception.AppException;
import com.websitesaoviet.WebsiteSaoViet.exception.ErrorCode;
import com.websitesaoviet.WebsiteSaoViet.mapper.ScheduleMapper;
import com.websitesaoviet.WebsiteSaoViet.repository.ScheduleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ScheduleService {
    ScheduleRepository scheduleRepository;
    ScheduleMapper scheduleMapper;
    SequenceService sequenceService;

    public ScheduleResponse createSchedule(ScheduleCreationRequest request) {
        LocalDate today = LocalDate.now();
        LocalDate minStartDate = today.plusDays(3);

        if (request.getStartDate().isBefore(minStartDate)) {
            throw new AppException(ErrorCode.STARTDATE_INVALID);
        }

        LocalDate endDate = LocalDate.now();

        Schedule schedule = scheduleMapper.createSchedule(request);

        schedule.setCode(getNextCode("schedule"));
        schedule.setEndDate(endDate);
        schedule.setQuantityPeople(0);
        schedule.setStatus(CommonStatus.NOT_STARTED.getValue());

        return scheduleMapper.toScheduleResponse(scheduleRepository.save(schedule));
    }

    public Page<ScheduleResponse> getSchedules(Pageable pageable) {
        return scheduleRepository.findAll(pageable).map(scheduleMapper::toScheduleResponse);
    }

    public ScheduleResponse getScheduleById(String id) {
        return scheduleMapper.toScheduleResponse(scheduleRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.SCHEDULE_NOT_EXITED)));
    }

    public List<ScheduleSummaryResponse> getSchedulesByTourId(String tourId) {
        return scheduleRepository.findSchedulesByTourIdAndStatus(tourId, CommonStatus.NOT_STARTED.getValue());
    }

    public void deleteSchedule(String id) {
        if (scheduleRepository.existsByIdAndStatus(id, CommonStatus.IN_PROGRESS.getValue())) {
            throw new AppException(ErrorCode.SCHEDULE_IN_PROGRESS);
        }

        scheduleRepository.deleteById(id);
    }

    public void deleteByTourId(String tourId) {
        if (!scheduleRepository.existsScheduleByTourId(tourId)) {
            throw new AppException(ErrorCode.SCHEDULE_NOT_EXITED);
        }

        if (scheduleRepository.existsScheduleByTourIdAndStatus(tourId, CommonStatus.IN_PROGRESS.getValue())) {
            throw new AppException(ErrorCode.SCHEDULE_IN_PROGRESS);
        }

        scheduleRepository.deleteAllByTourId(tourId);
    }

//    public boolean existsSchedule(String id, int people) {
//        return scheduleRepository.existsScheduleByNumberPeople(id, people);
//    }
//
//    @Transactional
//    public void addQuantityPeople(String id, int people) {
//        scheduleRepository.addQuantityPeople(id, people);
//    }
//
//    @Transactional
//    public void minusQuantityPeople(String id, int people) {
//        scheduleRepository.minusQuantityPeople(id, people);
//    }

    public String getNextCode(String type) {
        int nextCode = sequenceService.getNextNumber(type.toLowerCase());

        return "LT" + Year.now().getValue() + String.format("%09d", nextCode);
    }
}