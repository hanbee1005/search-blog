package com.task.searchblog.term.adapter.out.persistance;

import com.task.searchblog.term.domain.Term;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
class TermRedisRepositoryTest {

    @Autowired TermRedisRepository termRedisRepository;

    private final List<String> keys = List.of("인테리어", "테스트코드");

    @BeforeEach
    public void init() {
        termRedisRepository.saveAll(keys.stream().map(Term::create).collect(Collectors.toList()));
    }

    @Test
    @DisplayName("redis에 키워드 저장")
    public void save() {
        // given
        String key = "은행";
        Term term = Term.create(key);

        // when
        Term savedTerm = termRedisRepository.save(term);

        // then
        Optional<Term> findTerm = termRedisRepository.findById(key);
        assertThat(findTerm.isPresent()).isTrue();
        assertThat(findTerm.get().getTerm()).isEqualTo(savedTerm.getTerm());
        assertThat(findTerm.get().getCount()).isEqualTo(savedTerm.getCount());
    }

    @Test
    @DisplayName("검색 수 증가")
    public void updateCount() {
        // given
        String key = keys.get(0);
        Term term = termRedisRepository.findById(key).orElseThrow();

        // when
        term.updateCount();
        termRedisRepository.save(term);

        // then
        Optional<Term> findTerm = termRedisRepository.findById(key);
        assertThat(findTerm.isPresent()).isTrue();
        assertThat(findTerm.get().getCount()).isEqualTo(term.getCount());
    }

}