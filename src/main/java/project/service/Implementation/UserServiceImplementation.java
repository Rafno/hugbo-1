package project.service.Implementation;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.persistence.entities.Users;
import project.persistence.repositories.UsersRepository;
import project.service.UserService;

import java.util.List;

@Service
public class UserServiceImplementation implements UserService{
	
	
	// Instance Variables
	UsersRepository repository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	// Dependency Injection
	@Autowired
	public UserServiceImplementation(UsersRepository repository) {

		this.repository = repository;
	}
	
	@Override
	public void save(Users user) {
		// TODO, senda tilbaka villuskilaboð
		String username = repository.findByUsername(user.getUsername());

		if(username == null)
		{
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			repository.save(user);
		} else {
			System.out.println("User already exists");
		}
	}
	@Override
	public void delete(Users user) {
		 repository.delete(user);
	}

	@Override
	public Users userLogin(String username, String password) throws NullPointerException
	{
		Users possibleUser = new Users();
		try{
			possibleUser = repository.userLogin(username);
			if(passwordEncoder.matches(password, possibleUser.getPassword())) {
				return possibleUser;
			} else {
				System.out.println("User does not exists");
			}
			
			return possibleUser;
		} catch (NullPointerException e){
			System.out.println("User doesnt exist");
		}
		return possibleUser;
	}
	
	@Override
	public List<Users> getAllPatients(Users user) {
		return repository.getAllPatients(user);
	}
	
	@Override
	public String addPatient(Users user) {
		// Get all the Users notes
		return repository.addPatient(user);
	}
	
	@Override
	public String editPatient(Users user) {
		return repository.editPatient(user);
	}
	
	@Override
	public String findByUsername(String username) {
		
		return repository.findByUsername(username);
	}
}
