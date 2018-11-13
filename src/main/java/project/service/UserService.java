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
	
	/**
	 * finds all patient users.
	 * @param id
	 * @return
	 */
	List<Users> findAllPatients(Long id);
	
	/**
	 * Finds all doctor users
	 * @param id
	 * @return
	 */
	List<Users> findDoctor(Long id);
	
	String addPatient(Users user);
	
	String editPatient(Users user);
	
	String findByUsername(String username);
	
	List<String> getUsersById(List<Long> userids);
	
	Users getUsersByUsername(String username);
	
	Boolean [] userNameExists(String username);
	
	List<Users>findAll();
}
