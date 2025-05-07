<link rel="stylesheet" href="css/style.css">

<%@ page import="com.examportal.model.Student" %>
<%
    Student s = (Student) session.getAttribute("student");
%>
<div class="card">
    <!-- Your form or student info here -->
<h2>Student Card</h2>
<p>Name: <%= s.getName() %></p>
<p>Email: <%= s.getEmail() %></p>
<p>Contact: <%= s.getContact() %></p>
<p>Department: <%= s.getDepartment() %></p>
<p>Roll No: <%= s.getRollNo() %></p>
</div>
<form action="ExamServlet" method="post">
    Select Subject:
    <select name="subject">
        <option>Math</option>
        <option>Science</option>
    </select>
    <button type="submit">Start Exam</button>
</form>
