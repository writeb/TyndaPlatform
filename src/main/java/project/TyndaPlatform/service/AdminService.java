package project.TyndaPlatform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.TyndaPlatform.dto.PermissionDTO;
import project.TyndaPlatform.dto.UserDTO;
import project.TyndaPlatform.mapper.PermissionMapper;
import project.TyndaPlatform.mapper.UserMapper;
import project.TyndaPlatform.model.Permission;
import project.TyndaPlatform.repository.PermissionRepository;
import project.TyndaPlatform.repository.UserRepository;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    public UserDTO addUser(UserDTO user){
        if (userRepository.findByEmail(user.getEmail()) == null){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            UserDTO newUser = userMapper.toDto(userRepository.save(userMapper.toModel(user)));
            changeUserRole(newUser.getId(), Long.valueOf(2));
            return newUser;
        }
        return null;
    }

    public void changeUserRole(Long userId, Long roleId){
        UserDTO user = userMapper.toDto(userRepository.findById(userId).orElse(null));
        if (user!=null){
            List<PermissionDTO> permissions = permissionMapper.toDtoList(permissionRepository.findPermissionsById(roleId));
            user.setPermissions(permissions);
            userMapper.toDto(userRepository.save(userMapper.toModel(user)));
        }
    }

    public UserDTO updatePassword(String newPassword, String oldPassword) {
        UserDTO currentUser = getCurrentSessionUser();
        if (passwordEncoder.matches(oldPassword, currentUser.getPassword())) {
            currentUser.setPassword(passwordEncoder.encode(newPassword));
            return userMapper.toDto(userRepository.save(userMapper.toModel(currentUser)));
        }
        return null;
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public List<UserDTO> getUsers(){
        return userMapper.toDtoList(userRepository.findAll());
    }

    public UserDTO getCurrentSessionUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)){
            UserDTO user = (UserDTO) authentication.getPrincipal();
            if (user!=null){
                return user;
            }
        }
        return null;
    }

    public List<UserDTO> searchUser(String key){
        return userMapper.toDtoList(userRepository.searchUser(key));
    }
}
