package project.TyndaPlatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.TyndaPlatform.model.Artist;

import java.util.List;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {

    Artist getArtistById(Long id);

    List<Artist> getArtistsByUserId(Long id);
}
