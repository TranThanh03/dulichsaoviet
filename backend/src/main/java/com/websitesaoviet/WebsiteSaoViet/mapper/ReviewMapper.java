package com.websitesaoviet.WebsiteSaoViet.mapper;

import com.websitesaoviet.WebsiteSaoViet.dto.request.user.ReviewCreationRequest;
import com.websitesaoviet.WebsiteSaoViet.dto.request.user.ReviewUpdateRequest;
import com.websitesaoviet.WebsiteSaoViet.dto.response.user.ReviewResponse;
import com.websitesaoviet.WebsiteSaoViet.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    Review createReview(ReviewCreationRequest request);

    ReviewResponse toReviewResponse(Review review);

    List<ReviewResponse> toReviewListResponse(List<Review> review);

    void updateReview(@MappingTarget Review review, ReviewUpdateRequest request);
}
