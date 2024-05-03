@import exercise.util.NamedRoutes
@import exercise.dto.posts.PostsPage
@param PostsPage page

@template.layout.page(content = @`
    <div class="col-lg-8 mx-auto p-4 py-md-5">
        <main>
            <h1>Посты</h1>
            @if(page.getPostList().isEmpty())
                <p>Пока не добавлено ни одного поста</p>
            @else
                <ul>
                    @for(var post : page.getPostList())
                        <li>
                            <h2><a href="${NamedRoutes.postPath(post.getId())}">${post.getName()}</a></h2>
                            <p>${post.getBody()}</p>
                        </li>
                    @endfor
                </ul>
                <div>
                    <a href="${
                        page.getCurrentPage() > 1 ?
                        NamedRoutes.postsPath(page.getCurrentPage() - 1) :
                        ""
                    }">❮ Previous</a>
                    <a href="${
                        page.isHasNextPage() ?
                        NamedRoutes.postsPath(page.getCurrentPage() + 1) :
                        ""
                    }">Next ❯</a>
                </div>
            @endif
        </main>
    </div>
`)