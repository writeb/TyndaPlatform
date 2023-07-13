package project.TyndaPlatform.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
public class PermissionDTO implements GrantedAuthority {
    private Long id;
    private String role;
    public String getAuthority() {
        return this.role;
    }
}
