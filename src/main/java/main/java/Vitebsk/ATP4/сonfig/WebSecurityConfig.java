package main.java.Vitebsk.ATP4.сonfig;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import main.java.Vitebsk.ATP4.role.Role;
import main.java.Vitebsk.ATP4.role.User;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter 
{
	
	@Autowired
	private DataSource dataSource;
	
	

	@Override
	protected void configure(HttpSecurity http) throws Exception 
	{
		http
			.authorizeRequests()
				.antMatchers("/addnews").hasRole("Admin")
				.antMatchers("/", "/home", "/job", "/about_us", "/contacts", "/news", "/news/{id_post}", "/services", "/shareholders").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.and()
			.logout()
				.permitAll();
	}

	
    @Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception 
	{


        auth.jdbcAuthentication()
                .dataSource(dataSource)
				.passwordEncoder(NoOpPasswordEncoder.getInstance())
				
                .usersByUsernameQuery("select username, password, active from usr where username=?")
                .authoritiesByUsernameQuery("select u.username, ur.roles from usr u inner join user_role ur on u.id = ur.user_id where u.username=?");
    }



}