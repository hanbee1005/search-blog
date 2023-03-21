package com.task.searchblog.blog.adapter.in.web;
import com.task.searchblog.blog.application.model.SearchBlogDto;
import com.task.searchblog.blog.application.service.BlogQueryService;
import com.task.searchblog.common.CommonControllerTest;
import com.task.searchblog.mock.MockModel;
import com.task.searchblog.term.application.service.TermLockFacade;
import com.task.searchblog.util.MultiValueMapConverter;
import com.task.searchblog.util.RestdocsUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.restdocs.request.RequestDocumentation;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.MultiValueMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BlogRestController.class)
class BlogRestControllerTest extends CommonControllerTest {
    @MockBean
    BlogQueryService blogQueryService;
    @MockBean
    TermLockFacade termLockFacade;

    @Test
    @DisplayName("블로그 목록 조회")
    public void searchBlogs() throws Exception {
        // given
        String documentPath = "search-blogs";
        String url = API_V1 + "/blogs";

        int page = 1;
        int size = 10;

        MultiValueMap<String, String> request = MultiValueMapConverter.convert(objectMapper, MockModel.getSearchBlogRequest());
        SearchBlogDto response = MockModel.getSearchBlogDto(page, size);

        given(blogQueryService.searchBlog(any())).willReturn(response);

        // when
        ResultActions resultActions = mockMvc.perform(RestdocsUtils.getWrapper(url).params(request));

        // then
        resultActions.andExpect(status().isOk())
                .andDo(MockMvcRestDocumentation.document(documentPath,
                        RestdocsUtils.getDocumentRequest(),
                        RestdocsUtils.getDocumentResponse(),
                        RequestDocumentation.requestParameters(
                                RestdocsUtils.parameterWithNameAndType("query", "String").description("키워드"),
                                RestdocsUtils.parameterWithNameAndType("sort", "String").description("정렬조건 {\"ACCURACY\": \"정확도순\", \"RECENCY\": \"최신순\"}").optional(),
                                RestdocsUtils.parameterWithNameAndType("page", "number").description("결과 페이지 번호").optional(),
                                RestdocsUtils.parameterWithNameAndType("size", "number").description("한 페이지에 보여질 문서 수").optional()
                        ),
                        RestdocsUtils.commonResponseFields(false,
                                PayloadDocumentation.fieldWithPath("page").type(JsonFieldType.NUMBER).description("현재 페이지 수"),
                                PayloadDocumentation.fieldWithPath("size").type(JsonFieldType.NUMBER).description("현재 페이지에 보여지는 문서 수"),
                                PayloadDocumentation.fieldWithPath("hasNext").type(JsonFieldType.BOOLEAN).description("다음 페이지 여부"),
                                PayloadDocumentation.fieldWithPath("blogs").type(JsonFieldType.ARRAY).description("블로그 목록"),
                                PayloadDocumentation.fieldWithPath("blogs[].blogname").type(JsonFieldType.STRING).description("블로그의 이름"),
                                PayloadDocumentation.fieldWithPath("blogs[].contents").type(JsonFieldType.STRING).description("블로그 글 요약"),
                                PayloadDocumentation.fieldWithPath("blogs[].datetime").type(JsonFieldType.STRING).description("블로그 글 작성시간"),
                                PayloadDocumentation.fieldWithPath("blogs[].thumbnail").type(JsonFieldType.STRING).description("검색 시스템에서 추출한 대표 미리보기 이미지 URL"),
                                PayloadDocumentation.fieldWithPath("blogs[].title").type(JsonFieldType.STRING).description("블로그 글 제목"),
                                PayloadDocumentation.fieldWithPath("blogs[].url").type(JsonFieldType.STRING).description("블로그 글 URL")
                        )
                )).andDo(print());
    }
}