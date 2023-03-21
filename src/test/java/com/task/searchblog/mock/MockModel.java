package com.task.searchblog.mock;

import com.task.searchblog.blog.adapter.in.web.model.SearchBlogRequest;
import com.task.searchblog.blog.adapter.out.web.model.KakaoSearchBlogResponse;
import com.task.searchblog.blog.application.model.BlogDto;
import com.task.searchblog.blog.application.model.SearchBlogDto;
import com.task.searchblog.common.constant.SearchBlogSortType;
import com.task.searchblog.term.application.model.TermDto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MockModel {
    public static SearchBlogRequest getSearchBlogRequest() {
        SearchBlogRequest req = new SearchBlogRequest();
        req.setQuery("테스트 검색어");
        return req;
    }

    public static SearchBlogDto getSearchBlogDto(int page, int size) {
        return SearchBlogDto.builder()
                .page(page)
                .size(size)
                .hasNext(true)
                .blogs(getBlogDtoList(size))
                .build();
    }

    public static List<TermDto> getTermDto(int limit) {
        List<TermDto> resp = new ArrayList<>();

        for (int i = limit; i > 0; i--) {
            resp.add(new TermDto("검색어 " + i, (long) i));
        }

        return resp;
    }

    public static List<BlogDto> getBlogDtoList(int size) {
        List<BlogDto> resp = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            resp.add(BlogDto.builder()
                    .blogname("블로그 이름 " + i)
                    .contents("블로그 글 요약 " + i)
                    .datetime("2023-03-21T12:59:38.000+09:00")
                    .thumbnail("https://sample.image/" + i)
                    .title("블로그 글 제목" + i)
                    .url("http://blog.com/" + i)
                    .build());
        }

        return resp;
    }

    public static SearchBlogRequest getSearchBlogRequest(String query, SearchBlogSortType sort, int page, int size) {
        SearchBlogRequest request = new SearchBlogRequest();
        request.setQuery(query);
        request.setSort(sort);
        request.setPage(page);
        request.setSize(size);

        return request;
    }

    public static KakaoSearchBlogResponse getKakaoSearchBlogResponse(int size) {
        return KakaoSearchBlogResponse.builder()
                .meta(KakaoSearchBlogResponse.Meta.builder().isEnd(false).pageableCount(800).totalCount(10000).build())
                .documents(makeBlogResponseList(size))
                .build();
    }

    private static List<KakaoSearchBlogResponse.Document> makeBlogResponseList(int size) {
        List<KakaoSearchBlogResponse.Document> responses = new ArrayList<>();

        for(int i = 0; i < size; i++) {
            responses.add(KakaoSearchBlogResponse.Document.builder()
                    .blogname(i + " blogname")
                    .contents(i + " contents")
                    .datetime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                    .thumbnail(i + " thumbnail")
                    .title(i + " title")
                    .url(i + " url")
                    .build());
        }

        return responses;
    }
}
