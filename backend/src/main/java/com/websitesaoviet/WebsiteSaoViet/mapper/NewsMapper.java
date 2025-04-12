package com.websitesaoviet.WebsiteSaoViet.mapper;

import com.websitesaoviet.WebsiteSaoViet.dto.request.admin.NewsCreationRequest;
import com.websitesaoviet.WebsiteSaoViet.dto.request.admin.NewsUpdateRequest;
import com.websitesaoviet.WebsiteSaoViet.dto.response.common.NewsResponse;
import com.websitesaoviet.WebsiteSaoViet.entity.News;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface NewsMapper {
    News createNews(NewsCreationRequest request);

    NewsResponse toNewsResponse(News news);

    void updateNews(@MappingTarget News news, NewsUpdateRequest request);
}
