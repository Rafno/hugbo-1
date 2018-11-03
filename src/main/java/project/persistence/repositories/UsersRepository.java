package project.persistence.repositories;

import org.hibernate.annotations.SQLInsert;
import org.hibernate.annotations.SQLUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.persistence.entities.Users;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users, Long>{
	//TODO endurskrifa þessi Queries
	Users save(Users user);
	
	void delete(Users user);
	@Query(value = "SELECT p FROM Users p WHERE p.username = ?1")
	Users userLogin(String username);
	@Query(value = "SELECT p.name, p.username FROM Users p")
	List<Users> getAllPatients(Users user);
	
	// TODO Bæta við virkni til að bæta við notanda
	@Query(value = "SELECT p.name FROM Users p")
	String addPatient(Users user);
	@Query(value = "SELECT p.name FROM Users p")
	String editPatient(Users user);
	String findByUsername(String username);
	@Query(value = "SELECT p.password FROM Users p WHERE p.username = ?1 AND p.password = ?2")
	String checkPassword(String username, String password);
	@Query(value = "select (CASE WHEN userName = ?1 THEN True ELSE False END) from Users p" )
	Boolean [] userNameExists(String username);
}
