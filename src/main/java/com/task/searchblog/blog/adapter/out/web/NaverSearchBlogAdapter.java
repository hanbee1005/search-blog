package com.task.searchblog.blog.adapter.out.web;

import com.task.searchblog.blog.adapter.out.web.client.NaverSearchBlogClient;
import com.task.searchblog.blog.adapter.out.web.model.NaverSearchBlogResponse;
import com.task.searchblog.common.constant.SearchBlogSortType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NaverSearchBlogAdapter implements SearchBlogAdapter<NaverSearchBlogResponse> {
    private final ObjectProvider<NaverSearchBlogClient> naverSearchBlogClientProvider;

    public NaverSearchBlogResponse searchBlog(String query, SearchBlogSortType sort, int page, int size) {
        return getClient().searchBlog(query, sort.getNaverCode(), page, size);
    }

    private NaverSearchBlogClient getClient() { return naverSearchBlogClientProvider.getIfAvailable(); }
}
