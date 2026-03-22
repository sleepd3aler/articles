package ru.articles.service.generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import ru.articles.model.Article;
import ru.articles.model.Word;

public class RandomArticleGenerator implements ArticleGenerator {
    @Override
    public Article generate(List<Word> words) {
        var wordsCopy = new ArrayList<>(words);
        Collections.shuffle(words);
        var content = wordsCopy.stream()
                .map(Word::getValue)
                .collect(Collectors.joining(" "));
        return new Article(content);
    }
}