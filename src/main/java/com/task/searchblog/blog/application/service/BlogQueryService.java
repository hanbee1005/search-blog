package com.task.searchblog.blog.application.service;

import com.task.searchblog.blog.adapter.in.web.model.SearchBlogRequest;
import com.task.searchblog.blog.adapter.out.web.KakaoSearchBlogAdapter;
import com.task.searchblog.blog.adapter.out.web.model.KakaoSearchBlogRequest;
import com.task.searchblog.blog.adapter.out.web.model.KakaoSearchBlogResponse;
import com.task.searchblog.blog.application.model.SearchBlogQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BlogQueryService {
    private final KakaoSearchBlogAdapter kakaoSearchBlogAdapter;

    public SearchBlogQuery searchBlog(SearchBlogRequest request) {
        KakaoSearchBlogResponse kakaoSearchBlogResponse = kakaoSearchBlogAdapter.searchBlog(KakaoSearchBlogRequest.of(request));
        return SearchBlogQuery.of(request.getPage(), request.getSize(), kakaoSearchBlogResponse);
    }
}
