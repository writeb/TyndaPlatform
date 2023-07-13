package project.TyndaPlatform.dto;

import lombok.Getter;
import lombok.Setter;
import project.TyndaPlatform.model.User;

@Getter
@Setter
public class ArtistDTO {

    private Long id;
    private String nickname;
    private String fullName;
    private User user;
}
