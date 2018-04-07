package com.shri.demo.rest;

import com.shri.demo.entity.Question;
import com.shri.demo.repository.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionRestService {

    @Autowired
    QuestionRepo questionRepo;

    @Autowired
    ElasticsearchTemplate template;


    //TODO call the service facade insted of calling repo directly.

    @PostMapping(value = "/", consumes ="application/json")
    public Question insert(@RequestBody Question question) {
        return questionRepo.save(question);
    }

//    @PutMapping(value = "/question/{id}", consumes ="application/json")
//    public Question update(Long id, Question question) {
//        return questionRepo.;
//    }

    @GetMapping(value = "/_search/title/{text}")
    public List<Question> searchByTitle(@PathVariable final String text) {
        return questionRepo.findByTitle(text);
    }


    @GetMapping(value = "/_search/text/{text}")
    public List<Question> searchByText(@PathVariable final String text) {
        return questionRepo.findByText(text);
    }


    @GetMapping(value = "/_search/user/{user}")
    public List<Question> searchByUser(@PathVariable final String user) {
        return questionRepo.findByUser(user);
    }


    @GetMapping(value = "/_search/all")
    public List<Question> searchAll() {
        List<Question> usersList = new ArrayList<>();
        Iterable<Question> userses = questionRepo.findAll();
        userses.forEach(usersList::add);
        return usersList;
    }

    @DeleteMapping(value = "/{id}")
    public void delete(Long id) {
        questionRepo.deleteById(id);
    }

    @GetMapping(value = "/_delete_index")
    public boolean delete() {
        return template.deleteIndex(Question.class);
    }


}
