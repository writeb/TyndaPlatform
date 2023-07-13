package project.TyndaPlatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import project.TyndaPlatform.dto.ArtistDTO;
import project.TyndaPlatform.dto.MusicDTO;
import project.TyndaPlatform.mapper.ArtistMapper;
import project.TyndaPlatform.service.AdminService;
import project.TyndaPlatform.service.ArtistService;
import project.TyndaPlatform.service.FileUploadService;
import project.TyndaPlatform.service.MusicService;

@Controller
public class MusicController {

    @Autowired
    private MusicService musicService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private FileUploadService fileUploadService;

    @Autowired
    private ArtistService artistService;

    @Autowired
    private ArtistMapper artistMapper;

    @GetMapping(value = "/")
    public String homePage(Model model, @RequestParam(name = "key", required = false, defaultValue = "") String key){
        model.addAttribute("musics", musicService.searchMusic(key));
        model.addAttribute("artists", artistService.getArtists());
        return "home";
    }

    @PreAuthorize("hasAnyRole('ROLE_VIP_USER', 'ROLE_ADMIN')")
    @GetMapping(value = "/upload-song")
    public String uploadSongPage(Model model){
        model.addAttribute("artists", artistService.getArtists());
        return "upload-song";
    }

    @PreAuthorize("hasAnyRole('ROLE_VIP_USER', 'ROLE_ADMIN')")
    @PostMapping(value = "/upload-song")
    public String uploadSong(@RequestParam(name = "music_title") String title,
                             @RequestParam(name = "music_genre") String genre,
                             @RequestParam(name = "music_artist") Long artistId,
                             @RequestParam(name = "music_image") MultipartFile multipartFile,
                             @RequestParam(name = "music_audio") MultipartFile multipartFile2){

        MusicDTO music = new MusicDTO();
        music.setTitle(title);

        ArtistDTO artistDTO = artistService.getArtistById(artistId);
        music.setArtist(artistDTO);

        music.setGenre(genre);

        if (multipartFile.getContentType().equals("image/jpeg") ||
                multipartFile.getContentType().equals("image/png") ||
                multipartFile.getContentType().equals("image/webp")){

            fileUploadService.uploadImage(multipartFile, music);
        }

        if (multipartFile2.getContentType().equals("audio/mp4") ||
                multipartFile2.getContentType().equals("audio/mpeg") ||
                multipartFile2.getContentType().equals("audio/wav") ||
                multipartFile2.getContentType().equals("audio/ogg")){

            fileUploadService.uploadMusic(multipartFile2, music);
        }

        music.setUser(adminService.getCurrentSessionUser());

        musicService.addMusic(music);
        return "redirect:/";
    }



    @PreAuthorize("hasAnyRole('ROLE_VIP_USER', 'ROLE_ADMIN')")
    @PostMapping(value = "/delete-music")
    public String deleteMusic(@RequestParam(name = "id") Long id){
        musicService.deleteMusicById(id);
        return "redirect:/";
    }

    @PreAuthorize("hasAnyRole('ROLE_VIP_USER', 'ROLE_ADMIN')")
    @GetMapping(value = "/delete-music")
    public String deleteMusicPage(Model model, @RequestParam(name = "musicKey", required = false, defaultValue = "") String key){
        model.addAttribute("musics", musicService.searchMusic(key));
        return "delete";
    }

    @GetMapping(value = "/music-details/{musicId}")
    public String getMusicDetails(@PathVariable(name = "musicId") Long id, Model model){
        model.addAttribute("music", musicService.getMusicById(id));
        return "musicDetails";
    }

}
