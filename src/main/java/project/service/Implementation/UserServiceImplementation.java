package project.service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import project.persistence.entities.*;
import project.persistence.repositories.*;
import project.service.UserService;

import javax.validation.constraints.Null;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class UserServiceImplementation implements UserService{
	
	
	// Instance Variables
	private UsersRepository repository;
	
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	private UserRolesRepository userRolesRepository;
	// Dependency Injection
	private CabinetRepository cabinetRepository;
	private DoctorPatientsRepository doctorPatientsRepository;
	private ReminderRepository reminderRepository;
	@Autowired
	public UserServiceImplementation(UsersRepository repository, UserRolesRepository userRolesRepository,
									 CabinetRepository cabinetRepository, DoctorPatientsRepository doctorPatientsRepository, ReminderRepository reminderRepository) {

		this.repository = repository;
		this.userRolesRepository = userRolesRepository;

	}
	
	/**
	 * Saves the user, encrypts the password before inserting into database.
	 * user roles are saved dynamically.
	 * @param user user object
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
	
	/**
	 * Deletes user and all possible combinations in our schema.
	 * One of the worst codes I've ever written, due to a fundemental problem with our schema relations.
	 * no foreign keys of any kind, due to any kind of googling of one-to-one, one-to-many resulting in errors
	 * and deprecated functions that Java Spring won't accept any more.
	 * Beware all who come here, do not touch this and do not ever ask me to read this.
	 * @param user
	 */
	@Override
	public void delete(Users user) {
		DoctorPatients doctorPatients;
		try{
			if(user.getRole() == "DOCTOR"){
				doctorPatients = doctorPatientsRepository.findByDoctorId(user.getId());
			} else {
				doctorPatients = doctorPatientsRepository.findByPatientId(user.getId());
			}
			doctorPatientsRepository.delete(doctorPatients);
		} catch (NullPointerException e) {
			// user did not have a doctor patient relationship.
		}
		try {
			Reminder reminder = reminderRepository.findByUsersId(user.getId());
			reminderRepository.delete(reminder);
		} catch (NullPointerException e){
			// User did not have a reminder service
		}
		try {
			Cabinet cab = cabinetRepository.findByUsersId(user.getId());
			cabinetRepository.delete(cab);
		} catch(NullPointerException e){
			// user did not have a cabinet
		}
		User_roles auth = userRolesRepository.findByUsername(user.getUsername());
		userRolesRepository.delete(auth);
		repository.delete(user);
	}
	
	/**
	 * Huge mess. Absolutely not up to safety standards. NEVER USE FOR PRODUCTION CODE
	 * Checks if the user exists by looking into database for the unique username,
	 * then checks if the user with that username matches the encrypted password.
	 * if correct, send the user data back.
	 * @param username username of user
	 * @param password password of user
	 * @return Returns possible user
	 * @throws NullPointerException if we catch a null.
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
			
			}
			
			return possibleUser;
		} catch (NullPointerException e){
		
		}
		return possibleUser;
	}
	
	/**
	 * Returns the user with matching username
	 * @param {String} username
	 * @return a user
	 */
	@Override
	public Users getUser(String username){
		return repository.getUser(username);
	}
	
	/**
	 * Returns the user with matching id
	 * @param {Long} id
	 * @return a user
	 */
	@Override
	public Users findOne(Long id){
		return repository.findOne(id);
	}
	
	/**
	 * Returns a list of users who are connected to a doctor
	 * @param id Id á lækninum
	 * @return list of users
	 */
	@Override
	public List<Users> findAllPatients(Long id) {
		
		return repository.findAllPatients(id);
	}
	
	/**
	 *
	 * @param id Finnur alla usera sem eru læknar.
	 * @return
	 */
	@Override
	public List<Users> findDoctor(Long id){
		return repository.findDoctor(id);
	}
	
	/**
	 *
	 * @return all users
	 */
	@Override
	public List<Users>findAll(){return repository.findAll();}
	
	/**
	 * Adds a patient, used with Doctor/Patients.
	 * @param user
	 * @return
	 */
	@Override
	public String addPatient(Users user) {
		return repository.addPatient(user);
	}
	
	/**
	 * used to modify a patients profile.
	 * @param user
	 * @return
	 */
	@Override
	public String editPatient(Users user) {
		return repository.editPatient(user);
	}
	
	/**
	 * I have no idea what this returns.
	 * This method should not be returning a string.
	 * @param username
	 * @return
	 */
	@Override
	public String findByUsername(String username) {
		
		return repository.findByUsername(username);
	}
	
	/**
	 * Returns true if a user with said username exists
	 * Works due to all usernames in our database being unique.
	 * @param username
	 * @return
	 */
	@Override
	public Boolean [] userNameExists(String username){
		return repository.userNameExists(username);
	}
	
	/**
	 * Updates image for a user, finding them via username
	 * @param imageId
	 * @param userName
	 */
	public void updateImageId(String imageId, String userName){
		repository.updateImageId(imageId,userName);
	}
	
	/**
	 * Returns a list of users found connected to a list of ids.
	 * @param userids
	 * @return
	 */
	public List<Users> getUsersById(List<Long> userids){return repository.getUsersById(userids);	}
	
	/**
	 * Returns a user by their username, TODO, this is a duplicate of another repository.
	 * @param username
	 * @return
	 */
	public Users getUsersByUsername(String username){return repository.getUsersByUsername(username);}

	public List<Users> getPatients(){ return repository.getPatients();}
	
	/**
	 * Changes the boolean value in the table Users to true
	 * since the user has confirmed their email.
	 * @param id id of possible user, pushed in with email.
	 */
	public void confirmEmail(String id){
		repository.confirmEmail(id);
	}
}
