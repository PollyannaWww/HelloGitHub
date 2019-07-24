package com.baizhi;

import com.baizhi.dao.CarouselDao;
import com.baizhi.entity.Carousel;
import com.baizhi.service.CarouselService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestCarsousel {
    @Autowired
    private CarouselService carouselService;
    @Test
    public void test1(){
        List<Carousel> all = carouselService.findAllByPage(1, 3, "id", "asc");
        all.forEach(a -> System.out.println(a));
    }

    @Test
    public void test2(){
        carouselService.deleteCarousel("frhd");
    }
}
