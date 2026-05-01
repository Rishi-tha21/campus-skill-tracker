<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Skill - Campus Skill Tracker</title>
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
                <h1>🛠️ Add Skill</h1>
                <p>Adding skill for student: <strong>${student.fullName}</strong> (${student.department})</p>
            </div>

            <div class="card">
                <form:form action="/skills/add?studentId=${student.id}" modelAttribute="skill" method="post" class="form">

                    <div class="form-group">
                        <label for="skillName">Skill Name <span class="required">*</span></label>
                        <form:input path="skillName" id="skillName" class="form-control" placeholder="e.g. Java Programming"/>
                        <form:errors path="skillName" cssClass="field-error"/>
                    </div>

                    <div class="form-group">
                        <label for="skillLevel">Skill Level <span class="required">*</span></label>
                        <form:select path="skillLevel" id="skillLevel" class="form-control">
                            <form:option value="" label="-- Select Level --"/>
                            <form:option value="Beginner" label="Beginner"/>
                            <form:option value="Intermediate" label="Intermediate"/>
                            <form:option value="Advanced" label="Advanced"/>
                            <form:option value="Expert" label="Expert"/>
                        </form:select>
                        <form:errors path="skillLevel" cssClass="field-error"/>
                    </div>

                    <div class="form-group">
                        <label for="certificateProvider">Certificate Provider</label>
                        <form:input path="certificateProvider" id="certificateProvider" class="form-control"
                                   placeholder="e.g. Oracle, Coursera, Udemy"/>
                    </div>

                    <div class="button-row">
                        <button type="submit" class="btn btn-success">✅ Add Skill</button>
                        <a href="/students" class="btn btn-secondary">Cancel</a>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</body>
</html>
