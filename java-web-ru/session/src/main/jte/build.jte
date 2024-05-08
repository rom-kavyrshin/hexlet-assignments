@import exercise.util.NamedRoutes
@import exercise.dto.LoginPage
@param LoginPage page

@template.layout.page(content = @`
    @if(page.getValidationErrors() != null)
        <ul>
            @for(var validationErrors : page.getValidationErrors().values())
                @for(var error: validationErrors)
                    <li>${error.getMessage()}</li>
                @endfor
            @endfor
        </ul>
    @endif
    <form action="${NamedRoutes.loginPath()}" method="post">
        <div>
            <label>
                Nickname
                <input required type="text" name="name" value="${page.getName()}">
            </label>
        </div>
        <div>
            <label>
                Пароль
                <input type="password" name="password" required>
            </label>
        </div>
        <input type="submit" value="Войти">
    </form>
`)