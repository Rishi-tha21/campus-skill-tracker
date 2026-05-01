<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Student - Campus Skill Tracker</title>
    <link rel="stylesheet" href="<c:url value='/resources/css/style.css' />">
</head>
<body>
    <nav class="navbar">
        <a href="/" class="navbar-brand">🎓 Campus Skill Tracker</a>
        <ul class="nav-links">
            <li><a href="/">Dashboard</a></li>
            <li><a href="/students">Students</a></li>
            <li><a href="/students/new">Add Student</a></li>
            <li><a href="/students/skills">Skill Directory</a></li>
        </ul>
    </nav>

    <div class="container">
        <div class="form-container">
            <div class="page-header">
                <h1>✏️ Edit Student</h1>
                <p>Update the details for <strong>${student.fullName}</strong></p>
            </div>

            <div class="card">
                <%-- Pre-fills the form with existing student data via EL (Expression Language) --%>
                <form:form action="/students/update/${student.id}" modelAttribute="student" method="post" class="form">
                    <form:hidden path="id"/>

                    <div class="form-group">
                        <label for="fullName">Full Name <span class="required">*</span></label>
                        <%-- EL: ${student.fullName} auto-populates via modelAttribute binding --%>
                        <form:input path="fullName" id="fullName" class="form-control"/>
                        <form:errors path="fullName" cssClass="field-error"/>
                    </div>

                    <div class="form-group">
                        <label for="email">Email Address <span class="required">*</span></label>
                        <form:input path="email" id="email" type="email" class="form-control"/>
                        <form:errors path="email" cssClass="field-error"/>
                    </div>

                    <div class="form-group">
                        <label for="department">Department <span class="required">*</span></label>
                        <form:input path="department" id="department" class="form-control"/>
                        <form:errors path="department" cssClass="field-error"/>
                    </div>

                    <div class="form-group">
                        <label for="yearOfStudy">Year of Study</label>
                        <form:input path="yearOfStudy" id="yearOfStudy" type="number" class="form-control"/>
                        <form:errors path="yearOfStudy" cssClass="field-error"/>
                    </div>

                    <div class="form-group">
                        <label for="phoneNumber">Phone Number</label>
                        <form:input path="phoneNumber" id="phoneNumber" class="form-control"/>
                    </div>

                    <div class="button-row">
                        <button type="submit" class="btn btn-primary">✅ Update Student</button>
                        <a href="/students" class="btn btn-secondary">Cancel</a>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</body>
</html>
