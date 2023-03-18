package com.task.searchblog.blog.adapter.out.web;

import com.task.searchblog.blog.adapter.out.web.model.KakaoSearchBlogRequest;
import com.task.searchblog.blog.adapter.out.web.model.KakaoSearchBlogResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KakaoSearchBlogAdapter {
    private final ObjectProvider<KakaoSearchBlogClient> kakaoSearchBlogClientProvider;

    public KakaoSearchBlogResponse searchBlog(KakaoSearchBlogRequest request) {
        return getClient().searchBlog(request);
    }

    private KakaoSearchBlogClient getClient() { return kakaoSearchBlogClientProvider.getIfAvailable(); }
}
