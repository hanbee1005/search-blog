package com.task.searchblog.blog.application.service.mock;

import com.task.searchblog.blog.adapter.in.web.model.SearchBlogRequest;
import com.task.searchblog.blog.adapter.out.web.model.BlogResponse;
import com.task.searchblog.blog.adapter.out.web.model.KakaoSearchBlogResponse;
import com.task.searchblog.blog.adapter.out.web.model.MetaResponse;
import com.task.searchblog.common.constant.SearchBlogSortType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MockModel {
    public static SearchBlogRequest getSearchBlogRequest(String query, SearchBlogSortType sort, int page, int size) {
        SearchBlogRequest request = new SearchBlogRequest();
        request.setQuery(query);
        request.setSort(sort);
        request.setPage(page);
        request.setSize(size);

        return request;
    }

    public static KakaoSearchBlogResponse getKakaoSearchBlogResponse(int size) {
        return KakaoSearchBlogResponse.builder()
                .meta(MetaResponse.builder().isEnd(false).pageableCount(800).totalCount(10000).build())
                .documents(makeBlogResponseList(size))
                .build();
    }

    private static List<BlogResponse> makeBlogResponseList(int size) {
        List<BlogResponse> responses = new ArrayList<>();

        for(int i = 0; i < size; i++) {
            responses.add(BlogResponse.builder()
                    .blogname(i + " blogname")
                    .contents(i + " contents")
                    .datetime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                    .thumbnail(i + " thumbnail")
                    .title(i + " title")
                    .url(i + " url")
                    .build());
        }

        return responses;
    }
}
