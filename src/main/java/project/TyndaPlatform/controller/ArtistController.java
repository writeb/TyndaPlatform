package project.TyndaPlatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import project.TyndaPlatform.dto.ArtistDTO;
import project.TyndaPlatform.service.ArtistService;

@Controller
public class ArtistController {

    @Autowired
    private ArtistService artistService;

    @PreAuthorize("hasAnyRole('ROLE_VIP_USER', 'ROLE_ADMIN')")
    @GetMapping(value = "/add-artist")
    public String addArtist(){
        return "add-artist";
    }

    @PreAuthorize("hasAnyRole('ROLE_VIP_USER', 'ROLE_ADMIN')")
    @PostMapping(value = "/add-artist")
    public String addArtist(ArtistDTO artist){
        artistService.addArtist(artist);
        return "redirect:/";
    }
}
