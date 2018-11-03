package project.service.Implementation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import project.persistence.entities.Users;
import project.persistence.repositories.UsersRepository;



@Service
public class UserDetailServiceImplementation implements UserDetailsService
{
	
	UsersRepository repository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	// Dependency Injection
	@Autowired
	public UserDetailServiceImplementation(UsersRepository repository)
	{
		
		this.repository = repository;
	}
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		Users user = repository.userLogin(username);
		
		if(user == null)
		{
			System.out.println("User not found! " + username);
			throw new UsernameNotFoundException("User " + username + " was not found in the database");
		}
		
		return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), user.getAuthority());
	}
}