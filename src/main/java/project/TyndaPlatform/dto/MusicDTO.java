package project.TyndaPlatform.dto;

import lombok.Getter;
import lombok.Setter;

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
