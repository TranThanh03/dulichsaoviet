package com.websitesaoviet.WebsiteSaoViet.mapper;

import com.websitesaoviet.WebsiteSaoViet.dto.request.admin.ScheduleCreationRequest;
import com.websitesaoviet.WebsiteSaoViet.dto.response.common.ScheduleResponse;
import com.websitesaoviet.WebsiteSaoViet.entity.Schedule;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {
    Schedule createSchedule(ScheduleCreationRequest request);

    ScheduleResponse toScheduleResponse(Schedule schedule);
}
