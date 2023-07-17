package project.TyndaPlatform.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArtistDTO {

    private Long id;
    private String nickname;
    private String fullName;
    private UserDTO user;
}
