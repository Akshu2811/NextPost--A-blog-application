package com.scaler.blogapp.articles;

import com.scaler.blogapp.articles.dtos.CreateArticleRequest;
import com.scaler.blogapp.articles.dtos.UpdateArticleRequest;
import com.scaler.blogapp.users.UsersRepository;
import com.scaler.blogapp.users.UsersService;
import org.springframework.stereotype.Service;

@Service
public class ArticlesService {

    private ArticleRepository articleRepository;
    private UsersRepository usersRepository;

    public ArticlesService(ArticleRepository articleRepository,UsersRepository usersRepository) {
        this.articleRepository = articleRepository;
        this.usersRepository = usersRepository;
    }

    public Iterable<ArticleEntity> getAllArticles() {
        return articleRepository.findAll();
    }

    public ArticleEntity getArticleBySlug(String slug) {
        var article = articleRepository.findBySlug(slug);
        if(article == null){
            throw new ArticleNotFoundException(slug);
        }
        return article;
    }
    public ArticleEntity createArticle(CreateArticleRequest a,Long authorId){
        var author = usersRepository.findById(authorId).orElseThrow(() -> new UsersService.UserNotFoundException(authorId));
        return articleRepository.save(ArticleEntity.builder()
                .title(a.getTitle())
                // TODO: CREATE A PROPER SLUGIFICATION FUNCTION
                .slug(a.getTitle().toLowerCase().replaceAll("\\s+", "-"))
                .body(a.getBody())
                .subtitle(a.getSubtitle())
                        .author(author)
                .build());
    }

    public ArticleEntity updateArticle(Long articleId, UpdateArticleRequest a){
        var article = articleRepository.findById(articleId).orElseThrow(() -> new ArticleNotFoundException(articleId));
        if(a.getTitle()!=null) {
            article.setTitle(a.getTitle());
            article.setSlug(a.getTitle().toLowerCase().replaceAll("\\s+", "-"));
        }
        if(a.getBody()!=null) {article.setBody(a.getBody());}
        if(a.getSubtitle()!=null) {article.setSubtitle(a.getSubtitle());}
        return articleRepository.save(article);
    }

    static class ArticleNotFoundException extends IllegalArgumentException{
        public ArticleNotFoundException(String slug) {
            super("Slug "+slug+" not found");
        }
        public ArticleNotFoundException(Long articleId) {
            super("Article with id: " + articleId + " not found");
        }
    }
}
