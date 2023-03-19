package com.task.searchblog.blog.application.model;

import com.task.searchblog.blog.adapter.out.web.model.BlogResponse;
import com.task.searchblog.blog.adapter.out.web.model.KakaoSearchBlogResponse;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SearchBlogQuery {
    int page;
    int size;
    boolean hasNext;

    List<BlogDto> blogs;

    @Builder
    private SearchBlogQuery(int page, int size, boolean isEnd, List<BlogResponse> blogs) {
        this.page = page;
        this.size = size;
        this.hasNext = !isEnd;
        this.blogs = blogs.stream().map(BlogDto::of).collect(Collectors.toList());
    }

    public static SearchBlogQuery of(int page, int size, KakaoSearchBlogResponse response) {
        return SearchBlogQuery.builder()
                .page(page)
                .size(size)
                .isEnd(response.getMeta().isEnd())
                .blogs(response.getDocuments())
                .build();
    }
}
