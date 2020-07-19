package com.carrito.modeloDAO;

import com.carrito.config.Conexion;
import com.carrito.config.ConsultasBD;
import com.carrito.modelo.Producto;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;

public class ProductoDAO {

    private final static Logger LOGGER = Logger.getLogger("bitacora.subnivel.Control");
    int r = 0;

    private ProductoDAO() {
    }

    public static ProductoDAO getProductoDAO() {
        return new ProductoDAO();
    }

    public Producto listarId(int id) {
        Producto p = new Producto();
        String sql = "select * from producto where IdProducto=?";
        try {
            PreparedStatement ps = ConsultasBD.preparedStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ConsultasBD.resultSet(ps);
            while (rs.next()) {
                p.setId(rs.getInt(1));
                p.setNombres(rs.getString(2));
                p.setImagen(rs.getString(3));
                /**p.setFoto(rs.getBinaryStream(3));**/
                p.setDescripcion(rs.getString(4));
                p.setPrecio(rs.getDouble(5));
                p.setStock(rs.getInt(6));
            }
        } catch (Exception e) {
            LOGGER.log(Level.INFO, "Error de {0}", e);
            LOGGER.log(Level.INFO, "{0}Error de ", e);
        }
        return p;
    }

    public List listar() {
        List lista = new ArrayList();
        String sql = "select * from producto";
        try {
            PreparedStatement ps = ConsultasBD.preparedStatement(sql);
            ResultSet rs = ConsultasBD.resultSet(ps);
            while (rs.next()) {
                Producto p = new Producto();
                p.setId(rs.getInt(1));
                p.setNombres(rs.getString(2));
                p.setImagen(rs.getString(3));
                /**p.setFoto(rs.getBinaryStream(3));**/
                p.setDescripcion(rs.getString(4));
                p.setPrecio(rs.getDouble(5));
                p.setStock(rs.getInt(6));
                lista.add(p);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.INFO, "Error Al obtener datos de BD  {0}", e);
        }
        return lista;
    }

    public void listarImg(int id, HttpServletResponse response) {
        String sql = "select * from producto where IdProducto=" + id;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        response.setContentType("image/*");
        try {
            outputStream = response.getOutputStream();
            PreparedStatement ps = ConsultasBD.preparedStatement(sql);
            ResultSet rs = ConsultasBD.resultSet(ps);
            if (rs.next()) {
                inputStream = rs.getBinaryStream("Foto");
            }
            bufferedInputStream = new BufferedInputStream(inputStream);
            bufferedOutputStream = new BufferedOutputStream(outputStream);
            int i = 0;
            while ((i = bufferedInputStream.read()) != -1) {
                bufferedOutputStream.write(i);
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "{}",e);
        }
    }

    public int AgregarNuevoProducto(Producto p) {
        String sql = "insert into producto(Nombres,Foto,Descripcion,Precio,Stock)values(?,?,?,?,?)";
        try {
            PreparedStatement ps = Conexion.getConnection().prepareStatement(sql);
            ps.setString(1, p.getNombres());
            ps.setString(2, p.getImagen());
            ps.setString(3, p.getDescripcion());
            ps.setDouble(4, p.getPrecio());
            ps.setInt(5, p.getStock());
            r = ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE,"{}", e);
        }
        return r;
    }
}
