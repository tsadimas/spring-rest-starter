package gr.hua.dit.ds.springdemo.config;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import gr.hua.dit.ds.springdemo.jwt.JwtAuthenticationEntryPoint;
import gr.hua.dit.ds.springdemo.jwt.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;
	
	
	 @Resource(name = "userService")
	    private UserDetailsService userDetailsService;

	    @Autowired
	    private JwtAuthenticationEntryPoint unauthorizedHandler;

	    
	    @Override
	    @Bean
	    public AuthenticationManager authenticationManagerBean() throws Exception {
	        return super.authenticationManagerBean();
	    }

	    @Autowired
	    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(userDetailsService)
	                .passwordEncoder(passwordEncoder());
	    }

	    @Bean
	    public JwtAuthenticationFilter authenticationTokenFilterBean() throws Exception {
	        return new JwtAuthenticationFilter();
	    }
	    
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		 auth.userDetailsService(userDetailsService)
         .passwordEncoder(passwordEncoder());
//		auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder())
//				.usersByUsernameQuery("select username,password, enabled from user where username=?")
//				.authoritiesByUsernameQuery("select username, authority from authorities where username=?");

	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// secures all REST endpoints under "/api/customers"
//		http.authorizeRequests()
//		.antMatchers(HttpMethod.GET, "/api/customers").hasAnyRole("EMPLOYEE","ADMIN")
//		.antMatchers(HttpMethod.GET, "/api/customers/**").hasRole("EMPLOYEE")
//		.antMatchers(HttpMethod.POST, "/api/customers").hasAnyRole("MANAGER", "ADMIN")
//		.antMatchers(HttpMethod.POST, "/api/customers/**").hasAnyRole("MANAGER", "ADMIN")
//		.antMatchers(HttpMethod.PUT, "/api/customers").hasAnyRole("MANAGER", "ADMIN")
//		.antMatchers(HttpMethod.PUT, "/api/customers/**").hasAnyRole("MANAGER", "ADMIN")
//		.antMatchers(HttpMethod.DELETE, "/api/customers/**").hasRole("ADMIN")
//		.and()
//		.httpBasic()
//		.and()
//		.csrf().disable()
//		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		
		 http.cors().and().csrf().disable().
         authorizeRequests()
         .antMatchers("/token/*", "/signup").permitAll()
         .anyRequest().authenticated()
         .antMatchers(HttpMethod.GET, "/api/customers").hasAnyRole("EMPLOYEE","ADMIN")
 		.antMatchers(HttpMethod.GET, "/api/customers/**").hasRole("EMPLOYEE")
 		.antMatchers(HttpMethod.POST, "/api/customers").hasAnyRole("MANAGER", "ADMIN")
 		.antMatchers(HttpMethod.POST, "/api/customers/**").hasAnyRole("MANAGER", "ADMIN")
 		.antMatchers(HttpMethod.PUT, "/api/customers").hasAnyRole("MANAGER", "ADMIN")
 		.antMatchers(HttpMethod.PUT, "/api/customers/**").hasAnyRole("MANAGER", "ADMIN")
 		.antMatchers(HttpMethod.DELETE, "/api/customers/**").hasRole("ADMIN")
         .and()
         .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
         .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		 http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
}
						
		
	
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
