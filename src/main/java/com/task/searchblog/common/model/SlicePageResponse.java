package com.task.searchblog.common.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SlicePageResponse {
    final int page;
    final int size;
    final boolean hasNext;

    public SlicePageResponse(int page, int size, boolean hasNext) {
        this.page = page;
        this.size = size;
        this.hasNext = hasNext;
    }
}
