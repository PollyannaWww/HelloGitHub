package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("album")
public class AlbumController {
    @Autowired
    private AlbumService albumService;
    @RequestMapping("showAllAlbums")
    public Map<String,Object> showAllAlbums(Integer page,Integer rows,String sidx,String sord){
        Map<String, Object> map = new HashMap<>();
        map = albumService.selectAllAlbums(page,rows,sidx,sord);
        return map;
    }
    @RequestMapping("editAlbum")
    public Map<String,Object> editAlbum(String oper, Album album){
        Map<String, Object> map = new HashMap<>();
        try {
            if ("add".equals(oper)){
                albumService.addAlbum(album);
                map.put("status",true);
                map.put("message",album.getId());
            }
        }catch (Exception e){
            map.put("status",false);
            map.put("message",e.getMessage());
        }
        return map;
    }
    @RequestMapping("upload")
    public void upload(String id, MultipartFile albumCover, HttpServletRequest request) throws Exception{
        albumCover.transferTo(new File(request.getSession().getServletContext().getRealPath("/album/audios"),albumCover.getOriginalFilename()));
        System.out.println("file uploaded!");
        Album album = new Album();
        album.setId(id);
        album.setAlbumCover(albumCover.getOriginalFilename());
        albumService.updateAlbum(album);
    }
}
