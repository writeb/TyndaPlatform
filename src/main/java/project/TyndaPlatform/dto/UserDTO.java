package project.TyndaPlatform.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import project.TyndaPlatform.model.Permission;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class UserDTO {
    private Long id;
    private String email;
    private String password;
    private String fullName;
    private List<PermissionDTO> permissions;


}
