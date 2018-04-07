package com.shri.demo.repository;

import com.shri.demo.entity.Question;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface QuestionRepo extends ElasticsearchRepository<Question, Long> {

    List<Question> findByUser(String user);

    List<Question> findByText(String text);

    List<Question> findByTitle(String text);
}
