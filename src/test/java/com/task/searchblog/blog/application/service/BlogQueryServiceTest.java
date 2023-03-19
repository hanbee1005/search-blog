package com.task.searchblog.blog.application.service;

import com.task.searchblog.blog.adapter.in.web.model.SearchBlogRequest;
import com.task.searchblog.blog.adapter.out.web.KakaoSearchBlogAdapter;
import com.task.searchblog.blog.adapter.out.web.model.KakaoSearchBlogResponse;
import com.task.searchblog.blog.application.model.SearchBlogQuery;
import com.task.searchblog.blog.application.service.mock.MockModel;
import com.task.searchblog.common.constant.SearchBlogSortType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class BlogQueryServiceTest {

    @InjectMocks
    BlogQueryService blogQueryService;

    @Mock
    KakaoSearchBlogAdapter kakaoSearchBlogAdapter;

    @Test
    @DisplayName("블로그 검색하기")
    public void searchBlog() {
        // given
        String query = "인테리어";
        SearchBlogSortType sort = SearchBlogSortType.accuracy;
        int page = 1;
        int size = 10;
        SearchBlogRequest request = MockModel.getSearchBlogRequest(query, sort, page, size);

        KakaoSearchBlogResponse kakaoSearchBlogResponse = MockModel.getKakaoSearchBlogResponse(size);
        given(kakaoSearchBlogAdapter.searchBlog(any())).willReturn(kakaoSearchBlogResponse);

        // when
        SearchBlogQuery response = blogQueryService.searchBlog(request);

        // then
        assertThat(response.getPage()).isEqualTo(page);
        assertThat(response.getSize()).isEqualTo(size);
        assertThat(response.isHasNext()).isTrue();
        assertThat(response.getBlogs().size()).isEqualTo(size);
    }
}