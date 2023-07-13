package project.TyndaPlatform.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import project.TyndaPlatform.dto.MusicDTO;
import project.TyndaPlatform.model.Music;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MusicMapper {

    MusicDTO toDto(Music music);

    Music toModel(MusicDTO musicDTO);

    List<MusicDTO>  toDtoList(List<Music> musicList);
    List<Music>  toModelList(List<MusicDTO> musicList);

}
