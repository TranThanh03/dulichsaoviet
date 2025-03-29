package com.websitesaoviet.WebsiteSaoViet.service;

import com.websitesaoviet.WebsiteSaoViet.dto.request.TourCreationRequest;
import com.websitesaoviet.WebsiteSaoViet.dto.request.TourUpdateRequest;
import com.websitesaoviet.WebsiteSaoViet.dto.response.admin.AssignmentToursResponse;
import com.websitesaoviet.WebsiteSaoViet.dto.response.admin.PopularToursResponse;
import com.websitesaoviet.WebsiteSaoViet.dto.response.user.TourNewResponse;
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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TourService {
    TourRepository tourRepository;
    TourMapper tourMapper;
    SequenceService sequenceService;

    public TourResponse createTour(TourCreationRequest request) {
        Tour tour = tourMapper.createTour(request);

        tour.setCode(String.valueOf(generateNextCode("tour")));
        tour.setCreatedTime(LocalDateTime.now());
        tour.setNumberOfOrders(0);

        return tourMapper.toTourResponse(tourRepository.save(tour));
    }

    public Page<TourResponse> getTours(Pageable pageable) {
        return tourRepository.findAll(pageable).map(tourMapper::toTourResponse);
    }

    public List<AssignmentToursResponse> getToursByAssignment() {
        return tourMapper.toTourListResponse(tourRepository.findAll());
    }

    public List<TourNewResponse> getToursNew() {
        return tourRepository.getTourListNew();
    }

    public TourResponse getTourById(String id) {
        return tourMapper.toTourResponse(tourRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.TOUR_NOT_EXITED)));
    }

    public Page<TourResponse> getTourByCategoryId(int id, Pageable pageable) {
        return tourRepository.findToursByCategoryId(id, pageable).map(tourMapper::toTourResponse);
    }

    @Transactional
    public void addOders(String id, int people) {
        tourRepository.addOrders(id, people);
    }

    public TourResponse updateTour(String id, TourUpdateRequest request) {
        Tour tour = tourRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.TOUR_NOT_EXITED));

        if (tour == null) {
            throw new AppException(ErrorCode.TOUR_NOT_EXITED);
        }

        tourMapper.updateTour(tour, request);
        tour.setCreatedTime(LocalDateTime.now());

        return tourMapper.toTourResponse(tourRepository.save(tour));
    }

    public void deleteTour(String id) {
        if (!tourRepository.existsById(id)) {
            throw new AppException(ErrorCode.TOUR_NOT_EXITED);
        }

        tourRepository.deleteById(id);
    }

    public Page<TourResponse> searchTours(String search, Pageable pageable) {
        return tourRepository.findByNameContainingIgnoreCaseOrderByOrdersDesc(search, pageable).map(tourMapper::toTourResponse);
    }

    public long countTours() {
        return tourRepository.count();
    }

    public List<PopularToursResponse> getPopularTours() {
        return tourRepository.getPopularTours();
    }

    public String generateNextCode(String type) {
        int nextNumber = sequenceService.getNextNumber(type);

        return "T" + Year.now().getValue() + String.format("%05d", nextNumber);
    }
}