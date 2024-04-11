@import exercise.dto.users.UsersPage
@param UsersPage page

@template.layout.page(content = @`
    <div class="col-lg-8 mx-auto p-4 py-md-5">
        <main>
            @if(page.getUsers().isEmpty())
                <p>Пока не добавлено ни одного курса</p>
            @else
                <ul>
                    @for(var user : page.getUsers())
                        <li>
                            <h2><a href="/users/${user.getId()}">${user.getFirstName() + " " + user.getLastName()}</a></h2>
                        </li>
                    @endfor
                </ul>
            @endif
        </main>
    </div>
`)