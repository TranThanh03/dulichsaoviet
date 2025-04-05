package com.websitesaoviet.WebsiteSaoViet.mapper;

import com.websitesaoviet.WebsiteSaoViet.dto.response.common.BookingResponse;
import com.websitesaoviet.WebsiteSaoViet.entity.Booking;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    BookingResponse toBookingResponse(Booking booking);
}
