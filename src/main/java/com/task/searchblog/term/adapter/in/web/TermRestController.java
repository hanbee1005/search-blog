package com.task.searchblog.term.adapter.in.web;

import com.task.searchblog.common.model.CommonResponse;
import com.task.searchblog.term.adapter.in.web.model.SearchTermResponse;
import com.task.searchblog.term.application.service.TermQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TermRestController {
    private final TermQueryService termQueryService;

    @GetMapping("/v1/terms")
    public CommonResponse<SearchTermResponse> searchTopTerm(@RequestParam(required = false, defaultValue = "10") Integer limit) {
        return CommonResponse.ok(SearchTermResponse.of(termQueryService.searchTopTerm(limit)));
    }
}
