<link rel="stylesheet" href="css/style.css">

<%@ page import="com.examportal.model.Question, java.util.List" %>
<%
    List<Question> questions = (List<Question>) session.getAttribute("questions");
%>
<div class="card">
    <!-- Your form or student info here -->
 
<form action="ResultServlet" method="post">
<% int i = 0;
   for (Question q : questions) { %>
    <p><%= q.getQuestion() %></p>
    <input type="radio" name="q<%=i%>" value="<%=q.getOptionA()%>"><%=q.getOptionA()%><br>
    <input type="radio" name="q<%=i%>" value="<%=q.getOptionB()%>"><%=q.getOptionB()%><br>
    <input type="radio" name="q<%=i%>" value="<%=q.getOptionC()%>"><%=q.getOptionC()%><br>
    <input type="radio" name="q<%=i%>" value="<%=q.getOptionD()%>"><%=q.getOptionD()%><br>
    <hr>
<% i++; } %>
    <button type="submit">Submit Exam</button>
</form>
</div>
