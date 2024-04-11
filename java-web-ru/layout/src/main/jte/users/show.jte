@import exercise.dto.users.UserPage
@param UserPage page

@template.layout.page(content = @`
    <div class="col-lg-8 mx-auto p-4 py-md-5">
        <main>
            <h1>${page.getUser().getFirstName() + " " + page.getUser().getLastName()}</h1>
            <p><a href="mailto:${page.getUser().getEmail()}">${page.getUser().getEmail()}</a></p>
        </main>
    </div>
`)