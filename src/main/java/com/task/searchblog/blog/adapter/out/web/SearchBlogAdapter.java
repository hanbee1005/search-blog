package com.task.searchblog.blog.adapter.out.web;

import com.task.searchblog.blog.application.model.SearchBlogDto;
import com.task.searchblog.common.constant.SearchBlogSortType;

public interface SearchBlogAdapter {
    SearchBlogDto searchBlog(String query, SearchBlogSortType sort, int page, int size);
}
