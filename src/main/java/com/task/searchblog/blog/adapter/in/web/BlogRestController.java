package com.task.searchblog.blog.adapter.in.web;

import com.task.searchblog.blog.adapter.in.web.model.SearchBlogRequest;
import com.task.searchblog.blog.adapter.in.web.model.SearchBlogResponse;
import com.task.searchblog.blog.application.service.BlogQueryService;
import com.task.searchblog.common.model.CommonResponse;
import com.task.searchblog.search.application.service.TermLockFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class BlogRestController {
    private final BlogQueryService blogQueryService;
    private final TermLockFacade termLockFacade;

    @GetMapping("/blog")
    public CommonResponse<SearchBlogResponse> searchBlog(@Valid SearchBlogRequest request) {
        SearchBlogResponse searchBlogResponse = SearchBlogResponse.of(blogQueryService.searchBlog(request));
        termLockFacade.increaseTermCount(request.getQuery());
        return CommonResponse.ok(searchBlogResponse);
    }
}
