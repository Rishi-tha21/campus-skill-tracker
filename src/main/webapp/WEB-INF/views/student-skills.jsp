<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Skill Directory - Campus Skill Tracker</title>
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
            <h1>🔗 Campus Skill Directory</h1>
            <p>Results from custom INNER JOIN query — Students who have at least one skill</p>
        </div>

        <div class="card">
            <div class="card-header-row">
                <h2>Student–Skill Report</h2>
                <span class="badge-count">${studentSkills.size()} records</span>
            </div>

            <c:choose>
                <c:when test="${empty studentSkills}">
                    <div class="empty-state">
                        No student-skill records found. Add skills to students first.
                        <br/><a href="/students" class="btn btn-primary" style="margin-top:15px;">Go to Students</a>
                    </div>
                </c:when>
                <c:otherwise>
                    <table class="data-table">
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>Student Name</th>
                                <th>Department</th>
                                <th>Skill</th>
                                <th>Level</th>
                                <th>Certificate By</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%-- EL: ${dto.fullName} reads from StudentSkillDTO projection interface --%>
                            <c:forEach var="dto" items="${studentSkills}" varStatus="status">
                                <tr>
                                    <td>${status.count}</td>
                                    <td><strong>${dto.fullName}</strong></td>
                                    <td><span class="badge">${dto.department}</span></td>
                                    <td>${dto.skillName}</td>
                                    <td>
                                        <span class="level-badge level-${dto.skillLevel.toLowerCase()}">${dto.skillLevel}</span>
                                    </td>
                                    <td>${not empty dto.certificateProvider ? dto.certificateProvider : '—'}</td>
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
