package com.websitesaoviet.WebsiteSaoViet.mapper;

import com.websitesaoviet.WebsiteSaoViet.dto.request.user.ReviewCreationRequest;
import com.websitesaoviet.WebsiteSaoViet.dto.response.user.ReviewResponse;
import com.websitesaoviet.WebsiteSaoViet.entity.Review;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    Review createReview(ReviewCreationRequest request);

    ReviewResponse toReviewResponse(Review review);
}
