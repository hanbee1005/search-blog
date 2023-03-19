package com.task.searchblog.blog.adapter.in.web;

import com.task.searchblog.blog.adapter.in.web.model.SearchBlogRequest;
import com.task.searchblog.blog.adapter.in.web.model.SearchBlogResponse;
import com.task.searchblog.blog.application.service.BlogQueryService;
import com.task.searchblog.common.model.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class BlogRestController {
    private final BlogQueryService blogQueryService;

    @GetMapping("/blog")
    public CommonResponse<SearchBlogResponse> searchBlog(@Valid SearchBlogRequest request) {
        return CommonResponse.ok(SearchBlogResponse.of(blogQueryService.searchBlog(request)));
    }
}
