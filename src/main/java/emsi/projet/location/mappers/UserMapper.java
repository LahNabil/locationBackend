package emsi.projet.location.mappers;

import org.mapstruct.Mapper;

import emsi.projet.location.dto.UserDto;
import emsi.projet.location.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
	
	UserDto toUserDto(User user);

}
