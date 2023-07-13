package project.TyndaPlatform.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "t_artists")
@Getter
@Setter
public class Artist extends Base{

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "full_name")
    private String fullName;

    @ManyToOne
    private User user;

}
