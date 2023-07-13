package project.TyndaPlatform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.TyndaPlatform.model.Music;
import project.TyndaPlatform.model.Permission;
import project.TyndaPlatform.model.User;
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

    public User addUser(User user){
        if (userRepository.findByEmail(user.getEmail()) == null){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            User newUser = userRepository.save(user);
            changeUserRole(newUser.getId(), Long.valueOf(2));
            return newUser;
        }
        return null;
    }

    public void changeUserRole(Long userId, Long roleId){
        User user = userRepository.findById(userId).orElse(null);
        if (user!=null){
            List<Permission> permissions = permissionRepository.findPermissionsById(roleId);
            user.setPermissions(permissions);
            userRepository.save(user);
        }
    }

    public User updatePassword(String newPassword, String oldPassword) {
        User currentUser = getCurrentSessionUser();
        if (passwordEncoder.matches(oldPassword, currentUser.getPassword())) {
            currentUser.setPassword(passwordEncoder.encode(newPassword));
            return userRepository.save(currentUser);
        }
        return null;
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User getCurrentSessionUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)){
            User user = (User) authentication.getPrincipal();
            if (user!=null){
                return user;
            }
        }
        return null;
    }

    public List<User> searchUser(String key){
        List<User> userList = userRepository.searchUser(key);
        return userList;
    }
}
