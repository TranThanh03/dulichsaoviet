package com.websitesaoviet.WebsiteSaoViet.service;

import com.websitesaoviet.WebsiteSaoViet.dto.request.admin.TourCreationRequest;
import com.websitesaoviet.WebsiteSaoViet.dto.request.admin.TourUpdateRequest;
import com.websitesaoviet.WebsiteSaoViet.dto.request.user.FilterToursAreaRequest;
import com.websitesaoviet.WebsiteSaoViet.dto.request.user.FilterToursRequest;
import com.websitesaoviet.WebsiteSaoViet.dto.request.user.SearchToursDestinationRequest;
import com.websitesaoviet.WebsiteSaoViet.dto.request.user.SearchToursRequest;
import com.websitesaoviet.WebsiteSaoViet.dto.response.common.TourResponse;
import com.websitesaoviet.WebsiteSaoViet.dto.response.user.*;
import com.websitesaoviet.WebsiteSaoViet.entity.Tour;
import com.websitesaoviet.WebsiteSaoViet.exception.AppException;
import com.websitesaoviet.WebsiteSaoViet.exception.ErrorCode;
import com.websitesaoviet.WebsiteSaoViet.mapper.TourMapper;
import com.websitesaoviet.WebsiteSaoViet.repository.TourRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.Normalizer;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
        tour.setTimeStamp(currentTime);
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
        tour.setTimeStamp(currentTime);

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

    public Page<FilterToursResponse> getFilteredTours(FilterToursRequest request, int page, int size) {
        Double minPrice = null;
        Double maxPrice = null;
        String area = null;

        if (request.getPrice() != null && request.getPrice().size() > 0) {
            if (request.getPrice().get(0) >= 0) {
                minPrice = request.getPrice().get(0);
            }
            if (request.getPrice().size() > 1) {
                maxPrice = request.getPrice().get(1);
            }
        }

        if (request.getArea() != null) {
            switch (request.getArea().toLowerCase()) {
                case "b" -> area = "b";
                case "t" -> area = "t";
                case "n" -> area = "n";
                default -> area = null;
            }
        }

        Integer rating = (request.getRating() != null && request.getRating() >= 1 && request.getRating() <= 5)
                ? request.getRating() : null;

        Integer quantityDay = (request.getDuration() != null && request.getDuration() >= 1 && request.getDuration() <= 100)
                ? request.getDuration() : null;

        Sort sort = Sort.unsorted();

        if (request.getSort() != null) {
            sort = switch (request.getSort()) {
                case "high-to-low" -> Sort.by(Sort.Direction.DESC, "adult_price");
                case "low-to-high" -> Sort.by(Sort.Direction.ASC, "adult_price");
                case "new" -> Sort.by(Sort.Direction.DESC, "created_time");
                case "old" -> Sort.by(Sort.Direction.ASC, "created_time");
                case "default" -> Sort.by(Sort.Direction.DESC, "quantity_order");
                default -> Sort.unsorted();
            };
        }

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Object[]> rawResult = tourRepository.findFilteredTours(
                minPrice, maxPrice, area, rating, quantityDay, pageable
        );

        return rawResult.map(obj -> new FilterToursResponse(
                (String) obj[0],
                (String) obj[1],
                (String) obj[2],
                (String) obj[3],
                ((Number) obj[4]).intValue(),
                (Double) obj[5],
                ((Number) obj[6]).intValue(),
                ((Number) obj[7]).intValue()
        ));
    }

    public AreaTourCountResponse getCountToursByArea() {
        return tourRepository.countToursByArea();
    }

    public List<PopularToursResponse> getPopularTours() {
        return tourRepository.findPopularTours()
                .stream()
                .map(tourMapper::toPopularToursResponse)
                .collect(Collectors.toList());
    }

    public List<ThreePopularToursResponse> getThreePopularTours() {
        List<Object[]> rawResult = tourRepository.findThreePopularTours();

        return rawResult.stream()
                .map(obj -> new ThreePopularToursResponse(
                        (String) obj[0],
                        (String) obj[1],
                        (String) obj[2],
                        (String) obj[3],
                        ((Number) obj[4]).intValue()
                ))
                .collect(Collectors.toList());
    }

    public Page<SearchToursResponse> getSearchTours(SearchToursRequest request, int page, int size) {
        String keyword = normalize(request.getKeyword() == null ? "" : request.getKeyword());
        String sort = request.getSort() == null ? "default" : request.getSort();

        Pageable pageable = PageRequest.of(page, size);

        List<Object[]> rawResult = tourRepository.findSearchTours();

        List<SearchToursResponse> allTours = rawResult.stream()
                .map(obj -> new SearchToursResponse(
                        (String) obj[0],
                        (String) obj[1],
                        (String) obj[2],
                        (String) obj[3],
                        ((Number) obj[4]).intValue(),
                        (Double) obj[5],
                        ((Number) obj[6]).intValue(),
                        ((Number) obj[7]).intValue(),
                        ((Timestamp) obj[8]).toLocalDateTime()
                ))
                .toList();

        List<SearchToursResponse> filtered = allTours.stream()
                .filter(tour -> {
                    String name = normalize(tour.getName());
                    String dest = normalize(tour.getDestination());
                    return name.contains(keyword) || dest.contains(keyword);
                })
                .toList();

        Comparator<SearchToursResponse> comparator = switch (sort) {
            case "new" -> Comparator.comparing(SearchToursResponse::getCreatedTime).reversed();
            case "old" -> Comparator.comparing(SearchToursResponse::getCreatedTime);
            case "high-to-low" -> Comparator.comparing(SearchToursResponse::getAdultPrice).reversed();
            case "low-to-high" -> Comparator.comparing(SearchToursResponse::getAdultPrice);
            default -> Comparator.comparing(SearchToursResponse::getQuantityDay);
        };

        List<SearchToursResponse> sorted = filtered.stream()
                .sorted(comparator)
                .toList();

        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), sorted.size());
        List<SearchToursResponse> paged = sorted.subList(start, end);

        return new PageImpl<>(paged, pageable, sorted.size());
    }

    public static String normalize(String input) {
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        String noDiacritics = pattern.matcher(normalized).replaceAll("")
                .replaceAll("đ", "d")
                .replaceAll("Đ", "D")
                .toLowerCase();

        noDiacritics = noDiacritics.trim().replaceAll("\\s+", " ");

        return noDiacritics;
    }

    public Page<SearchToursResponse> getSearchToursByDestination(SearchToursDestinationRequest request, int page, int size) {
        String sort = request.getSort() == null ? "default" : request.getSort();
        Pageable pageable = PageRequest.of(page, size);

        List<Object[]> rawResult = tourRepository.findSearchToursByDestination(request.getDestination().trim(), request.getStartDate(), request.getEndDate());

        List<SearchToursResponse> allTours = rawResult.stream()
                .map(obj -> new SearchToursResponse(
                        (String) obj[0],
                        (String) obj[1],
                        (String) obj[2],
                        (String) obj[3],
                        ((Number) obj[4]).intValue(),
                        (Double) obj[5],
                        ((Number) obj[6]).intValue(),
                        ((Number) obj[7]).intValue(),
                        ((Timestamp) obj[8]).toLocalDateTime()
                ))
                .toList();

        Comparator<SearchToursResponse> comparator = switch (sort) {
            case "new" -> Comparator.comparing(SearchToursResponse::getCreatedTime).reversed();
            case "old" -> Comparator.comparing(SearchToursResponse::getCreatedTime);
            case "high-to-low" -> Comparator.comparing(SearchToursResponse::getAdultPrice).reversed();
            case "low-to-high" -> Comparator.comparing(SearchToursResponse::getAdultPrice);
            default -> Comparator.comparing(SearchToursResponse::getQuantityDay);
        };

        List<SearchToursResponse> sorted = allTours.stream()
                .sorted(comparator)
                .toList();

        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), sorted.size());
        List<SearchToursResponse> paged = sorted.subList(start, end);

        return new PageImpl<>(paged, pageable, sorted.size());
    }

    public Page<FilterToursAreaResponse> getFilteredToursByArea(FilterToursAreaRequest request, int page, int size) {
        Double minPrice = null;
        Double maxPrice = null;
        String area = null;
        LocalDate startDate = request.getStartDate();
        LocalDate endDate = request.getEndDate();
        Sort sort = Sort.unsorted();

        if (request.getPrice() != null && request.getPrice().size() > 0) {
            if (request.getPrice().get(0) >= 0) {
                minPrice = request.getPrice().get(0);
            }
            if (request.getPrice().size() > 1) {
                maxPrice = request.getPrice().get(1);
            }
        }

        if (request.getArea() != null) {
            switch (request.getArea().toLowerCase()) {
                case "b" -> area = "b";
                case "t" -> area = "t";
                case "n" -> area = "n";
                default -> area = null;
            }
        }

        Integer quantityDay = (request.getDuration() != null && request.getDuration() >= 1 && request.getDuration() <= 100)
                ? request.getDuration() : null;

        if (request.getSort() != null) {
            sort = switch (request.getSort()) {
                case "high-to-low" -> Sort.by(Sort.Direction.DESC, "adult_price");
                case "low-to-high" -> Sort.by(Sort.Direction.ASC, "adult_price");
                case "new" -> Sort.by(Sort.Direction.DESC, "created_time");
                case "old" -> Sort.by(Sort.Direction.ASC, "created_time");
                case "default" -> Sort.by(Sort.Direction.DESC, "quantity_order");
                default -> Sort.unsorted();
            };
        }

        if (startDate != null && endDate != null) {
            sort = sort.and(Sort.by(Sort.Direction.ASC, "start_date"));
        } else if (startDate != null) {
            sort = sort.and(Sort.by(Sort.Direction.ASC, "start_date"));
        } else if (endDate != null) {
            sort = sort.and(Sort.by(Sort.Direction.DESC, "end_date"));
        }

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Object[]> rawResult = tourRepository.findFilteredToursByArea(
                minPrice, maxPrice, area, startDate, endDate, quantityDay, pageable
        );

        return rawResult.map(obj -> new FilterToursAreaResponse(
                (String) obj[0],
                (String) obj[1],
                (String) obj[2],
                (String) obj[3],
                ((Number) obj[4]).intValue(),
                (Double) obj[5],
                ((Date) obj[6]).toLocalDate(),
                ((Date) obj[7]).toLocalDate(),
                ((Number) obj[8]).intValue(),
                ((Number) obj[9]).intValue()
        ));
    }

    public long getCount() {
        return tourRepository.count();
    }
}