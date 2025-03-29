package com.websitesaoviet.WebsiteSaoViet.mapper;

import com.websitesaoviet.WebsiteSaoViet.dto.request.TourCreationRequest;
import com.websitesaoviet.WebsiteSaoViet.dto.request.TourUpdateRequest;
import com.websitesaoviet.WebsiteSaoViet.dto.response.common.TourResponse;
import com.websitesaoviet.WebsiteSaoViet.dto.response.admin.AssignmentToursResponse;
import com.websitesaoviet.WebsiteSaoViet.entity.Tour;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TourMapper {
//    Tour createTour(TourCreationRequest request);
//
//    TourResponse toTourResponse(Tour tour);
//
//    List<AssignmentToursResponse> toTourListResponse(List<Tour> tourList);
//
//    void updateTour(@MappingTarget Tour tour, TourUpdateRequest request);
}
