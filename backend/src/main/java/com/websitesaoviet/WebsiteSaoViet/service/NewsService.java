package com.websitesaoviet.WebsiteSaoViet.service;

import com.websitesaoviet.WebsiteSaoViet.dto.request.admin.NewsCreationRequest;
import com.websitesaoviet.WebsiteSaoViet.dto.request.admin.NewsUpdateRequest;
import com.websitesaoviet.WebsiteSaoViet.dto.response.common.NewsResponse;
import com.websitesaoviet.WebsiteSaoViet.entity.News;
import com.websitesaoviet.WebsiteSaoViet.exception.AppException;
import com.websitesaoviet.WebsiteSaoViet.exception.ErrorCode;
import com.websitesaoviet.WebsiteSaoViet.mapper.NewsMapper;
import com.websitesaoviet.WebsiteSaoViet.repository.NewsRepository;
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
public class NewsService {
    NewsRepository newsRepository;
    NewsMapper newsMapper;

    public NewsResponse createNews(NewsCreationRequest request) {
        News news = newsMapper.createNews(request);

        if (request.getType().equals("featured")) {
            news.setType("Nổi bật");
        } else {
            news.setType("Thường");
        }

        news.setViewCount(0);
        news.setTimeStamp(LocalDateTime.now());

        return newsMapper.toNewsResponse(newsRepository.save(news));
    }

    public Page<NewsResponse> getNewss(Pageable pageable) {
        return newsRepository.findAll(pageable).map(newsMapper::toNewsResponse);
    }

    public NewsResponse getNewsById(String id) {
        var news = newsRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NEWS_NOT_EXITED));

        int viewCount = news.getViewCount();

        news.setViewCount(++viewCount);
        newsRepository.save(news);

        return newsMapper.toNewsResponse(news);
    }

    public NewsResponse updateNews(String id, NewsUpdateRequest request) {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NEWS_NOT_EXITED));

        newsMapper.updateNews(news, request);

        if (request.getType().equals("featured")) {
            news.setType("Nổi bật");
        } else {
            news.setType("Thường");
        }

        news.setTimeStamp(LocalDateTime.now());

        return newsMapper.toNewsResponse(newsRepository.save(news));
    }

    public void deleteNews(String id) {
        if (!newsRepository.existsById(id)) {
            throw new AppException(ErrorCode.NEWS_NOT_EXITED);
        }

        newsRepository.deleteById(id);
    }
}