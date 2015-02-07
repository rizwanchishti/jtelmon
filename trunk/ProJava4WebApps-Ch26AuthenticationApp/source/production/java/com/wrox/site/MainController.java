/*
 * http://localhost:8080/authentication/login
 * http://java.dzone.com/articles/springmvc4-spring-data-jpa
 * http://www.sivalabs.in/2014/03/springmvc4-spring-data-jpa.html
 * http://www.javacodegeeks.com/2014/03/springmvc4-spring-data-jpa-springsecurity-configuration-using-javaconfig.html
 * https://github.com/sivaprasadreddy/sivalabs-blog-samples-code/tree/master/springmvc-datajpa-security-demo
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
