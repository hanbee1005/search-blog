package com.task.searchblog.blog.adapter.out.web.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@ToString
@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MetaResponse {
    @JsonProperty(value = "is_end")
    boolean isEnd;
    @JsonProperty(value = "pageable_count")
    int pageableCount;
    @JsonProperty(value = "total_count")
    int totalCount;
}
