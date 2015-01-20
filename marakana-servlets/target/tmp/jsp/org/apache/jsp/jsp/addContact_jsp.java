package org.apache.jsp.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class addContact_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write(" \n");
      out.write("<html>\n");
      out.write("\n");
      out.write("<head>\n");
      out.write("<title>add contact</title>\n");
      out.write("\n");
      out.write("</head>\n");
      out.write("\n");
      out.write("<body>\n");
      out.write("<h1>add contact</h1>\n");
      out.write("\n");
      out.write("<form action=\"contact?add\" method=\"post\">\n");
      out.write("\n");
      out.write("<input type=\"hidden\" name=\"add\">\n");
      out.write("\n");
      out.write("<ul>\n");
      out.write("\n");
      out.write("\t<li>name: <input type=\"text\" name=\"name\"> </li>\n");
      out.write("\t<li>street: <input type=\"text\" name=\"street\"> </li>\n");
      out.write("\t<li>city: <input type=\"text\" name=\"city\"> </li>\n");
      out.write("\t<li>state: <input type=\"text\" name=\"state\"> </li>\t\n");
      out.write("\t<li>zip: <input type=\"text\" name=\"zip\"> </li>\t\t\n");
      out.write("</ul>\n");
      out.write("\n");
      out.write("<input type=\"submit\" value=\"add\">\n");
      out.write("\n");
      out.write("</form>\n");
      out.write("\n");
      out.write("<a href=\"contacts\">back to contact list</a>\n");
      out.write("\n");
      out.write("</body></html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
