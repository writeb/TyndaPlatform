package project.TyndaPlatform.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "t_musics")
@Getter
@Setter
public class Music extends Base{

    @Column(name = "title")
    private String title;

    @Column(name = "genre")
    private String genre;

    @Column(name = "image")
    private String image;

    @Column(name = "audio")
    private String audio;

    @ManyToOne
    private User user;

    @ManyToOne
    private Artist artist;

}
