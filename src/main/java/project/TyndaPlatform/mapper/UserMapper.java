package project.TyndaPlatform.mapper;

import org.mapstruct.Mapper;
import project.TyndaPlatform.dto.UserDTO;
import project.TyndaPlatform.model.User;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDto(User user);

    User toModel(UserDTO userDTO);

    List<UserDTO>  toDtoList(List<User> userList);
    List<User>  toModelList(List<UserDTO> userDTOList);

}
