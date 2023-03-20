package com.task.searchblog.blog.adapter.out.web.model;

import com.task.searchblog.blog.application.model.SearchBlogDto;

public interface SearchBlogResponse {
    SearchBlogDto toDto(int page, int size);
}
