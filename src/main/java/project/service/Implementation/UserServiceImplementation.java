package project.service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.persistence.entities.Users;
import project.persistence.repositories.UsersRepository;
import project.service.UserService;

import java.util.List;

@Service
public class UserServiceImplementation implements UserService{
	
	
	// Instance Variables
	UsersRepository repository;
	
	// Dependency Injection
	@Autowired
	public UserServiceImplementation(UsersRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public Users create(Users user) {return repository.create(user);}
	@Override
	public void delete(Users user) {
		 repository.delete(user);
	}
	
	@Override
	public String userLogin(String username, String password) {
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
	public String findByUsername(Users user) {
		
		return repository.findByUsername(user);
	}
}
