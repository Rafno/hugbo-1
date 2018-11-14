package project.persistence.repositories;

import org.hibernate.annotations.SQLInsert;
import org.hibernate.annotations.SQLUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import project.persistence.entities.Users;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Every user function is used here.
 * Users are created, users can be patients or doctors
 */

/**
 * HOW TO CREATE QUERIES
 * @Query(value="Select f from Foo f"
 * Foo must be the name of the java class of the entity, not the schema created by Spring.
 * f must be a variable used to parse through the schema, such as select f from Foo f where f.username = ?1
 * INSERT/DELETE is not supported by spring queries. must be used via the nativeQuery keyword.
 */
@Repository
public interface UsersRepository extends JpaRepository<Users, Long>{

	Users save(Users user);
	
	void delete(Users user);
	//used to check logins.
	@Query(value = "SELECT p FROM Users p WHERE p.username = ?1")
	Users userLogin(String username);
	// Used to verify logins
	@Query(value = "SELECT p FROM Users p WHERE p.username = ?1")
	Users getUser(String username);
	//VERY IMPORTANT!
	// While the entity is created via camelCase, Spring will CHANGE camelCase into snake_case
	//All queries must be used with the entity names.
	@Query(value="SELECT p FROM Users p INNER JOIN DoctorPatients dp ON p.id = dp.patientId WHERE dp.doctorId = ?1")
	List<Users> findAllPatients(Long id);
	
	@Query(value="SELECT p FROM Users p INNER JOIN DoctorPatients dp ON p.id = dp.doctorId WHERE dp.patientId = ?1")
	List<Users> findDoctor(Long id);
	
	List<Users>findAll();
	
	@Query(value = "SELECT p.name FROM Users p")
	String addPatient(Users user);
	
	@Query(value = "SELECT p.name FROM Users p")
	String editPatient(Users user);
	
	String findByUsername(String username);
	
	@Query(value = "select (CASE WHEN userName = ?1 THEN True ELSE False END) from Users p" )
	Boolean [] userNameExists(String username);

	@Query(value = "SELECT p FROM Users p WHERE p.id = ?1")
	Users findOne(Long id);
	
	/**
	 * Used to update images on your myHome page.
	 * @param imageId
	 * @param username
	 */
	@Transactional
	@Modifying
	@Query("UPDATE Users p SET p.imagePublicId = :image_id WHERE p.username = :userName")
	void updateImageId(@Param("image_id") String imageId, @Param("userName") String username);

	@Query( "select p from Users p where p.id in :ids" )
	List<Users> getUsersById(@Param("ids") List<Long> userids);

	@Query( "select p from Users p where p.username LIKE ?1" )
	Users getUsersByUsername(String username);

	@Query( "select p from Users p where p.role LIKE 'USER' ")
	List<Users> getPatients();

}