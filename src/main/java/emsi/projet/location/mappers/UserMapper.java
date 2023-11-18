package emsi.projet.location.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import emsi.projet.location.dto.SignUpDto;
import emsi.projet.location.dto.UserDto;
import emsi.projet.location.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
	
	UserDto toUserDto(User user);
	
	@Mapping(target = "password", ignore = true)
    User signUpToUser(SignUpDto signUpDto);


}
