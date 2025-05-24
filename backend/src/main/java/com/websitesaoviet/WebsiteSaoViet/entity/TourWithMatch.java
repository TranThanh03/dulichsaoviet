package com.websitesaoviet.WebsiteSaoViet.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TourWithMatch {
    private Tour tour;
    private Object[] rawTour;
    private int matchCount;

    public TourWithMatch(Tour tour, int matchCount) {
        this.tour = tour;
        this.matchCount = matchCount;
    }

    public TourWithMatch(Object[] rawTour, int matchCount) {
        this.rawTour = rawTour;
        this.matchCount = matchCount;
    }
}