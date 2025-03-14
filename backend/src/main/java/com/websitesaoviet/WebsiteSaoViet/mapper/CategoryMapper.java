package com.websitesaoviet.WebsiteSaoViet.mapper;

import com.websitesaoviet.WebsiteSaoViet.dto.response.user.CategoryResponse;
import com.websitesaoviet.WebsiteSaoViet.entity.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    List<CategoryResponse> toTourCategoryResponse(List<Category> categorysList);
}
