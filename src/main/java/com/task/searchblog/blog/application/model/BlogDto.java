package com.task.searchblog.blog.application.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BlogDto {
    String blogname;
    String contents;
    String datetime;
    String thumbnail;
    String title;
    String url;
}
