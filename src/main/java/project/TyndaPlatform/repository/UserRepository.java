package project.TyndaPlatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.TyndaPlatform.model.Music;
import project.TyndaPlatform.model.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    void deleteById(Long id);

    @Query("SELECT user FROM User user WHERE lower(user.email) LIKE lower(concat('%', :criteria, '%'))")
    List<User> searchUser(@Param("criteria") String email);

}
