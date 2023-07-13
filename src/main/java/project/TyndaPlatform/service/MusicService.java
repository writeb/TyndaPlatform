package project.TyndaPlatform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.TyndaPlatform.model.Music;
import project.TyndaPlatform.repository.MusicRepository;

import java.util.List;

@Service
public class MusicService {

    @Autowired
    private MusicRepository musicRepository;

    public Music addMusic(Music music){
        return musicRepository.save(music);
    }

    public List<Music> getMusics(){
        return musicRepository.findAll();
    }

    public void deleteMusicById(Long id){
        musicRepository.deleteMusicById(id);
    }

    public Music getMusicById(Long id){
        return musicRepository.getMusicById(id);
    }

    public List<Music> searchMusic(String key){
        List<Music> musicList = musicRepository.searchMusic(key);
        return musicList;
    }

    public List<Music> getMusicsByUserId(Long id){
        return musicRepository.getMusicByUserId(id);
    }

    public Music updateMusic(Music music){
        return musicRepository.save(music);
    }

}
