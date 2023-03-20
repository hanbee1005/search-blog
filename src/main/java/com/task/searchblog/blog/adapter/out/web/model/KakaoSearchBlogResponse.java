package com.task.searchblog.blog.adapter.out.web.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.util.List;

@ToString
@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class KakaoSearchBlogResponse {
    Meta meta;
    List<Document> documents;

    @ToString
    @Getter
    @Builder
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Meta {
        @JsonProperty(value = "is_end")
        boolean isEnd;
        @JsonProperty(value = "pageable_count")
        int pageableCount;
        @JsonProperty(value = "total_count")
        int totalCount;
    }

    @ToString
    @Getter
    @Builder
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Document {
        String blogname;
        String contents;
        String datetime;
        String thumbnail;
        String title;
        String url;
    }
}
