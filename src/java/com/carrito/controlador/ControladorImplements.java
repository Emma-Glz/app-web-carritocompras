package com.carrito.controlador;

import com.carrito.modelo.Carrito;
import com.carrito.modelo.Producto;
import com.carrito.modeloDAO.ProductoDAO;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class ControladorImplements {

    public static void actualizarCantidad(int idProducto, int cant, List<Carrito> listaCarrito) {
        for (int j = 0; j < listaCarrito.size(); j++) {
            if (listaCarrito.get(j).getIdProducto() == idProducto) {
                listaCarrito.get(j).setCantidad(cant);
                listaCarrito.get(j).setSubTotal(listaCarrito.get(j).getPrecioCompra() * cant);
            }
        }
    }

    public static void eliminarProducto(int idProducto, List<Carrito> listaCarrito) {
        if (listaCarrito != null) {
            for (int j = 0; j < listaCarrito.size(); j++) {
                if (listaCarrito.get(j).getIdProducto() == idProducto) {
                    listaCarrito.remove(j);
                }
            }
        }
    }

    public static void agregarCarrito(HttpServletRequest request, List<Carrito> listaCarrito, double subtotal) {
        Carrito car;
        int cantidad = 1;
        int pos = 0;
        int idpp = Integer.parseInt(request.getParameter("id"));
        if (listaCarrito.size() > 0) {
            for (int i = 0; i < listaCarrito.size(); i++) {
                if (listaCarrito.get(i).getIdProducto() == idpp) {
                    pos = i;
                }
            }
            if (idpp == listaCarrito.get(pos).getIdProducto()) {
                cantidad = listaCarrito.get(pos).getCantidad() + cantidad;
                subtotal = listaCarrito.get(pos).getPrecioCompra() * cantidad;
                listaCarrito.get(pos).setCantidad(cantidad);
                listaCarrito.get(pos).setSubTotal(subtotal);
            } else {
                car = new Carrito();
                Producto p = ProductoDAO.getProductoDAO().listarId(idpp);
                car.setIdProducto(p.getId());
                car.setNombres(p.getNombres());
                car.setImagen(p.getImagen());
                car.setDescripcion(p.getDescripcion());
                car.setPrecioCompra(p.getPrecio());
                car.setCantidad(cantidad);
                subtotal = cantidad * p.getPrecio();
                car.setSubTotal(subtotal);
                listaCarrito.add(car);
            }
        } else {
            car = new Carrito();
            Producto p = ProductoDAO.getProductoDAO().listarId(idpp);
            car.setIdProducto(p.getId());
            car.setNombres(p.getNombres());
            car.setImagen(p.getImagen());
            car.setDescripcion(p.getDescripcion());
            car.setPrecioCompra(p.getPrecio());
            car.setCantidad(cantidad);
            subtotal = cantidad * p.getPrecio();
            car.setSubTotal(subtotal);
            listaCarrito.add(car);
        }
    }

    public static void response(String url, String message, String alert,HttpServletRequest request) {
        request.setAttribute("URL", url);
        request.setAttribute("mensaje", message);
        request.setAttribute("alert", alert);
    }
}
