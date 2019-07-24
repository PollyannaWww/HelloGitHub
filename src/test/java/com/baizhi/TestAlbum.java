package com.baizhi;

import com.baizhi.dao.AlbumDao;
import com.baizhi.entity.Album;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestAlbum {
    @Autowired
    private AlbumDao albumDao;
    @Test
    public void test1(){
        List<Album> albums = albumDao.showAlbums();
        albums.forEach(a -> System.out.println(a));
    }

}
