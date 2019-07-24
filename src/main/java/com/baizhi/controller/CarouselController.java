package com.baizhi.controller;

        import com.baizhi.entity.Carousel;
        import com.baizhi.service.CarouselService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.ResponseBody;
        import org.springframework.web.multipart.MultipartFile;

        import javax.servlet.http.HttpServletRequest;
        import java.io.File;
        import java.io.IOException;
        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;

@Controller
@RequestMapping("/carousel")
public class CarouselController {
    @Autowired
    private CarouselService carouselService;

    @RequestMapping("/showAllCarousels")
    @ResponseBody
    public Map<String,Object> showAllCarousels(Integer page,Integer rows,String sidx,String sord){
        Map<String,Object> map = new HashMap<>();
        Integer count = carouselService.countAll();
        List<Carousel> carousels = carouselService.findAllByPage(page, rows, sidx, sord);
        Integer total = 0;
        if(count % rows == 0){
            total = count / rows;
        }else {
            total = count / rows + 1;
        }
        map.put("page",page);
        map.put("records",count);
        map.put("total",total);
        map.put("sidx",sidx);
        map.put("sord",sord);
        map.put("rows",carousels);
        return map;
    }
    @RequestMapping(value = "/editCarousel")
    @ResponseBody
    public Map<String,Object> editCarousel(String oper,Carousel carousel,String id){
        Map<String, Object> map = new HashMap<String,Object>();
        System.out.println("oper==========="+oper);
        if ("add".equals(oper)){
            System.out.println("carousel=============="+carousel);
            carouselService.addCarousel(carousel);
            map.put("status",true);
            map.put("message",carousel.getId());
        }else if("edit".equals(oper)){
            carouselService.updateCarousel(carousel);
            map.put("status",true);
        }else if("del".equals(oper)){
            System.out.println("id============"+id);
            carouselService.deleteCarousel(id);
            map.put("status",true);
        }else {
            map.put("status",false);
        }
        return map;
    }
    @RequestMapping("/upload")
    public void upload(String id, MultipartFile carouselPicture, HttpServletRequest request) throws Exception {
        //文件上传
        System.out.println(carouselPicture);
        carouselPicture.transferTo(new File(request.getSession().getServletContext().getRealPath("/carousel/img"),carouselPicture.getOriginalFilename()));
        System.out.println("upload successfully!");
        //修改文件名
        Carousel carousel = new Carousel();
        carousel.setId(id);
        carousel.setCarouselPicture(carouselPicture.getOriginalFilename());
        carouselService.updateCarousel(carousel);

    }
}
