package com.task.searchblog.blog.application.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SearchBlogDto {
    int page;
    int size;
    boolean hasNext;

    List<BlogDto> blogs;
}
