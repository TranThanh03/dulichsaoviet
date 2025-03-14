package com.websitesaoviet.WebsiteSaoViet.service;

import com.websitesaoviet.WebsiteSaoViet.dto.response.user.CategoryResponse;
import com.websitesaoviet.WebsiteSaoViet.mapper.CategoryMapper;
import com.websitesaoviet.WebsiteSaoViet.repository.CategoryRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryService {
    CategoryRepository categoryRepository;
    CategoryMapper categoryMapper;

    public List<CategoryResponse> getTourCategory() {
        return categoryMapper.toTourCategoryResponse(categoryRepository.findAll());
    }
}