package project.service;

import project.persistence.entities.Users;

import java.util.List;

public interface UserService
{
	
	Users create(Users user);
	
	void delete(Users user);
	
	List<Users> userLogin(String username, String password);
	
	List<Users> getAllPatients(Users user);
	
	String addPatient(Users user);
	
	String editPatient(Users user);
	
	String findByUsername(Users user);
}
