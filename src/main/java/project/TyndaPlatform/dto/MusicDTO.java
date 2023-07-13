package project.TyndaPlatform.dto;

import lombok.Getter;
import lombok.Setter;
import project.TyndaPlatform.model.User;

@Getter
@Setter
public class MusicDTO {
    private Long id;
    private String title;
    private String genre;
    private String image;
    private String audio;
    private ArtistDTO artist;
    private UserDTO user;

}
