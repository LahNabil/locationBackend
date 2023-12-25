package emsi.projet.location.services;

import java.nio.CharBuffer;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import emsi.projet.location.dto.CredentialsDto;
import emsi.projet.location.dto.SignUpDto;
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

		//public User login(String login, String password) {
		   // var user = userRepository.findByLogin(login).orElseThrow();
		   // return user;

		    //return optionalUser.map(user -> {
		        //if (passwordEncoder.matches(CharBuffer.wrap(password), user.getPassword())) {
		           // return user;
		        //} else {
		           // throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
		        //}
		    //}).orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
		//}
	
	    
	    public User register(User user) {
	        Optional<User> optionalUser = userRepository.findByLogin(user.getLogin());

	        if (optionalUser.isPresent()) {
	            throw new AppException("Login already exists", HttpStatus.BAD_REQUEST);
	        }

	        //User user = userMapper.signUpToUser(userDto);
	        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(user.getPassword())));
	        return userRepository.save(user);

	        //User savedUser = userRepository.save(user);

	        //return userMapper.toUserDto(savedUser);
	    }
	    
	    public List<User> findAll(){
			return userRepository.findAll();
		}
	
}
