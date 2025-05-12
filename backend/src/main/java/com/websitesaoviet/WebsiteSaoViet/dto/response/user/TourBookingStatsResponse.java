package com.websitesaoviet.WebsiteSaoViet.dto.response.user;

import com.websitesaoviet.WebsiteSaoViet.entity.Tour;

public interface TourBookingStatsResponse {
    Tour getTour();
    Long getBookingCount();
}