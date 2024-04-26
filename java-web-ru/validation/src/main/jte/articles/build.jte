@import exercise.dto.articles.BuildArticlePage
@param BuildArticlePage page

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
    <form action="/articles" method="post">
        <div>
            <label>
                Название
                <input type="text" name="title" value="${page.getTitle()}" required>
            </label>
        </div>
        <div>
            <label>
                Описание
                <textarea name="content" required rows="10" cols="50">${page.getContent()}</textarea>
            </label>
        </div>
        <input type="submit" value="Добавить">
    </form>
`)