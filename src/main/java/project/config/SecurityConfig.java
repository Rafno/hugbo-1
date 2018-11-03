package project.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;


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
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		auth.jdbcAuthentication().dataSource(dataSource)
			.passwordEncoder(new BCryptPasswordEncoder())
			.authoritiesByUsernameQuery("select username, role from user_roles where username=?");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http
			.authorizeRequests()
			.antMatchers("/login*").anonymous()
			.anyRequest().authenticated()
			.and()
			.formLogin()
			.loginPage("/login")
			.defaultSuccessUrl("/")
			.failureUrl("/login.html?error=true")
			.and()
			.logout().logoutSuccessUrl("/login.html");
		
		
		/*
		http
			.authorizeRequests()
			.antMatchers("/myHome").hasRole("USER")
			
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
			*/
	}
	
}