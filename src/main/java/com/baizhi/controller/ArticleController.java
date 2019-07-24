package com.baizhi.controller;

import com.baizhi.entity.Article;
import com.baizhi.service.ArticleService;
import com.baizhi.service.ArticleServiceImpl;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @RequestMapping("showAllArticles")
    public Map<String,Object> showAllArticles(Integer page, Integer rows,String sidx,String sord){
        Map<String, Object> map = new HashMap<>();
        map = articleService.findAllArticles(page,rows,sidx,sord);
        return map;
    }
    @RequestMapping("editArticle")
    public Map<String,Object> editArticle(String oper, Article article){
        Map<String, Object> map = new HashMap<>();
        try{
            if("add".equals(oper)){
                articleService.addArticle(article);
            }else if("edit".equals(oper)){
                articleService.editArticle(article);
            }else if ("del".equals(oper)){
                articleService.deleteArticle(article);
            }
            map.put("status",true);
        }catch (Exception e){
            map.put("status",false);
            map.put("message",e.getMessage());
        }
        return map;
    }
    @RequestMapping("browser")
    public Map<String,Object> browser(HttpServletRequest request){
        File file = new File(request.getSession().getServletContext().getRealPath("/article/images"));
        File[] files = file.listFiles();
        Map<String, Object> map = new HashMap<>();
        map.put("current_url","http://localhost:8082/cmfz/article/images/");
        map.put("total_count",files.length);
        ArrayList<Object> objects = new ArrayList<>();
        for (File image : files) {
            HashMap<String, Object> imgMap = new HashMap<>();
            imgMap.put("is_dir",false);
            imgMap.put("has_file",false);
            imgMap.put("filesize",image.length());
            imgMap.put("is_photo",true);
            imgMap.put("filetype", FilenameUtils.getExtension(image.getName()));
            imgMap.put("filename",image.getName());
            objects.add(imgMap);
        }
        map.put("file_list",objects);
        return map;
    }
    @RequestMapping("upload")
    public Map<String,Object> upload(HttpServletRequest request, MultipartFile articleImage){
        Map<String, Object> map = new HashMap<>();
        try {
            articleImage.transferTo(new File(request.getSession().getServletContext().getRealPath("/article/images"),articleImage.getOriginalFilename()));
            map.put("error",0);
            map.put("url","http://localhost:8082/cmfz/article/images/"+articleImage.getOriginalFilename());
            //String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
        }catch (Exception e){
            map.put("error",1);
            e.printStackTrace();
        }
        return map;
    }
}
