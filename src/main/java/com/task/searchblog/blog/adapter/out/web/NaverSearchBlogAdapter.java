package com.task.searchblog.blog.adapter.out.web;

import com.task.searchblog.blog.adapter.out.web.client.NaverSearchBlogClient;
import com.task.searchblog.blog.adapter.out.web.model.NaverSearchBlogResponse;
import com.task.searchblog.blog.application.model.SearchBlogDto;
import com.task.searchblog.common.constant.SearchBlogSortType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NaverSearchBlogAdapter implements SearchBlogAdapter {
    private final ObjectProvider<NaverSearchBlogClient> naverSearchBlogClientProvider;

    public SearchBlogDto searchBlog(String query, SearchBlogSortType sort, int page, int size) {
        NaverSearchBlogResponse response = getClient().searchBlog(query, sort.getNaverCode(), page, size);
        return response.toDto(page, size);
    }

    private NaverSearchBlogClient getClient() { return naverSearchBlogClientProvider.getIfAvailable(); }
}
