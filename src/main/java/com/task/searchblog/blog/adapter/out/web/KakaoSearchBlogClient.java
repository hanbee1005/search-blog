package com.task.searchblog.blog.adapter.out.web;

import com.task.searchblog.blog.adapter.out.web.model.KakaoSearchBlogRequest;
import com.task.searchblog.blog.adapter.out.web.model.KakaoSearchBlogResponse;
import com.task.searchblog.common.config.KakaoSearchBlogFeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "kakao-search-blog-client",
        path = "${external.api.kakao-search}/blog",
        configuration = {KakaoSearchBlogFeignClientConfig.class}
)
public interface KakaoSearchBlogClient {
    @GetMapping
    KakaoSearchBlogResponse searchBlog(@RequestParam KakaoSearchBlogRequest request);
}
