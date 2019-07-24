package com.baizhi.service;

import com.baizhi.dao.AlbumDao;
import com.baizhi.entity.Album;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public interface AlbumService {
    Map<String,Object> selectAllAlbums(Integer page, Integer pageSize, String sidx, String sord);
    void addAlbum(Album album);
    void updateAlbum(Album album);
    Album selectById(String id);
    List<Album> showAlbums();
}
