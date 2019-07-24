package com.baizhi.service;

import com.baizhi.annotation.RedisCache;
import com.baizhi.dao.CarouselDao;
import com.baizhi.entity.Carousel;
import com.baizhi.util.IdWorker;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class CarouselServiceImpl implements CarouselService {
    @Autowired
    private CarouselDao carouselDao;
    @Autowired
    private IdWorker idWorker;

    @Override
    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    @RedisCache
    public List<Carousel> findAllByPage(Integer page, Integer pageSize,String sidx,String sord) {
        Example example = new Example(Carousel.class);
        example.setOrderByClause(sidx+" "+sord);
        //Carousel carousel = new Carousel();
        RowBounds rowBounds = new RowBounds((page - 1) * pageSize, pageSize);
        List<Carousel> carousels = carouselDao.selectByExampleAndRowBounds(example,rowBounds);
        return carousels;
    }

    @Override
    public Integer countAll() {
        Carousel carousel = new Carousel();
        int count = carouselDao.selectCount(carousel);
        return count;
    }

    @Override
    public void addCarousel(Carousel carousel) {
//        String s = UUID.randomUUID().toString();
//        String s1 = s.replaceAll("-", "");
//        System.out.println("s1=================="+s1);
        carousel.setId(idWorker.nextId()+"");
        carouselDao.insert(carousel);
    }

    @Override
    public void updateCarousel(Carousel carousel) {
        carouselDao.updateByPrimaryKeySelective(carousel);
    }

    @Override
    public void deleteCarousel(String id) {
        carouselDao.deleteByPrimaryKey(id);
    }

    @Override
    public List<Carousel> showCarousel() {
        Example example = new Example(Carousel.class);
        example.createCriteria().andEqualTo("carouselStatus",1);
        List<Carousel> carousels = carouselDao.selectByExample(example);
        return carousels;
    }
}
