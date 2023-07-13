package project.TyndaPlatform.mapper;

import org.mapstruct.Mapper;
import project.TyndaPlatform.dto.ArtistDTO;
import project.TyndaPlatform.model.Artist;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArtistMapper {

    ArtistDTO toDto(Artist artist);

    Artist toModel(ArtistDTO artistDTO);

    List<ArtistDTO>  toDtoList(List<Artist> artistList);
    List<Artist>  toModelList(List<ArtistDTO> artistDTOList);

}
