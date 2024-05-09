@import exercise.util.NamedRoutes
@import exercise.dto.BasePage
@import gg.jte.Content
@param Content content
@param BasePage page = null

<html lang="en">
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Хекслет Flash сообщения</title>
</head>
<body>
<header>
    <a href="${NamedRoutes.rootPath()}">
        Хекслет
    </a>
</header>
@if(page != null && page.getFlashMessage() != null)
    <div class="alert alert-${page.getFlashMessage().isSuccess() ? "success" : "danger"}" role="alert">${page.getFlashMessage().getMessage()}</div>
@endif
<div>
    ${content}
</div>
</body>
</html>
