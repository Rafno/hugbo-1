package project.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
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
	
	/**
	 * Handler for all our links,
	 * important! must add new antmatcher with permissions needed if a new link is created (ie controller)
	 * only an authenticated user (ie logged in user) can view myHome.
	 * @param http
	 * @throws Exception
	 */
	
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		
		http
			.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/about").permitAll()
			.antMatchers("/login").permitAll()
			.antMatchers("/register").anonymous()
			.antMatchers("/myHome").hasAuthority("USER").anyRequest().authenticated()
			.and()
			.formLogin()
			.loginPage("/login")
			.defaultSuccessUrl("/myHome")
			.and()
			.httpBasic()
			
			.and()
			.logout()
			.logoutUrl("/logout")
			.deleteCookies("JSESSIONID")
			
			.and()
			.exceptionHandling()
			.accessDeniedPage("/error")
			
			.and()
			.csrf().disable();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception{
		web
			.ignoring()
			.antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/img/**");
	}
	
}