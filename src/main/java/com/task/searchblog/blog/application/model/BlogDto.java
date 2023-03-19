package com.task.searchblog.blog.application.model;

import com.task.searchblog.blog.adapter.out.web.model.BlogResponse;
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

    public static BlogDto of(BlogResponse response) {
        return BlogDto.builder()
                .blogname(response.getBlogname())
                .contents(response.getContents())
                .datetime(response.getDatetime())
                .thumbnail(response.getThumbnail())
                .title(response.getTitle())
                .url(response.getUrl())
                .build();
    }
}
