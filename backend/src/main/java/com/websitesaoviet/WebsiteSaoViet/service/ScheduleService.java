package com.websitesaoviet.WebsiteSaoViet.service;

import com.websitesaoviet.WebsiteSaoViet.dto.request.admin.ScheduleCreationRequest;
import com.websitesaoviet.WebsiteSaoViet.dto.response.admin.ScheduleListResponse;
import com.websitesaoviet.WebsiteSaoViet.dto.response.common.ScheduleResponse;
import com.websitesaoviet.WebsiteSaoViet.dto.response.user.ScheduleSummaryResponse;
import com.websitesaoviet.WebsiteSaoViet.dto.response.user.ScheduleTourResponse;
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

import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ScheduleService {
    ScheduleRepository scheduleRepository;
    ScheduleMapper scheduleMapper;
    SequenceService sequenceService;
    TourService tourService;

    public ScheduleResponse createSchedule(ScheduleCreationRequest request) {
        LocalDate today = LocalDate.now();
        LocalDate minStartDate = today.plusDays(3);

        if (scheduleRepository.existsScheduleByTourIdAndStartDate(request.getTourId(), request.getStartDate())) {
            throw new AppException(ErrorCode.SCHEDULE_EXITED);
        }

        if (request.getStartDate().isBefore(minStartDate)) {
            throw new AppException(ErrorCode.STARTDATE_INVALID);
        }

        var tour = tourService.getTourById(request.getTourId());
        LocalDate endDate = LocalDate.now();

        Schedule schedule = scheduleMapper.createSchedule(request);

        schedule.setCode(getNextCode("schedule"));
        schedule.setEndDate(request.getStartDate().plusDays(tour.getQuantityDay()));
        schedule.setEndDate(endDate);
        schedule.setQuantityPeople(0);
        schedule.setStatus(CommonStatus.NOT_STARTED.getValue());

        return scheduleMapper.toScheduleResponse(scheduleRepository.save(schedule));
    }

    public Page<ScheduleListResponse> getSchedules(String keyword, Pageable pageable) {
        String keywordText = keyword.trim();
        LocalDate keywordDate = null;

        if (!keywordText.equals("") && !keywordText.matches("^[a-zA-Z0-9]+$")) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                keywordDate = LocalDate.parse(keyword, formatter);
                keywordText = null;
            } catch (Exception ignored) {
                throw new AppException(ErrorCode.DATETIME_INVALID);
            }
        }

        return scheduleRepository.findAllSchedules(keywordText, keywordDate, pageable);
    }

    public ScheduleResponse getScheduleById(String id) {
        return scheduleMapper.toScheduleResponse(scheduleRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.SCHEDULE_NOT_EXITED)));
    }

    public List<ScheduleSummaryResponse> getSchedulesByTourId(String tourId) {
        return scheduleRepository.findSchedulesByTourId(tourId);
    }

    public ScheduleTourResponse getScheduleTourById(String id) {
        return scheduleRepository.findScheduleTourById(id);
    }

    public void deleteSchedule(String id) {
        if (scheduleRepository.existsByIdAndStatus(id, CommonStatus.IN_PROGRESS.getValue())) {
            throw new AppException(ErrorCode.SCHEDULE_IN_PROGRESS);
        }

        scheduleRepository.deleteById(id);
    }

    public void deleteByTourId(String tourId) {
        if (scheduleRepository.existsScheduleByTourIdAndStatus(tourId, CommonStatus.IN_PROGRESS.getValue())) {
            throw new AppException(ErrorCode.SCHEDULE_IN_PROGRESS);
        }

        scheduleRepository.deleteAllByTourId(tourId);
    }

    public boolean existsScheduleByQuantityPeople(String id, int people) {
        return scheduleRepository.existsScheduleByQuantityPeople(id, people);
    }

    public void addQuantityPeople(String id, int people) {
        scheduleRepository.addQuantityPeople(id, people);
    }

    public void minusQuantityPeople(String id, int people) {
        scheduleRepository.minusQuantityPeople(id, people);
    }

    public String getNextCode(String type) {
        int nextCode = sequenceService.getNextNumber(type.toLowerCase());

        return "LT" + Year.now().getValue() + String.format("%09d", nextCode);
    }
}