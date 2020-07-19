package com.carrito.controlador;

import com.carrito.config.Fecha;
import com.carrito.modelo.Carrito;
import com.carrito.modelo.Cliente;
import com.carrito.modelo.Compra;
import com.carrito.modelo.Producto;
import com.carrito.modeloDAO.ProductoDAO;
import com.carrito.modeloDAO.CompraDAO;
import com.carrito.utils.Constants;
import com.carrito.utils.Utils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Controlador extends HttpServlet {

    ProductoDAO pdao = ProductoDAO.getProductoDAO();
    Producto p = new Producto();
    int cantidad = 1;
    double subtotal = 0.0;
    double totalPagar = 0.0;

    List<Carrito> listaCarrito = new ArrayList<>();
    List productos = new ArrayList();

    int idProducto = 0;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        productos = pdao.listar();
        String accion = request.getParameter("accion");

        switch (accion) {
            case "carrito":
                totalPagar = 0.0;
                request.setAttribute("Carrito", listaCarrito);
                for (int i = 0; i < listaCarrito.size(); i++) {
                    totalPagar = totalPagar + listaCarrito.get(i).getSubTotal();
                }
                request.setAttribute("totalPagar", totalPagar);
                Utils.distpatcherServlet(Constants.URL_CARRITO_INICIAL, request, response);
                break;
            case "Comprar":
                ControladorImplements.agregarCarrito(request, listaCarrito, subtotal);
                Utils.distpatcherServlet(Constants.URL_CARRITO, request, response);
                break;
            case "AgregarCarrito":
                ControladorImplements.agregarCarrito(request, listaCarrito, subtotal);
                request.setAttribute("cont", listaCarrito.size());
                Utils.distpatcherServlet(Constants.URL_HOME, request, response);
                break;
            case "deleteProducto":
                ControladorImplements.eliminarProducto(Integer.valueOf(request.getParameter("id")), listaCarrito);
                break;
            case "ActualizarCantidad":
                ControladorImplements.actualizarCantidad(
                        Integer.valueOf(request.getParameter("id")),
                        Integer.valueOf(request.getParameter("cantidad")),
                        listaCarrito);
                break;
            case "Nuevo":
                listaCarrito = new ArrayList();
                Utils.distpatcherServlet(Constants.URL_HOME, request, response);
                break;

            case "GenerarCompra":
                Cliente cliente = new Cliente();
                cliente.setId(1);
                CompraDAO dao = new CompraDAO();
                Compra compra = new Compra(cliente, 1, Fecha.FechaBD(), totalPagar, "Cancelado", listaCarrito);
                int res = dao.GenerarCompra(compra);
                if (res != 0 && totalPagar > 0) {
                    listaCarrito=new ArrayList<>();
                    ControladorImplements.response(Constants.URL_HOME, Constants.MESSAGE_SUCCESS, Constants.CONFIG_ALERT_SUCCESS, request);
                    Utils.distpatcherServlet(Constants.URL_MESSAGE, request, response);
                } else {
                    ControladorImplements.response(Constants.URL_HOME, Constants.MESSAGE_WARNING, Constants.CONFIG_ALERT_WARNING, request);
                    Utils.distpatcherServlet(Constants.URL_MESSAGE, request, response);
                }
                break;
            case "addNuevoProducto":
                Utils.distpatcherServlet(Constants.URL_ERROR, request, response);
                break;
            default:
                request.setAttribute("cont", listaCarrito.size());
                request.setAttribute("productos", productos);
                request.getRequestDispatcher(Constants.URL_INDEX).forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
