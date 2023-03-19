package com.task.searchblog.blog.adapter.out.web.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@ToString
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BlogResponse {
    String blogname;
    String contents;
    String datetime;
    String thumbnail;
    String title;
    String url;
}
