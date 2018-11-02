package project.service;

import project.persistence.entities.Users;

import java.util.List;

public interface UserService
{
	
	void save(Users user);
	
	void delete(Users user);
	
	Users userLogin(String username, String password);
	
	List<Users> getAllPatients(Users user);
	
	String addPatient(Users user);
	
	String editPatient(Users user);
	
	String findByUsername(String username);

	Boolean [] userNameExists(String username);
}
