package com.task.searchblog.blog.adapter.out.web;

import com.task.searchblog.blog.adapter.out.web.model.KakaoSearchBlogRequest;
import com.task.searchblog.blog.adapter.out.web.model.KakaoSearchBlogResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KakaoSearchBlogAdapter {

    @Value("${external.auth.kakao}")
    private String apiKey;
    private final ObjectProvider<KakaoSearchBlogClient> kakaoSearchBlogClientProvider;

    public KakaoSearchBlogResponse searchBlog(KakaoSearchBlogRequest request) {
        return getClient().searchBlog(apiKey, request);
    }

    private KakaoSearchBlogClient getClient() { return kakaoSearchBlogClientProvider.getIfAvailable(); }
}
