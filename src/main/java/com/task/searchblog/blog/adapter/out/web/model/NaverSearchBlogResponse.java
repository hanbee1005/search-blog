package com.task.searchblog.blog.adapter.out.web.model;

import com.task.searchblog.blog.application.model.BlogDto;
import com.task.searchblog.blog.application.model.SearchBlogDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.apache.logging.log4j.util.Strings;

import java.util.List;
import java.util.stream.Collectors;

@ToString
@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NaverSearchBlogResponse implements SearchBlogResponse {
    String lastBuildDate;
    int total;
    int start;
    int display;
    List<Item> items;

    @ToString
    @Getter
    @Builder
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Item {
        String title;
        String link;
        String description;
        String bloggername;
        String bloggerlink;
        String postdate;

        public BlogDto toDto() {
            return BlogDto.builder()
                    .blogname(bloggername)
                    .contents(description)
                    .datetime(postdate)
                    .thumbnail(Strings.EMPTY)
                    .title(title)
                    .url(link)
                    .build();
        }
    }

    public SearchBlogDto toDto(int page, int size) {
        return SearchBlogDto.builder()
                .page(page)
                .size(size)
                .hasNext(isEnd())
                .blogs(items.stream().map(Item::toDto).collect(Collectors.toList()))
                .build();
    }

    private boolean isEnd() {
        return total > (start * display);
    }
}
