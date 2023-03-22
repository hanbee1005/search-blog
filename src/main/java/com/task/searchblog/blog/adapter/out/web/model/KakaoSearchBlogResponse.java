package com.task.searchblog.blog.adapter.out.web.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.task.searchblog.blog.application.model.BlogDto;
import com.task.searchblog.blog.application.model.SearchBlogDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.stream.Collectors;

@ToString
@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class KakaoSearchBlogResponse implements SearchBlogResponse {
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

        public BlogDto toDto() {
            return BlogDto.builder()
                    .blogname(blogname)
                    .contents(contents)
                    .datetime(datetime)
                    .thumbnail(thumbnail)
                    .title(title)
                    .url(url)
                    .build();
        }
    }

    public SearchBlogDto toDto(int page) {
        return SearchBlogDto.builder()
                .page(page)
                .size(documents.size())
                .hasNext(!meta.isEnd)
                .blogs(documents.stream().map(Document::toDto).collect(Collectors.toList()))
                .build();
    }
}
