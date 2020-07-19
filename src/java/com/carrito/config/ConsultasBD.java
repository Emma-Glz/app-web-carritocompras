package com.carrito.config;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsultasBD {

    private static ResultSet rs;
    private static PreparedStatement ps;

    public static PreparedStatement preparedStatement(String sql) {
        try {
            ps = Conexion.getConnection().prepareStatement(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ps;
    }

    public static ResultSet resultSet(PreparedStatement ps) {
        try {
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
}
