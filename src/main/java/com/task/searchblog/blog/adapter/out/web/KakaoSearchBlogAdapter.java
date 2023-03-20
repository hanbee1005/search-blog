package com.task.searchblog.blog.adapter.out.web;

import com.task.searchblog.blog.adapter.out.web.client.KakaoSearchBlogClient;
import com.task.searchblog.blog.adapter.out.web.model.KakaoSearchBlogResponse;
import com.task.searchblog.common.constant.SearchBlogSortType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KakaoSearchBlogAdapter implements SearchBlogAdapter<KakaoSearchBlogResponse> {
    private final ObjectProvider<KakaoSearchBlogClient> kakaoSearchBlogClientProvider;

    public KakaoSearchBlogResponse searchBlog(String query, SearchBlogSortType sort, int page, int size) {
        return getClient().searchBlog(query, sort.getKakaoCode(), page, size);
    }

    private KakaoSearchBlogClient getClient() { return kakaoSearchBlogClientProvider.getIfAvailable(); }
}
