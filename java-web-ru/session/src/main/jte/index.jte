@import exercise.util.NamedRoutes
@import exercise.dto.MainPage
@param MainPage page

@template.layout.page(
content = @`
    <h1 class="text-body-emphasis">Привет, Хекслет!</h1>
    @if(page.getCurrentUser() == null)
        <h2><a href="${NamedRoutes.buildSessionPath()}">Войти</a></h2>
    @else
        <p>Добро пожаловать ${page.getCurrentUser()}</p>
        <form action="${NamedRoutes.logoutPath()}" method="post">
            <input type="submit" value="Выйти">
        </form>
    @endif
`)