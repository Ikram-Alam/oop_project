<link rel="stylesheet" href="css/style.css">

<%@ page import="com.examportal.model.Student" %>
<%
    Student s = (Student) session.getAttribute("student");
    int score = (Integer) session.getAttribute("score");
    String grade = (String) session.getAttribute("grade");
%>
<div class="card">
    <!-- Your form or student info here -->
 
<h2>Result Card</h2>
<p>Name: <%= s.getName() %></p>
<p>Roll No: <%= s.getRollNo() %></p>
<p>Marks: <%= score %> / 7</p>
<p>Grade: <%= grade %></p>
</div>