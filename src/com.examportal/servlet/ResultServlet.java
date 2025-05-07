package com.examportal.servlet;

import com.examportal.model.Question;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

public class ResultServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        List<Question> questions = (List<Question>) session.getAttribute("questions");

        int score = 0;
        for (int i = 0; i < questions.size(); i++) {
            String userAnswer = req.getParameter("q" + i);
            if (questions.get(i).getCorrect().equals(userAnswer)) {
                score++;
            }
        }

        session.setAttribute("score", score);
        session.setAttribute("grade", score >= 6 ? "A" : score >= 4 ? "B" : "C");

        RequestDispatcher rd = req.getRequestDispatcher("result.jsp");
        rd.forward(req, resp);
    }
}
