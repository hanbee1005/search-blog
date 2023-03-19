package com.task.searchblog.search.domain;

import com.task.searchblog.common.model.AbstractCreatedAtEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Search extends AbstractCreatedAtEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long memberId;  // TODO 추후 회원 관련 기능이 들어오면 연관관계 설정으로 변경 가능
    private String keyword;

    private Search(Long memberId, String keyword) {
        this.memberId = memberId;
        this.keyword = keyword;
    }

    public static Search create(Long memberId, String keyword) {
        return new Search(memberId, keyword);
    }
}
