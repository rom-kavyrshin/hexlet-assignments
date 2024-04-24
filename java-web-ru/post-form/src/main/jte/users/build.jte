@template.layout.page(content = @`
    <form action="/users" method="post">
        <div>
            <label>
                Имя
                <input type="text" name="name" required>
            </label>
        </div>
        <div>
            <label>
                Фамилия
                <input type="text" name="lastname" required>
            </label>
        </div>
        <div>
            <label>
                Email
                <input type="email" name="email" required>
            </label>
        </div>
        <div>
            <label>
                Пароль
                <input type="password" name="password" required>
            </label>
        </div>
        <input type="submit" value="Зарегистрировать">
    </form>
`)