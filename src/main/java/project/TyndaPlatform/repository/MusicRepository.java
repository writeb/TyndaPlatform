package project.TyndaPlatform.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.TyndaPlatform.model.Music;
import project.TyndaPlatform.model.User;

import java.util.List;

@Repository
public interface MusicRepository extends JpaRepository<Music, Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Music m WHERE m.id = :id")
    void deleteMusicById(@Param("id") Long id);

    Music getMusicById(Long id);

    @Query("SELECT mus FROM Music mus WHERE lower(mus.title) LIKE lower(concat('%', :criteria, '%'))")
    List<Music> searchMusic(@Param("criteria") String title);

    List<Music> getMusicByUserId(Long id);

}
