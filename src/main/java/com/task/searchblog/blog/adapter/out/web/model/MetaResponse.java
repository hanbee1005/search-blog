package com.task.searchblog.blog.adapter.out.web.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MetaResponse {
    boolean isEnd;
    int pageableCount;
    int totalCount;
}
