package project.TyndaPlatform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.TyndaPlatform.dto.MusicDTO;
import project.TyndaPlatform.mapper.MusicMapper;
import project.TyndaPlatform.repository.MusicRepository;

import java.util.List;

@Service
public class MusicService {

    @Autowired
    private MusicRepository musicRepository;

    @Autowired
    private AdminService adminService;

    @Autowired
    private MusicMapper musicMapper;

    public MusicDTO addMusic(MusicDTO music){

        return musicMapper.toDto(musicRepository.save(musicMapper.toModel(music)));
    }

    public List<MusicDTO> getMusics(){
        return musicMapper.toDtoList(musicRepository.findAll());
    }

    public void deleteMusicById(Long id){
        musicRepository.deleteMusicById(id);
    }

    public MusicDTO getMusicById(Long id){
        return musicMapper.toDto(musicRepository.getMusicById(id));
    }

    public List<MusicDTO> searchMusic(String key){
        return musicMapper.toDtoList(musicRepository.searchMusic(key, adminService.getCurrentSessionUser().getId()));
    }

    public List<MusicDTO> getMusicsByUserId(Long id){
        return musicMapper.toDtoList(musicRepository.getMusicByUserId(id));
    }

    public MusicDTO updateMusic(MusicDTO music){
        return musicMapper.toDto(musicRepository.save(musicMapper.toModel(music)));
    }

}
