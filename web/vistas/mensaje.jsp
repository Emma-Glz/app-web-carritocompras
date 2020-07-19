<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container mt-4">
            <div class="col-sm-8">
                <div class="${alert}" role="alert">
                    <h4 class="alert-heading">${mensaje}</h4>
                    <hr>
                    <a href="${URL}" class="btn btn-outline-danger">Volver a Inicio</a>
                </div>
            </div>
        </div>
    </body>
</html>


