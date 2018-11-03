package project.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import project.service.Implementation.UserDetailServiceImplementation;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	/**
	 * Simple bean that is used to encrypt passwords
	 * @returns BCryptPasswordEncoder
	 */
	@Bean
	public BCryptPasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	
	/**
	 * UserdetailService creates a hardcoded user with the username user
	 *  and the password userPass
	 * @return
	 */
	@Autowired
	private UserDetailServiceImplementation userDetailsService;
	/*
	@Bean
	public UserDetailsService userDetailsService()
	{
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(org.springframework.security.core.userdetails.User
			.withUsername(userDetailsService().loadUserByUsername())
			.password(passwordEncoder().encode("userPass"))
			.roles("USER")
			.build());
		
		return manager;
	}*/
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http
			.authorizeRequests()
			.antMatchers("/myHome/**").hasRole("USER")
			
			.and()
			.formLogin()
			.loginPage("/login")
			.usernameParameter("username")//
			.passwordParameter("password")
			.and()
			.httpBasic()
			
			.and()
			.logout()
			.logoutUrl("/user_logout")
			.deleteCookies("JSESSIONID")
			
			.and()
			.exceptionHandling()
			.accessDeniedPage("/403")
			
			.and()
			.csrf().disable();
	}
	
}