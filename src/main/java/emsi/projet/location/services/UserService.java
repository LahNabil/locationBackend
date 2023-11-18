package emsi.projet.location.services;

import java.nio.CharBuffer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import emsi.projet.location.dto.CredentialsDto;
import emsi.projet.location.dto.UserDto;
import emsi.projet.location.entities.User;
import emsi.projet.location.exceptions.AppException;
import emsi.projet.location.mappers.UserMapper;
import emsi.projet.location.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

		@Autowired
	 	private final UserRepository userRepository;
		@Autowired
	    private final UserMapper userMapper;
		@Autowired
	    private final PasswordEncoder passwordEncoder;

	    public UserDto login(CredentialsDto credentialsDto) {
	        User user = userRepository.findByLogin(credentialsDto.login())
	                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

	        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.password()), user.getPassword())) {
	            return userMapper.toUserDto(user);
	        }
	        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
	    }
	
}
