package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.entity.Article;
import com.baizhi.entity.Carousel;
import com.baizhi.service.AlbumService;
import com.baizhi.service.ArticleService;
import com.baizhi.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("firstPage")
public class FirstPageController {
    @Autowired
    private CarouselService carouselService;
    @Autowired
    private AlbumService albumService;
    @Autowired
    private ArticleService articleService;

    @RequestMapping("first_page")
    public Map<String,Object> all(String uid,String type,String sub_type){
        HashMap<String, Object> map = new HashMap<>();
        if("all".equals(type)){
            //查询五张轮播图
            List<Carousel> carousels = carouselService.showCarousel();
            //查询六个专辑
            List<Album> albums = albumService.showAlbums();
            //查询所属上师的文章
            List<Article> articles = articleService.showArticleByMaterId(uid);
            map.put("headers",carousels);
            map.put("album",albums);
            map.put("article",articles);
        }else if("wen".equals(type)){
            //查询六个专辑
            map.put("album",null);
        }else if("si".equals(type)){
            if("ssyj".equals(sub_type)){
                //查询所属上师的文章
                map.put("article",null);
            }else if("xmfy".equals(sub_type)){
                //查询所有上师的文章
            }
        }
        return map;
    }
}
