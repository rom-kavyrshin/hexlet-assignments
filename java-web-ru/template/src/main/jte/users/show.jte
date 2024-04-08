@import exercise.dto.users.UserPage
@param UserPage userPage

<html lang="en">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
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