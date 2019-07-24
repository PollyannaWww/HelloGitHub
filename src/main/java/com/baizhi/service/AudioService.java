package com.baizhi.service;

import com.baizhi.entity.Audio;

import java.util.Map;

public interface AudioService {
    Map<String,Object> findAudiosByAlbumId(String sidx,String sord,Integer page,Integer pageSize,String albumId);
    void addAudio(Audio audio);
    void updateAudio(Audio audio);
}
