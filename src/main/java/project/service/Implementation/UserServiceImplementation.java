package project.service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	PasswordEncoder passwordEncoder;
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
			PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
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
	public Users userLogin(String username, String password) {
		String encryptedPassword = repository.checkPassword(username, password);
		if(passwordEncoder.matches(password, encryptedPassword)){
			System.out.println("Yas Queen");
		} else {
			System.out.println("Nah Queen :(");
		}
		return repository.userLogin(username, password);
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
