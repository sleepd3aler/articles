package ru.articles.service.generator;

import ru.articles.model.Article;
import ru.articles.model.Word;

import java.util.List;

public interface ArticleGenerator {
    Article generate(List<Word> words);
}
