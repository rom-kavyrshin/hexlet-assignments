@import exercise.dto.users.UsersPage
@param UsersPage usersPage

<html lang="en">
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Хекслет</title>
</head>
<body>
<div class="col-lg-8 mx-auto p-4 py-md-5">
    <main>
        @if(usersPage.getUsers().isEmpty())
            <p>Пока не добавлено ни одного курса</p>
        @else
            <ul>
                @for(var user : usersPage.getUsers())
                    <li>
                        <h2><a href="./users/${user.getId()}">${user.getFirstName() + " " + user.getLastName()}</a></h2>
                    </li>
                @endfor
            </ul>
        @endif
    </main>
</div>
</body>
</html>