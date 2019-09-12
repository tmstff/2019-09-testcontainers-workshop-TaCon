/*
package de.tarent.youtrainserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
*/

/**
 * The security config to prevent access to parts of the REST API unless you have the right to do so.
 * <p>
 * TODO mvz: This is work in progress ...
 *
 * @author Mark Vz
 */
/*
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.
//                portMapper()
//                .http(8080).mapsTo(8090);

        http
                .authorizeRequests()
                .antMatchers("/courses/**").permitAll();
//                // .antMatchers("/user/**").hasRole("USER")
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .failureUrl("/login-error");
    }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
//                .withUser("user").password("password").roles("USER");
//    }
}
*/
