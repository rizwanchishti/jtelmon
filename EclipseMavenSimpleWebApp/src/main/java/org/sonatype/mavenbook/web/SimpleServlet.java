/*
 * 
 * http://books.sonatype.com/mvnex-book/reference/web-sect-creating-project.html
 * http://www.mkyong.com/maven/how-to-create-a-web-application-project-with-maven
 * 
 * 
 */
package org.sonatype.mavenbook.web;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class SimpleServlet extends HttpServlet {
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
        throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        out.println( "SimpleServlet Executed" );
        out.flush();
        out.close();
    }
}
