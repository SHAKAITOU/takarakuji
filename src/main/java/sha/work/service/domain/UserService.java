package sha.work.service.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import sha.work.dto.domain.User;
import sha.work.exception.TKRKScreenException;
import sha.work.mapper.domain.UserMapper;

@Service
public class UserService implements UserDetailsService {

	/** DB access class. */
	@Autowired
	private UserMapper userMapper;
	
	

	public User getByCode(String code) throws TKRKScreenException {
		
		return userMapper.getByCode(code);
	}



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UserDetails userDetails = getByCode(username);
		return userDetails;
	}

}
