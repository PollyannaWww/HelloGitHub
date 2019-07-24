package com.baizhi.service;

import com.baizhi.annotation.RedisCache;
import com.baizhi.annotation.RedisCacheHash;
import com.baizhi.dao.ArticleDao;
import com.baizhi.dao.MasterDao;
import com.baizhi.dao.UserDao;
import com.baizhi.entity.Article;
import com.baizhi.entity.Master;
import com.baizhi.entity.User;
import com.baizhi.util.IdWorker;
import io.goeasy.GoEasy;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private UserDao userDao;
    @Autowired
    private MasterDao masterDao;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @RedisCacheHash
    public Map<String, Object> findAllArticles(Integer page, Integer rows, String sidx, String sord) {
        Map<String, Object> map = new HashMap<>();
        Article article = new Article();
        int count = articleDao.selectCount(article);
        Example example = new Example(Article.class);
        example.setOrderByClause(sidx+" "+sord);
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        List<Article> articles = articleDao.selectByExampleAndRowBounds(example, rowBounds);
        map.put("rows",articles);
        map.put("page",page);
        map.put("records",count);
        map.put("sidx",sidx);
        map.put("sord",sord);
        map.put("total",(count%rows==0)?count/rows:count/rows+1);
        return map;
    }

    @Override
    public void addArticle(Article article) {
        article.setId(idWorker.nextId()+"");
        article.setArticlePubDate(new Date());
        int i = articleDao.insert(article);
        if(i == 0){
            throw new RuntimeException("添加失败");
        }else {
            System.out.println(1111);
            GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-35ead28a4ef84f7f89ffc7c60192d02c");
            goEasy.publish("test_wpl",article.getArticleContent());

        }
    }

    @Override
    public void editArticle(Article article) {
        int i = articleDao.updateByPrimaryKeySelective(article);
        if(i == 0){
            throw new RuntimeException("修改失败");
        }
    }

    @Override
    public void deleteArticle(Article article) {
        int i = articleDao.deleteByPrimaryKey(article.getId());
        if(i == 0){
            throw new RuntimeException("删除失败");
        }
    }

    @Override
    public List<Article> showArticleByMaterId(String uid) {
        User user = userDao.selectByPrimaryKey(uid);
        String masterId = user.getMasterId();
        Master master = masterDao.selectByPrimaryKey(masterId);
        String masterName = master.getMasterName();
        Example example = new Example(Article.class);
        example.createCriteria().andEqualTo("authorName",masterName);
        List<Article> articles = articleDao.selectByExample(example);
        return articles;
    }

    @Override
    public List<Article> showAllArticle() {
        List<Article> articles = articleDao.selectAll();
        return articles;
    }
}
