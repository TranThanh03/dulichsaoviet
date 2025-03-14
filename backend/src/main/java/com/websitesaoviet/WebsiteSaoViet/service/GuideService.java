package com.websitesaoviet.WebsiteSaoViet.service;

import com.websitesaoviet.WebsiteSaoViet.dto.request.GuideCreationRequest;
import com.websitesaoviet.WebsiteSaoViet.dto.request.GuideUpdateRequest;
import com.websitesaoviet.WebsiteSaoViet.dto.response.admin.AssignmentGuidesResponse;
import com.websitesaoviet.WebsiteSaoViet.dto.response.user.GuideEvaluateResponse;
import com.websitesaoviet.WebsiteSaoViet.dto.response.GuideResponse;
import com.websitesaoviet.WebsiteSaoViet.entity.Guide;
import com.websitesaoviet.WebsiteSaoViet.exception.AppException;
import com.websitesaoviet.WebsiteSaoViet.exception.ErrorCode;
import com.websitesaoviet.WebsiteSaoViet.mapper.GuideMapper;
import com.websitesaoviet.WebsiteSaoViet.repository.GuideRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GuideService {
    GuideRepository guideRepository;
    GuideMapper guideMapper;

    public GuideResponse createGuide(GuideCreationRequest request) {
        Guide guide = guideMapper.createGuide(request);

        guide.setId(String.valueOf(generateNextId()));

        return guideMapper.toGuideResponse(guideRepository.save(guide));
    }

    public Page<GuideResponse> getGuides(Pageable pageable) {
        return guideRepository.findAll(pageable).map(guideMapper::toGuideResponse);
    }

    public List<AssignmentGuidesResponse> getGuidesByAssignment() {
        return guideMapper.toGuidesByAssignment(guideRepository.findAll());
    }

    public List<GuideEvaluateResponse> getGuidesEvaluate() {
        return guideMapper.toGuidesEvaluateResponse(guideRepository.getGuideByEvaluate(5));
    }

    public GuideResponse getGuideById(String id) {
        return  guideMapper.toGuideResponse(guideRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.GUIDE_NOT_EXITED)));
    }

    public GuideResponse updateGuide(String id, GuideUpdateRequest request) {
        Guide guide = guideRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.GUIDE_NOT_EXITED));

        guideMapper.updateGuide(guide, request);

        return guideMapper.toGuideResponse(guideRepository.save(guide));
    }

    public void deleteGuide(String id) {
        if (!guideRepository.existsById(id)) {
            throw new AppException(ErrorCode.GUIDE_NOT_EXITED);
        }

        guideRepository.deleteById(id);
    }

    public long countGuides() {
        return guideRepository.count();
    }

    public String generateNextId() {
        String maxId = guideRepository.findMaxId();
        if (maxId == null) {
            return "HDV25001";
        }

        int currentMax = Integer.parseInt(maxId.substring(3));
        int nextId = currentMax + 1;
        return "HDV" + nextId;
    }
}