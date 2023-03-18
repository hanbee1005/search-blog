package com.task.searchblog.blog.adapter.out.web.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class KakaoSearchBlogRequest {
    String query;
    String sort;
    int page;
    int size;
}
