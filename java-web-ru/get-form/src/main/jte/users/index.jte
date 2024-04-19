@import exercise.dto.users.UsersPage
@param UsersPage usersPage

@template.layout.page(content = @`
    <div>
        <main>
            <form method="get" action="/users">
                <label >
                    Поиск
                    <input type="search" name="term" placeholder="Поиск" value="${usersPage.getTerm()}">
                </label>
                <input type="submit" value="Search">
            </form>
            <h1>Список пользователей</h1>
            @if(usersPage.getUsers().isEmpty())
                <p>Не найдено ни одного пользователя</p>
            @else
                <ul>
                    @for(var user : usersPage.getUsers())
                        <li>
                            <h2>${user.getFirstName() + " " + user.getLastName()}</h2>
                            <p><a href="mailto:${user.getEmail()}">${user.getEmail()}</a></p>
                        </li>
                    @endfor
                </ul>
            @endif
        </main>
    </div>
`)
