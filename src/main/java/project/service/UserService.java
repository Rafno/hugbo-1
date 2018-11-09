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

	Users findOne(Long id);

	List<Users> findAll();
	
	String addPatient(Users user);
	
	String editPatient(Users user);
	
	String findByUsername(String username);

	List<String> getUsersById(List<Long> userids);

	String getUsersByUsername(String username);

	Boolean [] userNameExists(String username);
}
