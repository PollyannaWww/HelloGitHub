package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.entity.Audio;
import com.baizhi.service.AlbumService;
import com.baizhi.service.AudioService;
import it.sauronsoftware.jave.Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

import java.io.File;
import java.math.BigDecimal;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("audio")
public class AudioController {
    @Autowired
    private AudioService audioService;
    @Autowired
    private AlbumService albumService;

    @RequestMapping("showAudiosByAlbumId")
    public Map<String, Object> showAudiosByAlbumId(String sidx, String sord, Integer page, Integer rows, String albumId) {
        Map<String, Object> map = new HashMap<>();
        map = audioService.findAudiosByAlbumId(sidx, sord, page, rows, albumId);
        return map;
    }

    @RequestMapping("editAudio")
    public Map<String, Object> editAudio(String oper, Audio audio, String albumId) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            if ("add".equals(oper)) {
                audio.setAlbumId(albumId);
                audioService.addAudio(audio);
                Album album = albumService.selectById(audio.getAlbumId());
                Integer count = album.getAlbumCount();
                album.setId(audio.getAlbumId());
                album.setAlbumCount(count+1);
                albumService.updateAlbum(album);
                map.put("status", true);
                map.put("message", audio.getId());
            }
        } catch (Exception e) {
            map.put("status", false);
            map.put("message", e.getMessage());
        }
        return map;
    }

    @RequestMapping("uploadAudio")
    public void uploadAudio(String id, MultipartFile audioLocation, HttpServletRequest request) throws Exception {
        String realPath = request.getSession().getServletContext().getRealPath("/album/audios");
        File file = new File(realPath, audioLocation.getOriginalFilename());
        audioLocation.transferTo(file);
        System.out.println("uploaded!");
        Audio audio = new Audio();
        audio.setId(id);
        audio.setAudioLocation(audioLocation.getOriginalFilename());
        //获取音频大小
        BigDecimal size = new BigDecimal(audioLocation.getSize());
        BigDecimal mod = new BigDecimal(1024);
        size = size.divide(mod).divide(mod).setScale(2, BigDecimal.ROUND_HALF_UP);
        audio.setAudioSize(size+"MB");
        //获取音频时长
        Encoder encoder = new Encoder();
        Long length = encoder.getInfo(file).getDuration();
        audio.setAudioDuration(length/60000+":"+length/1000%60);
        System.out.println();
        audioService.updateAudio(audio);
    }
}
