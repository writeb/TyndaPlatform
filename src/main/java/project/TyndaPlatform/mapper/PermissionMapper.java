package project.TyndaPlatform.mapper;

import org.mapstruct.Mapper;
import project.TyndaPlatform.dto.PermissionDTO;
import project.TyndaPlatform.model.Permission;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PermissionMapper {

    PermissionDTO toDto(Permission permission);

    Permission toModel(PermissionDTO permissionDTO);

    List<PermissionDTO> toDtoList(List<Permission> permissionList);
    List<Permission>  toModelList(List<PermissionDTO> permissionDTOList);
}
