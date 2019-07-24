package com.baizhi.service;

import com.baizhi.entity.Carousel;

import java.util.List;

public interface CarouselService {
    List<Carousel> findAllByPage(Integer page, Integer pageSize,String sidx,String sord);
    Integer countAll();
    void addCarousel(Carousel carousel);
    void updateCarousel(Carousel carousel);
    void deleteCarousel(String id);
    List<Carousel> showCarousel();
}
