package com.task.searchblog.blog.adapter.in.web.model;

import com.task.searchblog.blog.application.model.BlogDto;
import com.task.searchblog.blog.application.model.SearchBlogQuery;
import com.task.searchblog.common.model.SlicePageResponse;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SearchBlogResponse extends SlicePageResponse {
    List<BlogDto> blogs;

    private SearchBlogResponse(int page, int size, boolean hasNext, List<BlogDto> blogs) {
        super(page, size, hasNext);
        this.blogs = blogs;
    }

    public static SearchBlogResponse of(SearchBlogQuery query) {
        return new SearchBlogResponse(query.getPage(), query.getSize(), query.isHasNext(), query.getBlogs());
    }
}
