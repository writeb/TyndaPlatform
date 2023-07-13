package project.TyndaPlatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.TyndaPlatform.model.Permission;

import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

    List<Permission> findPermissionsById(Long id);
}
