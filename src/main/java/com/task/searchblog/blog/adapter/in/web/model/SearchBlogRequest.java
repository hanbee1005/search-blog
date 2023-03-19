package com.task.searchblog.blog.adapter.in.web.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter @Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SearchBlogRequest {
    @NotBlank
    String query;

    String sort;

    @Min(1) @Max(50)
    Integer page = 1;

    @Min(1) @Max(50)
    Integer size = 10;
}
