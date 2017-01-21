package sec.project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // no real security at the moment
        http.authorizeRequests()
                .antMatchers("/admin").authenticated()
                //this allows us the access all other resources unauthenticated
                .anyRequest().permitAll();
        
        http.formLogin()
                .permitAll();
        
        
        // This is the secured version
        /*
            http.authorizeRequests()

                            .antMatchers("/login", "/form", "/done", "/").permitAll()
                                .anyRequest().authenticated() 
                                        .and()
                                        .formLogin();
			 
       */
                
        
        
        //see https://docs.spring.io/spring-security/site/docs/current/reference/html/jc.html for more information
        //csrf detection disabled
        //http.csrf().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
