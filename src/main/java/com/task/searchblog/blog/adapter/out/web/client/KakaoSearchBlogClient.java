package com.task.searchblog.blog.adapter.out.web.client;

import com.task.searchblog.blog.adapter.out.web.model.KakaoSearchBlogResponse;
import com.task.searchblog.common.config.KakaoFeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "kakao-search-blog-client",
        url = "${external.api.kakao-search}/blog",
        configuration = {KakaoFeignClientConfig.class}
)
public interface KakaoSearchBlogClient {
    @GetMapping
    KakaoSearchBlogResponse searchBlog(@RequestParam String query,
                                  @RequestParam(required = false) String sort,
                                  @RequestParam(required = false) int page,
                                  @RequestParam(required = false) int size);
}
