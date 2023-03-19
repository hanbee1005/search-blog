package com.task.searchblog.blog.adapter.out.web;

import com.task.searchblog.blog.adapter.out.web.model.KakaoSearchBlogRequest;
import com.task.searchblog.blog.adapter.out.web.model.KakaoSearchBlogResponse;
import com.task.searchblog.common.config.KakaoFeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        name = "kakao-search-blog-client",
        url = "${external.api.kakao-search}/blog",
        configuration = {KakaoFeignClientConfig.class}
)
public interface KakaoSearchBlogClient {
    @GetMapping
    KakaoSearchBlogResponse searchBlog(@SpringQueryMap KakaoSearchBlogRequest request);
}
