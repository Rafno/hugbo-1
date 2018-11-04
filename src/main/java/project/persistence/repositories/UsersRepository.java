package project.persistence.repositories;

import org.hibernate.annotations.SQLInsert;
import org.hibernate.annotations.SQLUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.persistence.entities.Users;

import javax.transaction.Transactional;
import java.util.List;

public interface UsersRepository extends JpaRepository<Users, Long>{

	Users save(Users user);
	
	void delete(Users user);
	@Query(value = "SELECT p FROM Users p WHERE p.username = ?1")
	Users userLogin(String username);

	@Query(value = "SELECT p FROM Users p WHERE p.username = ?1")
	Users getUser(String username);

	@Query(value = "SELECT p.name, p.username FROM Users p")
	List<Users> getAllPatients(Users user);
	
	// TODO Bæta við virkni til að bæta við notanda
	@Query(value = "SELECT p.name FROM Users p")
	String addPatient(Users user);
	@Query(value = "SELECT p.name FROM Users p")
	String editPatient(Users user);
	String findByUsername(String username);
	@Query(value = "select (CASE WHEN userName = ?1 THEN True ELSE False END) from Users p" )
	Boolean [] userNameExists(String username);

	@Transactional
	@Modifying
	@Query("UPDATE Users p SET p.imagePublicId = :image_id WHERE p.username = :userName")
	void updateImageId(@Param("image_id") String imageId, @Param("userName") String username);

	@Query( "select p.name from Users p where p.id in :ids" )
	List<String> getUsersById(@Param("ids") List<Long> userids);

}