<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error - Campus Skill Tracker</title>
    <link rel="stylesheet" href="<c:url value='/resources/css/style.css' />">
</head>
<body>
    <nav class="navbar">
        <a href="/" class="navbar-brand">🎓 Campus Skill Tracker</a>
        <ul class="nav-links">
            <li><a href="/">Dashboard</a></li>
            <li><a href="/students">Students</a></li>
        </ul>
    </nav>

    <div class="container">
        <div class="error-container">
            <div class="error-icon">⚠️</div>
            <h1>${not empty errorTitle ? errorTitle : 'Error'}</h1>
            <p class="error-msg">${not empty errorMessage ? errorMessage : 'An unexpected error occurred.'}</p>
            <div class="button-row">
                <a href="/students" class="btn btn-primary">← Back to Students</a>
                <a href="/" class="btn btn-secondary">Go to Dashboard</a>
            </div>
        </div>
    </div>
</body>
</html>
