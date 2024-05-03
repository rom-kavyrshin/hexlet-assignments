@import exercise.dto.posts.PostPage
@import exercise.util.NamedRoutes
@param PostPage page

@template.layout.page(content = @`
    <div class="col-lg-8 mx-auto p-4 py-md-5">
        <main>
            <a href="${NamedRoutes.postsPath()}">❮ Back</a>
            <h1>${page.getPost().getName()}</h1>
            <p>${page.getPost().getBody()}</p>
        </main>
    </div>
`)