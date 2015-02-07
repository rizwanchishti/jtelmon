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

package com.wrox.site;

import com.wrox.config.annotation.WebController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@WebController
public class MainController
{
    @RequestMapping({ "/signup", "/about", "/policies" })
    public String unprotectedUris()
    {
        return "unprotected";
    }

    @RequestMapping({ "/secure/*" })
    public String securePage()
    {
        return "secure";
    }

    @RequestMapping({ "/admin/*" })
    public String adminPage()
    {
        return "admin";
    }

    @RequestMapping(value = { "/login" }, method = RequestMethod.GET)
    public String login()
    {
        return "login";
    }
}
