package com.baizhi.dao;

import com.baizhi.entity.Article;
import tk.mybatis.mapper.common.Mapper;

//public interface ArticleDao extends JpaRepository<Article,String>, JpaSpecificationExecutor<Article> {
//
//    @Query(value = "select * from c_article",nativeQuery = true)
//    public Page<Article> fingByPage(Pageable pageable);
//}
public interface ArticleDao extends Mapper<Article>{
}