package com.task.searchblog.blog.adapter.out.web;

import com.task.searchblog.common.constant.SearchBlogSortType;

public interface SearchBlogAdapter<T> {
    T searchBlog(String query, SearchBlogSortType sort, int page, int size);
}
