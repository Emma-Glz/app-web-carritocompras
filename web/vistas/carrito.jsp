<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" >
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css">
        <link href="css/estilos1.css" rel="stylesheet" type="text/css"/>
        <title>Carrito de Compras</title>
    </head>
    <body>       
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo03" aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>           
            <div class="collapse navbar-collapse" id="navbarTogglerDemo03">
                <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
                    <li class="nav-item active">
                        <a class="nav-link" href="#">SinFloo Store</a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="./Controlador?accion=Nuevo"><i class="fas fa-home"></i> Home<span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="./Controlador?accion=home"><i class="fas fa-plus-circle"></i> Ofertas del Dia</a>
                    </li>                   
                    <li class="nav-item">
                        <a class="nav-link" href="./Controlador?accion=home"><i class="fas fa-plus-circle"></i> Seguir Comprando</a>
                    </li>                  
                </ul>                                            
                <ul class="navbar-nav btn-group my-2 my-lg-0" role="group">
                    <a style="color: white; cursor: pointer" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="fas fa-user-tie"></i> Iniciar Session</a>
                    <div class="dropdown-menu text-center dropdown-menu-right">
                        <a class="dropdown-item" href="#"><img src="img/user.png" alt="60" height="60"/></a>                        
                        <a class="dropdown-item" href="#">Usuario</a>
                        <a class="dropdown-item" href="#" data-toggle="modal" data-target="#myModal">Usuario@Gamil.com</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="Controlador?accion=MisCompras">Mis Compras</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="./Controlador?accion=Salir"> <i class="fas fa-arrow-right"> Salir</i></a>                        
                    </div>
                </ul>  
            </div>
        </nav>
        <div class="container mt-4">
            <div class="d-flex">
                <h2>Carrito</h2>                       
            </div>                    
            <div class="row">             
                <div class="col-lg-9">                 
                    <table class="table table-responsive">
                        <thead class="table-primary">
                            <tr class="text-center">
                                <th>Item</th>                               
                                <th>Articulo</th>
                                <th>Descripcion</th>
                                <th>Precio</th>
                                <th>Cantidad</th>                       
                                <th>Total</th>                       
                                <th>Acciones</th>                       
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="c" items="${Carrito}" varStatus="iterator"> 
                                <tr class="text-center tr">
                                    <td>${iterator.index+1}</td>                                   
                                    <td>${c.getNombres()}
                                        <img src="${c.getImagen()}" width="130" height="110">
                                    </td>
                                    <td>${c.getDescripcion()}</td>
                                    <td>${c.getPrecioCompra()}</td>
                                    <td>        
                                        <input type="hidden" id="item1" value="${c.getIdProducto()}">
                                        <input type="number" min="1" max="10"  id="Cant" class=" form-control text-center" value="${c.getCantidad()}">
                                    </td>   
                                    <td>${c.getSubTotal()}</td>                           
                                    <td class="text-center">                                         
                                        <input type="hidden" id="item2" value="${c.getIdProducto()}">
                                        <a id="deleteItem" href="#" class="btn-outline-danger btn-sm"><i class="fas fa-trash-alt"></i></a>                                            
                                    </td>                           
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>                    
                </div>
                <div class="col-lg-3">                  
                    <div class="card">
                        <div class="card-header">
                            Generar Compra
                        </div>
                        <div class="card-body">
                            <label>Subtotal:</label>
                            <a class="form-control text-center"><i class="fas fa-dollar-sign h5"> ${totalPagar}0</i></a>
                            <label>Precio Envio:</label>
                            <a class="form-control text-center"><i class="fas fa-dollar-sign h5"> 0.00</i></a>
                            <label>Descuento:</label>
                            <a class="form-control text-center"><i class="fas fa-dollar-sign h5"> 0.00</i></a>
                            <label>Total a Pagar:</label>
                            <a class="form-control text-center"><i class="fas fa-dollar-sign h4 primary"> ${totalPagar}0</i></a>
                        </div>
                        <div class="card-footer">
                            <a href="Controlador?accion=GenerarCompra" class="btn btn-danger btn-block">Generar Compra</a>
                            <a href="#" class="btn btn-info btn-block">Realizar Pago</a>
                        </div>
                    </div>
                </div>
            </div>          
        </div>
        <script src="https://code.jquery.com/jquery-3.4.1.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <script src="js/funciones.js" type="text/javascript"></script>
    </body>
</html>
