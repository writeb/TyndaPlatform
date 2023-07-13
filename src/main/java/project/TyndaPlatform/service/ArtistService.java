package project.TyndaPlatform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.TyndaPlatform.model.Artist;
import project.TyndaPlatform.repository.ArtistRepository;

import java.util.List;

@Service
public class ArtistService {

    @Autowired
    private ArtistRepository artistRepository;

    public Artist addArtist(Artist artist){
        return artistRepository.save(artist);
    }

    public List<Artist> getArtists(){
        return artistRepository.findAll();
    }

    public Artist getArtistById(Long id){
        return artistRepository.getArtistById(id);
    }

    public List<Artist> getArtistsByUserId(Long id){
        return artistRepository.getArtistsByUserId(id);
    }
}
