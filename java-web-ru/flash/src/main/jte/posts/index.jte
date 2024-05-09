@import exercise.dto.posts.PostsPage
@import exercise.util.NamedRoutes
@param PostsPage page

@template.layout.page(
page = page,
content = @`
    <div class="col-lg-8 mx-auto p-4 py-md-5">
        <main>
            <h1>Посты</h1>
            @if(page.getPostsList().isEmpty())
                <p>Пока не добавлено ни одного поста</p>
            @else
                <ul>
                    @for(var post : page.getPostsList())
                        <li>
                            <h2><a href="${NamedRoutes.postPath(post.getId())}">${post.getName()}</a></h2>
                            <p>${post.getBody()}</p>
                        </li>
                    @endfor
                </ul>
            @endif
        </main>
    </div>
`)