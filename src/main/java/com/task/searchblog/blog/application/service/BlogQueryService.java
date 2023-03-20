package com.task.searchblog.blog.application.service;

import com.task.searchblog.blog.adapter.in.web.model.SearchBlogRequest;
import com.task.searchblog.blog.adapter.out.web.SearchBlogAdapter;
import com.task.searchblog.blog.adapter.out.web.model.KakaoSearchBlogResponse;
import com.task.searchblog.blog.adapter.out.web.model.NaverSearchBlogResponse;
import com.task.searchblog.blog.adapter.out.web.model.SearchBlogResponse;
import com.task.searchblog.blog.application.model.SearchBlogDto;
import com.task.searchblog.common.exception.KakaoClientException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BlogQueryService {
    private final SearchBlogAdapter<KakaoSearchBlogResponse> kakaoSearchBlogResponseAdapter;
    private final SearchBlogAdapter<NaverSearchBlogResponse> naverSearchBlogResponseAdapter;

    public SearchBlogDto searchBlog(SearchBlogRequest request) {
        try {
            SearchBlogResponse response = kakaoSearchBlogResponseAdapter.searchBlog(request.getQuery(), request.getSort(), request.getPage(), request.getSize());
            return response.toDto(request.getPage(), request.getSize());
        } catch (KakaoClientException e) {
            SearchBlogResponse response = naverSearchBlogResponseAdapter.searchBlog(request.getQuery(), request.getSort(), request.getPage(), request.getSize());
            return response.toDto(request.getPage(), request.getSize());
        }
    }
}
