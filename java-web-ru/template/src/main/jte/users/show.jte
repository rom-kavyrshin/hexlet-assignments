@import exercise.dto.users.UserPage
@param UserPage userPage

<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>${userPage.getUser().getFirstName()}</title>
</head>
<body>
<div class="col-lg-8 mx-auto p-4 py-md-5">
    <main>
        <h2>${userPage.getUser().getFirstName() + " " + userPage.getUser().getLastName()}</h2>
        <p><a href="mailto:${userPage.getUser().getEmail()}">${userPage.getUser().getEmail()}</a></p>
    </main>
</div>
</body>
</html>