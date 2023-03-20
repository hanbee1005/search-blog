package com.task.searchblog.blog.adapter.out.web;

import com.task.searchblog.blog.adapter.out.web.model.KakaoSearchBlogRequest;
import com.task.searchblog.blog.adapter.out.web.model.KakaoSearchBlogResponse;
import com.task.searchblog.common.config.FeignClientConfig;
import com.task.searchblog.common.constant.HttpHeaderConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(
        name = "kakao-search-blog-client",
        url = "${external.api.kakao-search}/blog",
        configuration = {FeignClientConfig.class}
)
public interface KakaoSearchBlogClient {
    @GetMapping
    KakaoSearchBlogResponse searchBlog(@RequestHeader(HttpHeaderConstants.AUTHORIZATION) String apiKey,
                                       @SpringQueryMap KakaoSearchBlogRequest request);
}
