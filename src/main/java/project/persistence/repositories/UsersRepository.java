package project.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.persistence.entities.Users;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users, Long>{
	//TODO endurskrifa þessi Queries
	@Query(value = "SELECT name FROM Users")
	Users create(Users user);
	
	void delete(Users user);
	
	@Query(value = "SELECT name FROM Users")
	Users userLogin(String username, String password);
	
	// TODO FIX THIS, * cannot be used
	@Query(value = "SELECT name FROM Users")
	List<Users> getAllPatients(Users user);
	
	@Query(value = "SELECT name FROM Users")
	String addPatient(Users user);
	@Query(value = "SELECT name FROM Users")
	String editPatient(Users user);
	
	String findByUsername(Users user);
}
