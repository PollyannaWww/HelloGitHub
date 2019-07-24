package com.baizhi.service;

import com.baizhi.dao.AlbumDao;
import com.baizhi.dao.AudioDao;
import com.baizhi.entity.Audio;
import com.baizhi.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
public class AudioServiceImpl implements AudioService {
    @Autowired
    private AudioDao audioDao;
    @Autowired
    private IdWorker idWorker;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Map<String, Object> findAudiosByAlbumId(String sidx,String sord,Integer page,Integer pageSize,String albumId) {
        Map<String, Object> map = new HashMap<>();
        Audio audio = new Audio();
        audio.setAlbumId(albumId);
        Example example = new Example(Audio.class);
        example.createCriteria().andEqualTo("albumId",albumId);
        List<Audio> audios = audioDao.selectByExample(example);
        //audios.forEach(s -> System.out.println(s));
        int count = audioDao.selectCount(audio);
        map.put("sidx",sidx);
        map.put("sord",sord);
        map.put("rows",audios);
        map.put("records",count);
        map.put("total",(count%pageSize==0)?count/pageSize:count/pageSize+1);
        map.put("page",page);
        return map;
    }

    @Override
    public void addAudio(Audio audio) {
//        String s = UUID.randomUUID().toString();
//        String s1 = s.replaceAll("-", "");
        audio.setId(idWorker.nextId()+"");
        int insert = audioDao.insert(audio);
        if(insert == 0){
            throw new RuntimeException("添加音频出错啦！");
        }
    }

    @Override
    public void updateAudio(Audio audio) {
        //System.out.println("audio====="+audio);
        audioDao.updateByPrimaryKeySelective(audio);
    }
}
