<#assign sf=JspTaglibs["http://www.springframework.org/security/tags"]>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><@title></@title></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>


</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="/main">Home</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Menu
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="/main">Main</a></li>
                        <li><a class="dropdown-item" href="/users">Users</a></li>
                        <li><a class="dropdown-item" href="/forum">Forum</a></li>
                        <li><a class="dropdown-item" href="/test">Tests</a></li>
                        <@sf.authorize access="isAuthenticated()">
                            <li><a class="dropdown-item" href="/users/${username}">Personal account</a></li>
                            <li><a class="dropdown-item" href="/logout">Exit</a></li>
                        </@sf.authorize>
                        <@sf.authorize access="!isAuthenticated()">
                            <li><a class="dropdown-item" href="/registration">Sign up</a></li>
                            <li><a class="dropdown-item" href="/login">Sign in</a></li>
                        </@sf.authorize>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class="content1">
    <div class="content"><@content></@content></div>
</div>

</body>
</html>
