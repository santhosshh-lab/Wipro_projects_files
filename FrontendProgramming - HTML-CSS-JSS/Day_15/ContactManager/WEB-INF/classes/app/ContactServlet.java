package app;
import java.io.*; 
import jakarta.servlet.*; 
import jakarta.servlet.http.*; 
import java.util.*;

public class ContactServlet extends HttpServlet {
  static List<Contact> contacts = new ArrayList<>();

  protected void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException {
    contacts.add(new Contact(req.getParameter("name"),req.getParameter("email"),req.getParameter("phone")));
    res.sendRedirect("list");
  }

  protected void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException {
    res.setContentType("text/html");
    PrintWriter out=res.getWriter();
    out.println("<h2>All Contacts</h2>");
    for(Contact c:contacts){
      out.println("<p>"+c.getName()+" - "+c.getEmail()+" - "+c.getPhone()+"</p>");
    }
    out.println("<a href='index.html'>Home</a>");
  }
}