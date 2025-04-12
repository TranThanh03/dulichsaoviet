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
    BookingService bookingService;

    public ReviewResponse createReview(String bookingId, String customerId, ReviewCreationRequest request) {
        var booking = bookingService.getBookingReviewValid(bookingId, customerId, true);

        if (booking == null) {
            throw new AppException(ErrorCode.REVIEW_INVALID);
        }

        bookingService.updateBookingByReview(bookingId, false);

        Review review = reviewMapper.createReview(request);

        review.setCustomerId(customerId);
        review.setTourId(booking.getTourId());
        review.setTimeStamp(LocalDateTime.now());

        return reviewMapper.toReviewResponse(reviewRepository.save(review));
    }

    public Page<ReviewResponse> getReviews(Pageable pageable) {
        return reviewRepository.findAll(pageable).map(reviewMapper::toReviewResponse);
    }

    public ReviewResponse getReviewById(String id) {
        return reviewMapper.toReviewResponse(reviewRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.TOUR_NOT_EXITED)));
    }

    public ReviewResponse updateReview(String id, String customerId, ReviewUpdateRequest request) {
        Review review = reviewRepository.findReviewByIdAndCustomerId(id, customerId);

        if (review == null) {
            throw new AppException(ErrorCode.REVIEW_NOT_EXITED);
        }

        reviewMapper.updateReview(review, request);

        review.setTimeStamp(LocalDateTime.now());

        return reviewMapper.toReviewResponse(reviewRepository.save(review));
    }

    public void deleteReview(String id, String customerId) {
        if (!reviewRepository.existsReviewByIdAndCustomerId(id, customerId)) {
            throw new AppException(ErrorCode.REVIEW_NOT_EXITED);
        }

        reviewRepository.deleteById(id);
    }
}