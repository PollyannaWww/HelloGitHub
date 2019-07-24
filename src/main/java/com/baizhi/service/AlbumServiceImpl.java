package com.baizhi.service;

import com.baizhi.dao.AlbumDao;
import com.baizhi.entity.Album;
import com.baizhi.util.IdWorker;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumDao albumDao;
    @Autowired
    private IdWorker idWorker;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Map<String,Object> selectAllAlbums(Integer page, Integer pageSize, String sidx, String sord) {
        HashMap<String, Object> map = new HashMap<>();
        Album album = new Album();
        int count = albumDao.selectCount(album);
        Integer total = 0;
        map.put("page",page);
        map.put("records",count);
        map.put("total",(count % pageSize == 0)?count/pageSize:count/pageSize+1);
        map.put("sidx",sidx);
        map.put("sord",sord);
        Example example = new Example(Album.class);
        example.setOrderByClause(sidx+" "+sord);
        RowBounds rowBounds = new RowBounds((page - 1) * pageSize, pageSize);
        map.put("rows",albumDao.selectByExampleAndRowBounds(example, rowBounds));
        return map;
    }

    @Override
    public void addAlbum(Album album) {
//        String s = UUID.randomUUID().toString();
//        String s1 = s.replaceAll("-", "");
        album.setId(idWorker.nextId()+"");
        album.setAlbumPubDate(new Date());
        int i = albumDao.insert(album);
        if(i == 0){
            throw new RuntimeException("添加专辑失败啦！");
        }
    }

    @Override
    public void updateAlbum(Album album) {
        albumDao.updateByPrimaryKeySelective(album);
    }

    @Override
    public Album selectById(String id) {
        Album album = albumDao.selectByPrimaryKey(id);
        return album;
    }

    @Override
    public List<Album> showAlbums() {
        List<Album> albums = albumDao.showAlbums();
        return albums;
    }
}
