package com.task.searchblog.blog.adapter.out.web.client;

import com.task.searchblog.blog.adapter.out.web.model.NaverSearchBlogResponse;
import com.task.searchblog.common.config.NaverFeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "naver-search-blog-client",
        url = "${external.api.naver-search}/blog.json",
        configuration = {NaverFeignClientConfig.class}
)
public interface NaverSearchBlogClient {
    @GetMapping
    NaverSearchBlogResponse searchBlog(@RequestParam String query,
                                  @RequestParam(required = false) String sort,
                                  @RequestParam(required = false) int start,
                                  @RequestParam(required = false) int display);
}
