package com.websitesaoviet.WebsiteSaoViet.mapper;

import com.websitesaoviet.WebsiteSaoViet.dto.request.AssignmentCreationRequest;
import com.websitesaoviet.WebsiteSaoViet.dto.response.common.AssignmentResponse;
import com.websitesaoviet.WebsiteSaoViet.entity.Assignment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AssignmentMapper {
    Assignment createAssignment(AssignmentCreationRequest request);

    AssignmentResponse toAssignmentResponse(Assignment assignment);
}