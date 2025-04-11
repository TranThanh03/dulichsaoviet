package com.websitesaoviet.WebsiteSaoViet.service;

import com.websitesaoviet.WebsiteSaoViet.dto.request.user.ReviewCreationRequest;
import com.websitesaoviet.WebsiteSaoViet.dto.request.user.ReviewUpdateRequest;
import com.websitesaoviet.WebsiteSaoViet.dto.response.user.ReviewResponse;
import com.websitesaoviet.WebsiteSaoViet.entity.Review;
import com.websitesaoviet.WebsiteSaoViet.exception.AppException;
import com.websitesaoviet.WebsiteSaoViet.exception.ErrorCode;
import com.websitesaoviet.WebsiteSaoViet.mapper.ReviewMapper;
import com.websitesaoviet.WebsiteSaoViet.repository.ReviewRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReviewService {
    ReviewRepository reviewRepository;
    ReviewMapper reviewMapper;

    public ReviewResponse createReview(ReviewCreationRequest request) {
        LocalDateTime currentTime = LocalDateTime.now();

        Review review = reviewMapper.createReview(request);



        return reviewMapper.toReviewResponse(reviewRepository.save(review));
    }

    public Page<ReviewResponse> getReviews(Pageable pageable) {
        return reviewRepository.findAll(pageable).map(reviewMapper::toReviewResponse);
    }

    public ReviewResponse getReviewById(String id) {
        return reviewMapper.toReviewResponse(reviewRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.TOUR_NOT_EXITED)));
    }

    public ReviewResponse updateReview(String id, ReviewUpdateRequest request) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.TOUR_NOT_EXITED));

        LocalDateTime currentTime = LocalDateTime.now();

        reviewMapper.updateReview(review, request);
        review.setTimeStamp(currentTime);

        return reviewMapper.toReviewResponse(reviewRepository.save(review));
    }

    public void deleteReview(String id) {
        if (!reviewRepository.existsById(id)) {
            throw new AppException(ErrorCode.TOUR_NOT_EXITED);
        }

        reviewRepository.deleteById(id);
    }
}