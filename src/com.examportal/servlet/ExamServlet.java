package com.examportal.servlet;

import com.examportal.model.Question;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

public class ExamServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String subject = req.getParameter("subject");

        List<Question> questions = new ArrayList<>();
        if ("Math".equals(subject)) {
            questions.add(new Question("2 + 2 = ?", "3", "4", "5", "6", "4"));
            questions.add(new Question("5 * 6 = ?", "30", "35", "25", "20", "30"));
            // Add 5 more...
        } else if ("Science".equals(subject)) {
            questions.add(new Question("Water formula?", "H2O", "CO2", "O2", "H2SO4", "H2O"));
            questions.add(new Question("Earth is a?", "Star", "Planet", "Moon", "Comet", "Planet"));
            // Add 5 more...
        }

        HttpSession session = req.getSession();
        session.setAttribute("subject", subject);
        session.setAttribute("questions", questions);

        RequestDispatcher rd = req.getRequestDispatcher("exam.jsp");
        rd.forward(req, resp);
    }
}
