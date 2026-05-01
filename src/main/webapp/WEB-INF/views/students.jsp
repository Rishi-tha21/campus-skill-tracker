<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>All Students - Campus Skill Tracker</title>
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
        <div class="page-header">
            <h1>👥 All Students</h1>
        </div>

        <!-- SUCCESS MESSAGE -->
        <c:if test="${not empty successMessage}">
            <div class="alert alert-success">${successMessage}</div>
        </c:if>

        <div class="card">
            <div class="card-header-row">
                <h2>Student List</h2>
                <a href="/students/new" class="btn btn-primary">➕ Add New Student</a>
            </div>

            <c:choose>
                <c:when test="${empty students}">
                    <div class="empty-state">No students found. Add your first student!</div>
                </c:when>
                <c:otherwise>
                    <table class="data-table">
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>Full Name</th>
                                <th>Email</th>
                                <th>Department</th>
                                <th>Year</th>
                                <th>Phone</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="student" items="${students}" varStatus="status">
                                <tr>
                                    <td>${status.count}</td>
                                    <td><strong>${student.fullName}</strong></td>
                                    <td>${student.email}</td>
                                    <td><span class="badge">${student.department}</span></td>
                                    <td>Year ${student.yearOfStudy}</td>
                                    <td>${student.phoneNumber}</td>
                                    <td class="action-btns">
                                        <a href="/students/edit/${student.id}" class="btn btn-sm btn-warning">✏️ Edit</a>
                                        <a href="/skills/add?studentId=${student.id}" class="btn btn-sm btn-success">➕ Add Skill</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</body>
</html>
