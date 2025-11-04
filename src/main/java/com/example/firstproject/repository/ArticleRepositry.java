package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ArticleRepositry extends CrudRepository<Article,Long> {

    @Override
    ArrayList<Article> findAll();
}
