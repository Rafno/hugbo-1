package project.service;

import project.persistence.entities.Users;

import java.util.List;

public interface UserService
{
	
	void save(Users user);
	
	void delete(Users user);

	void updateImageId(String imageId, String userName);
	
	Users userLogin(String username, String password);

	Users getUser(String username);

	List<Users> getAllPatients(Users user);
	
	String addPatient(Users user);
	
	String editPatient(Users user);
	
	String findByUsername(String username);

	List<String> getUsersbyId(List<Long> userids);

	Boolean [] userNameExists(String username);
}
