/*
 * http://localhost:8080/authentication/login
 * http://java.dzone.com/articles/springmvc4-spring-data-jpa
 * http://www.sivalabs.in/2014/03/springmvc4-spring-data-jpa.html
 * http://www.javacodegeeks.com/2014/03/springmvc4-spring-data-jpa-springsecurity-configuration-using-javaconfig.html
 * https://github.com/sivaprasadreddy/sivalabs-blog-samples-code/tree/master/springmvc-datajpa-security-demo
 * 
 * http://dtr-trading.blogspot.ro/2014/02/spring-mvc-4-security-part-1.html
 * http://dtr-trading.blogspot.ro/2014/04/spring-security-32-authorization-part-1.html
 * http://dtr-trading.blogspot.ro/search/label/Security
 * 
 * http://www.raistudies.com/spring-security-tutorial-acegi/
 * http://www.raistudies.com/spring-security-tutorial/authentication-authorization-spring-security-mysql-database/
 * 
 */

package com.wrox.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

@Configuration
@EnableWebMvcSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{
    @Override
    protected void configure(AuthenticationManagerBuilder builder)
            throws Exception
    {
        builder
                .inMemoryAuthentication()
                        .withUser("John")
                        .password("password")
                        .authorities("USER")
                    .and()
                        .withUser("Margaret")
                        .password("green")
                        .authorities("USER", "ADMIN");
    }

    @Override
    public void configure(WebSecurity security)
    {
        security.ignoring().antMatchers("/resource/**");
    }

    @Override
    protected void configure(HttpSecurity security) throws Exception
    {
        security
                .authorizeRequests()
                    .antMatchers("/signup", "/about", "/policies").permitAll()
                    .antMatchers("/secure/**").hasAuthority("USER")
                    .antMatchers("/admin/**").hasAuthority("ADMIN")
                    .anyRequest().authenticated()
                .and().formLogin()
                    .loginPage("/login").failureUrl("/login?error")
                    .defaultSuccessUrl("/secure/")
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .permitAll()
                .and().logout()
                    .logoutUrl("/logout").logoutSuccessUrl("/login?loggedOut")
                    .invalidateHttpSession(true).deleteCookies("JSESSIONID")
                    .permitAll()
                .and().sessionManagement()
                    .sessionFixation().changeSessionId()
                    .maximumSessions(1).maxSessionsPreventsLogin(true)
                .and().and().csrf().disable();
    }
}
