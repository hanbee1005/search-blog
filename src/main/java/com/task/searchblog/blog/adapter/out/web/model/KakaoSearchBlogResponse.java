package com.task.searchblog.blog.adapter.out.web.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.util.List;

@ToString
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class KakaoSearchBlogResponse {
    MetaResponse meta;
    List<BlogResponse> documents;
}