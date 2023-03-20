package com.task.searchblog.blog.adapter.out.web;

import com.task.searchblog.blog.adapter.out.web.client.KakaoSearchBlogClient;
import com.task.searchblog.blog.adapter.out.web.model.KakaoSearchBlogResponse;
import com.task.searchblog.blog.application.model.SearchBlogDto;
import com.task.searchblog.common.constant.SearchBlogSortType;
import com.task.searchblog.common.exception.KakaoClientException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoSearchBlogAdapter implements SearchBlogAdapter {
    private final ObjectProvider<KakaoSearchBlogClient> kakaoSearchBlogClientProvider;
    private final SearchBlogAdapter naverSearchBlogAdapter;

    public SearchBlogDto searchBlog(String query, SearchBlogSortType sort, int page, int size) {
        try {
            KakaoSearchBlogResponse response = getClient().searchBlog(query, sort.getKakaoCode(), page, size);
            return response.toDto(page, size);
        } catch (KakaoClientException e) {
            log.error("[Kakao Client Error] {}", e.getMessage());
            return naverSearchBlogAdapter.searchBlog(query, sort, page, size);
        }
    }

    private KakaoSearchBlogClient getClient() { return kakaoSearchBlogClientProvider.getIfAvailable(); }
}
