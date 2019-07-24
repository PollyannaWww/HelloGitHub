package com.baizhi.service;

import com.baizhi.dao.ArticleDao;
import com.baizhi.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public interface ArticleService {
    Map<String,Object> findAllArticles(Integer page,Integer rows,String sidx,String sord);
    void addArticle(Article article);
    void editArticle(Article article);
    void deleteArticle(Article article);
    List<Article> showArticleByMaterId(String uid);
    List<Article> showAllArticle();
}
