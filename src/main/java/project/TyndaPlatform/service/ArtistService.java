package project.TyndaPlatform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.TyndaPlatform.dto.ArtistDTO;
import project.TyndaPlatform.mapper.ArtistMapper;
import project.TyndaPlatform.repository.ArtistRepository;

import java.util.List;

@Service
public class ArtistService {

    @Autowired
    private ArtistMapper artistMapper;

    @Autowired
    private ArtistRepository artistRepository;

    public ArtistDTO addArtist(ArtistDTO artist){

        return artistMapper.toDto(artistRepository.save(artistMapper.toModel(artist)));
    }

    public List<ArtistDTO> getArtists(){
        return artistMapper.toDtoList(artistRepository.findAll());
    }

    public ArtistDTO getArtistById(Long id){
        return artistMapper.toDto(artistRepository.getArtistById(id));
    }

    public List<ArtistDTO> getArtistsByUserId(Long id){
        return artistMapper.toDtoList(artistRepository.getArtistsByUserId(id));
    }
}
