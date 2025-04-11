package com.websitesaoviet.WebsiteSaoViet.service;

import com.websitesaoviet.WebsiteSaoViet.dto.request.admin.TourCreationRequest;
import com.websitesaoviet.WebsiteSaoViet.dto.request.admin.TourUpdateRequest;
import com.websitesaoviet.WebsiteSaoViet.dto.response.common.TourResponse;
import com.websitesaoviet.WebsiteSaoViet.entity.Tour;
import com.websitesaoviet.WebsiteSaoViet.exception.AppException;
import com.websitesaoviet.WebsiteSaoViet.exception.ErrorCode;
import com.websitesaoviet.WebsiteSaoViet.mapper.TourMapper;
import com.websitesaoviet.WebsiteSaoViet.repository.TourRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Year;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TourService {
    TourRepository tourRepository;
    TourMapper tourMapper;
    SequenceService sequenceService;

    public TourResponse createTour(TourCreationRequest request) {
        LocalDateTime currentTime = LocalDateTime.now();

        Tour tour = tourMapper.createTour(request);

        tour.setCode(getNextCode("tour"));
        tour.setCreatedTime(currentTime);
        tour.setQuantityOrder(0);

        return tourMapper.toTourResponse(tourRepository.save(tour));
    }

    public Page<TourResponse> getTours(Pageable pageable) {
        return tourRepository.findAll(pageable).map(tourMapper::toTourResponse);
    }

    public TourResponse getTourById(String id) {
        return tourMapper.toTourResponse(tourRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.TOUR_NOT_EXITED)));
    }

    public TourResponse updateTour(String id, TourUpdateRequest request) {
        Tour tour = tourRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.TOUR_NOT_EXITED));

        LocalDateTime currentTime = LocalDateTime.now();

        tourMapper.updateTour(tour, request);
        tour.setCreatedTime(currentTime);

        return tourMapper.toTourResponse(tourRepository.save(tour));
    }

    public void deleteTour(String id) {
        if (!tourRepository.existsById(id)) {
            throw new AppException(ErrorCode.TOUR_NOT_EXITED);
        }

        tourRepository.deleteById(id);
    }

    public void addOrders(String id, int orders) {
        tourRepository.addOrders(id, orders);
    }

    public String getNextCode(String type) {
        int nextCode = sequenceService.getNextNumber(type.toLowerCase());

        return "T" + Year.now().getValue() + String.format("%06d", nextCode);
    }
}