package com.produit.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Configuration
@EnableWebSecurity
public class SecurityConfigs extends WebSecurityConfigurerAdapter {

    private final MyUserDetailsService myUserDetailsService;


    @Autowired
    public SecurityConfigs(MyUserDetailsService myUserDetailsService) {
        this.myUserDetailsService = myUserDetailsService;
    }


    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService).passwordEncoder(getBCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
   /*     http.formLogin().loginPage("/login");
        http.logout()
                .logoutSuccessUrl("/index");
//        http.authorizeRequests().anyRequest().permitAll();
        http.authorizeRequests().antMatchers("/admin/*").hasRole("ADMIN");
   */
        http.formLogin().loginPage("/login")
                .successForwardUrl("/")
                .usernameParameter("u")
                .passwordParameter("p")
                .failureForwardUrl("/login");
        http.logout().
                logoutRequestMatcher(new AntPathRequestMatcher("/log_out", "GET"))
                .logoutSuccessUrl("/")//si logoutSuccessHandler not found
                .logoutSuccessHandler((request, response, authentication) -> {
                    System.out.println("logout http = [" + http + "]");
                    request.setAttribute("logout", "true");
//                    response.sendRedirect("/login");
                    request.getRequestDispatcher("/login").forward(request, response);
                });
/* Toutes les requites HTTP avec URL /user/* nécssitent d’être authentifié avec
un utilisateur ayant le role USER */
        http.authorizeRequests().antMatchers("/user/*").hasAuthority("USER");
/* Toutes les requites HTTP avec URL /admin/* nécessitent d’être authentifié
avec un Utilisateur ayant le role ADMIN*/
        http.authorizeRequests().antMatchers("/admin/*").hasAuthority("ADMIN");
/* Si un Utilisateur tente d’accéder à une resource dont il n’a pas le droit,
il sera redirigué vers la page associée à l’action /403 */
        http.exceptionHandling().accessDeniedPage("/403");
    }
}


