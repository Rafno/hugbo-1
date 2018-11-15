package project.service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import project.persistence.entities.User_roles;
import project.persistence.entities.Users;
import project.persistence.repositories.UserRolesRepository;
import project.persistence.repositories.UsersRepository;
import project.service.UserService;

import java.util.List;
import java.util.logging.Logger;

@Service
public class UserServiceImplementation implements UserService{
	
	
	// Instance Variables
	UsersRepository repository;
	Logger logger;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	UserRolesRepository userRolesRepository;
	// Dependency Injection
	@Autowired
	public UserServiceImplementation(UsersRepository repository, UserRolesRepository userRolesRepository) {

		this.repository = repository;
		this.userRolesRepository = userRolesRepository;
	}
	
	/**
	 * Saves the user, encrypts the password before inserting into database.
	 * user roles are saved dynamically.
	 * @param user
	 */
	@Override
	public void save(Users user) {
		//Skoða ef læknir
		//if(user.getRole().matches("Læknir"))
		String username = repository.findByUsername(user.getUsername());
		if(username == null)
		{
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			repository.save(user);
			User_roles auth = new User_roles(user.getUsername(), user.getRole());
			userRolesRepository.save(auth);
		}
	}
	@Override
	public void delete(Users user) {
		 repository.delete(user);
	}
	
	/**
	 * Huge mess.
	 * Checks if the user exists by looking into database for the unique username,
	 * then checks if the user with that username matches the encrypted password.
	 * if correct, send the user data back.
	 * @param username
	 * @param password
	 * @return
	 * @throws NullPointerException
	 */
	@Override
	public Users userLogin(String username, String password) throws NullPointerException
	{
		Users possibleUser = new Users();
		try{
			possibleUser = repository.userLogin(username);
			if(passwordEncoder.matches(password, possibleUser.getPassword())) {
				return possibleUser;
			} else {
				logger.info("User did not exist");
			}
			
			return possibleUser;
		} catch (NullPointerException e){
			logger.info("User did not exist");
		}
		return possibleUser;
	}

	@Override
	public Users getUser(String username){
		return repository.getUser(username);
	}

	@Override
	public Users findOne(Long id){
		return repository.findOne(id);
	}

	@Override
	public List<Users> findAllPatients(Long id) {
		
		return repository.findAllPatients(id);
	}
	
	@Override
	public List<Users> findDoctor(Long id){
		return repository.findDoctor(id);
	}
	
	@Override
	public List<Users>findAll(){return repository.findAll();}
	
	@Override
	public String addPatient(Users user) {
		return repository.addPatient(user);
	}
	
	@Override
	public String editPatient(Users user) {
		return repository.editPatient(user);
	}
	
	@Override
	public String findByUsername(String username) {
		
		return repository.findByUsername(username);
	}
	@Override
	public Boolean [] userNameExists(String username){
		return repository.userNameExists(username);
	}

	public void updateImageId(String imageId, String userName){
		repository.updateImageId(imageId,userName);
	}

	public List<Users> getUsersById(List<Long> userids){return repository.getUsersById(userids);	}

	public Users getUsersByUsername(String username){return repository.getUsersByUsername(username);}

	public List<Users> getPatients(){ return repository.getPatients();}
	
	/**
	 * Changes the boolean value in the table Users to true
	 * since the user has confirmed their email.
	 * @param user
	 */
	public void confirmEmail(Users user){
		repository.confirmEmail(user);
	}
}
