package ru.articles.service;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.articles.model.Article;
import ru.articles.model.Word;
import ru.articles.service.generator.ArticleGenerator;
import ru.articles.store.Store;

public class SimpleArticleService implements ArticleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleArticleService.class.getSimpleName());

    private final ArticleGenerator articleGenerator;

    public SimpleArticleService(ArticleGenerator articleGenerator) {
        this.articleGenerator = articleGenerator;
    }

    @Override
    public void generate(Store<Word> wordStore, int count, Store<Article> articleStore) {
        LOGGER.info("Геренация статей в количестве {}", count);
        LOGGER.info("Геренация статей в количестве {}", count);
        var words = wordStore.findAll();
        List<WeakReference<Article>> articles = IntStream.iterate(0, i -> i < count, i -> i + 1)
                .peek(i -> LOGGER.info("Сгенерирована статья № {}", i))
                .mapToObj((x) -> new WeakReference<>(articleGenerator.generate(words)))
                .collect(Collectors.toList());
        articles.forEach(art -> {
            Article current = art.get();
            if (current != null) {
                articleStore.save(current);
            }
        });
    }
}