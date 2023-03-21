package com.task.searchblog.term.adapter.in.web;

import com.task.searchblog.common.CommonControllerTest;
import com.task.searchblog.mock.MockModel;
import com.task.searchblog.term.application.model.TermDto;
import com.task.searchblog.term.application.service.TermQueryService;
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

import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TermRestController.class)
class TermRestControllerTest extends CommonControllerTest {
    @MockBean
    TermQueryService termQueryService;

    @Test
    @DisplayName("인기 검색어 조회")
    public void searchTopTenTerms() throws Exception {
        // given
        String documentPath = "search-terms";
        String url = API_V1 + "/terms";

        int limit = 10;
        List<TermDto> response = MockModel.getTermDto(limit);

        given(termQueryService.searchTopTerm(anyInt())).willReturn(response);

        // when
        ResultActions resultActions = mockMvc.perform(RestdocsUtils.getWrapper(url));

        // then
        resultActions.andExpect(status().isOk())
                .andDo(MockMvcRestDocumentation.document(documentPath,
                        RestdocsUtils.getDocumentRequest(),
                        RestdocsUtils.getDocumentResponse(),
                        RequestDocumentation.requestParameters(
                                RestdocsUtils.parameterWithNameAndType("limit", "number").description("최대 검색 결과 수").optional()
                        ),
                        RestdocsUtils.commonResponseFields(false,
                                PayloadDocumentation.fieldWithPath("total").type(JsonFieldType.NUMBER).description("전체 결과 수"),
                                PayloadDocumentation.fieldWithPath("terms").type(JsonFieldType.ARRAY).description("인기 검색어 목록"),
                                PayloadDocumentation.fieldWithPath("terms[].term").type(JsonFieldType.STRING).description("검색어"),
                                PayloadDocumentation.fieldWithPath("terms[].count").type(JsonFieldType.NUMBER).description("검색된 수")
                        )
                )).andDo(print());
    }
}