package project.TyndaPlatform.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.TyndaPlatform.dto.MusicDTO;
import project.TyndaPlatform.model.Music;
import project.TyndaPlatform.service.MusicService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/music")
public class MusicRestController {

    @Autowired
    private MusicService musicService;

    @GetMapping
    public List<MusicDTO> getMusics(){
        return musicService.getMusics();
    }

    @GetMapping(value = "{id}")
    public MusicDTO getMusic(@PathVariable(name = "id") Long id){
        return musicService.getMusicById(id);
    }

    @PostMapping
    public MusicDTO addMusic(@RequestBody MusicDTO music){
        return musicService.addMusic(music);
    }

    @PutMapping
    public MusicDTO updateMusic(@RequestBody MusicDTO music){
        return musicService.updateMusic(music);
    }

    @DeleteMapping(value = "{id}")
    public void deleteMusic(@PathVariable(name = "id") Long id){
        musicService.deleteMusicById(id) ;
    }
}
