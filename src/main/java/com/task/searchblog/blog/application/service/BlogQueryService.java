package com.task.searchblog.blog.application.service;

import com.task.searchblog.blog.adapter.in.web.model.SearchBlogRequest;
import com.task.searchblog.blog.adapter.out.web.SearchBlogAdapter;
import com.task.searchblog.blog.application.model.SearchBlogDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BlogQueryService {
    private final SearchBlogAdapter searchBlogAdapter;

    public SearchBlogDto searchBlog(SearchBlogRequest request) {
        return searchBlogAdapter.searchBlog(request.getQuery(), request.getSort(), request.getPage(), request.getSize());
    }
}
