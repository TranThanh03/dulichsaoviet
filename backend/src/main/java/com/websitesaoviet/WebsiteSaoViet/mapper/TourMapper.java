package com.websitesaoviet.WebsiteSaoViet.mapper;

import com.websitesaoviet.WebsiteSaoViet.dto.request.admin.TourCreationRequest;
import com.websitesaoviet.WebsiteSaoViet.dto.request.admin.TourUpdateRequest;
import com.websitesaoviet.WebsiteSaoViet.dto.response.common.TourResponse;
import com.websitesaoviet.WebsiteSaoViet.dto.response.user.PopularToursResponse;
import com.websitesaoviet.WebsiteSaoViet.dto.response.user.SimilarToursResponse;
import com.websitesaoviet.WebsiteSaoViet.dto.response.user.TourBookingStatsResponse;
import com.websitesaoviet.WebsiteSaoViet.entity.Tour;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TourMapper {
    Tour createTour(TourCreationRequest request);

    TourResponse toTourResponse(Tour tour);

    @Mapping(source = "tour.id", target = "id")
    @Mapping(source = "tour.name", target = "name")
    @Mapping(source = "tour.image", target = "image")
    @Mapping(source = "tour.quantityDay", target = "quantityDay")
    @Mapping(source = "bookingCount", target = "bookingCount")
    PopularToursResponse toPopularToursResponse(TourBookingStatsResponse tour);

    @Mapping(source = "tour.quantityOrder", target = "bookingCount")
    PopularToursResponse to5PopularToursResponse(Tour tour);

    List<SimilarToursResponse> toSimilarToursResponse(List<Tour> tour);

    void updateTour(@MappingTarget Tour tour, TourUpdateRequest request);
}
