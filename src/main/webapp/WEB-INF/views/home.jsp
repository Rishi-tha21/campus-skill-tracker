<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Campus Skill Tracker - Dashboard</title>
    <link rel="stylesheet" href="<c:url value='/resources/css/style.css' />">
</head>
<body>
    <!-- NAVBAR -->
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
            <h1>📊 Dashboard</h1>
            <p>Welcome to the Campus Skill Tracker system</p>
        </div>

        <!-- STATS CARDS -->
        <div class="stats-grid">
            <div class="stat-card blue">
                <div class="stat-icon">👥</div>
                <div class="stat-value">${totalStudents}</div>
                <div class="stat-label">Total Students</div>
            </div>
            <div class="stat-card green">
                <div class="stat-icon">🛠️</div>
                <div class="stat-value">${totalSkills}</div>
                <div class="stat-label">Total Skills</div>
            </div>
        </div>

        <!-- QUICK ACTIONS -->
        <div class="card">
            <h2>Quick Actions</h2>
            <div class="button-row">
                <a href="/students/new" class="btn btn-primary">➕ Add New Student</a>
                <a href="/students" class="btn btn-secondary">📋 View All Students</a>
                <a href="/students/skills" class="btn btn-info">🔗 View Skill Directory (Inner Join)</a>
            </div>
        </div>
    </div>
</body>
</html>
