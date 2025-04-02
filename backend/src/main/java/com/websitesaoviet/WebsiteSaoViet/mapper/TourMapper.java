package com.websitesaoviet.WebsiteSaoViet.mapper;

import com.websitesaoviet.WebsiteSaoViet.dto.request.admin.TourCreationRequest;
import com.websitesaoviet.WebsiteSaoViet.dto.request.admin.TourUpdateRequest;
import com.websitesaoviet.WebsiteSaoViet.dto.response.common.TourResponse;
import com.websitesaoviet.WebsiteSaoViet.entity.Tour;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TourMapper {
    Tour createTour(TourCreationRequest request);

    TourResponse toTourResponse(Tour tour);

    void updateTour(@MappingTarget Tour tour, TourUpdateRequest request);
}
