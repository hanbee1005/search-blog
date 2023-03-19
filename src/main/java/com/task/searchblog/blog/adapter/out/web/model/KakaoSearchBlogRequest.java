package com.task.searchblog.blog.adapter.out.web.model;

import com.task.searchblog.blog.adapter.in.web.model.SearchBlogRequest;
import com.task.searchblog.common.constant.SearchBlogSortType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class KakaoSearchBlogRequest {
    String query;
    SearchBlogSortType sort;
    int page;
    int size;

    public static KakaoSearchBlogRequest of(SearchBlogRequest request) {
        return new KakaoSearchBlogRequest(request.getQuery(), request.getSort(), request.getPage(), request.getSize());
    }
}