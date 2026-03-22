package ru.articles.service;

import ru.articles.model.Article;
import ru.articles.model.Word;
import ru.articles.store.Store;

public interface ArticleService {
    void generate(Store<Word> wordStore, int count, Store<Article> articleStore);
}
