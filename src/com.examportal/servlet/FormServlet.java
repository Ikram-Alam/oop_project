package com.examportal.servlet;

import com.examportal.model.Student;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.UUID;

public class FormServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String contact = req.getParameter("contact");
        String dept = req.getParameter("department");

        String rollNo = "R-" + UUID.randomUUID().toString().substring(0, 6);
        Student student = new Student(name, email, contact, dept, rollNo);

        HttpSession session = req.getSession();
        session.setAttribute("student", student);

        RequestDispatcher rd = req.getRequestDispatcher("studentCard.jsp");
        rd.forward(req, resp);
    }
}
