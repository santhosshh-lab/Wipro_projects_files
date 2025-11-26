package com.example.contactmanager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "ContactServlet", urlPatterns = {"/contacts"})
public class ContactServlet extends HttpServlet {
    private ContactDAO dao = ContactDAO.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "new":
                req.getRequestDispatcher("/WEB-INF/views/add.jsp").forward(req, resp);
                break;
            case "edit":
                String idStr = req.getParameter("id");
                if (idStr != null) {
                    Contact c = dao.find(Integer.parseInt(idStr));
                    req.setAttribute("contact", c);
                }
                req.getRequestDispatcher("/WEB-INF/views/edit.jsp").forward(req, resp);
                break;
            case "delete":
                String did = req.getParameter("id");
                if (did != null) {
                    dao.delete(Integer.parseInt(did));
                }
                resp.sendRedirect(req.getContextPath() + "/contacts");
                break;
            default:
                req.setAttribute("contacts", dao.list());
                req.getRequestDispatcher("/WEB-INF/views/list.jsp").forward(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        if (action == null) action = "create";
        if ("create".equals(action)) {
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String phone = req.getParameter("phone");
            Contact c = new Contact(0, name, email, phone);
            dao.add(c);
            resp.sendRedirect(req.getContextPath() + "/contacts");
        } else if ("update".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String phone = req.getParameter("phone");
            Contact c = new Contact(id, name, email, phone);
            dao.update(c);
            resp.sendRedirect(req.getContextPath() + "/contacts");
        } else {
            resp.sendRedirect(req.getContextPath() + "/contacts");
        }
    }
}
