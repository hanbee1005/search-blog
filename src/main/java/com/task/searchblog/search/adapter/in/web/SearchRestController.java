package com.task.searchblog.search.adapter.in.web;

import com.task.searchblog.common.model.CommonResponse;
import com.task.searchblog.search.adapter.in.web.model.SearchKeywordResponse;
import com.task.searchblog.search.application.service.TermQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SearchRestController {
    private final TermQueryService termQueryService;

    @GetMapping("/search/keyword")
    public CommonResponse<SearchKeywordResponse> searchTopKeyword(@RequestParam(required = false, defaultValue = "10") Integer limit) {
        return CommonResponse.ok(SearchKeywordResponse.of(termQueryService.searchTopKeyword(limit)));
    }
}
