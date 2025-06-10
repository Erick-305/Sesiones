package org.erick.ManejodeSesiones.repositorio;

import org.erick.ManejodeSesiones.models.Articulo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticuloEstadoRepository {
    private Connection conn;

    public ArticuloEstadoRepository(Connection conn) {
        this.conn = conn;
    }

    public void cambiarCondicion(Long id, int condicion) throws SQLException {
        String sql = "UPDATE articulo SET condicion=? WHERE idarticulo=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, condicion);
            stmt.setLong(2, id);
            stmt.executeUpdate();
        }
    }

    public List<Articulo> listarDesactivadas() throws SQLException {
        List<Articulo> articulos = new ArrayList<>();
        String sql = "SELECT * FROM articulo WHERE condicion=0";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Articulo a = new Articulo(
                        rs.getLong("idarticulo"),
                        rs.getLong("idcategoria"),
                        rs.getString("codigo"),
                        rs.getString("nombre"),
                        rs.getDouble("stock"),
                        rs.getString("descripcion"),
                        rs.getString("imagen"),
                        rs.getInt("condicion")
                );
                articulos.add(a);
            }
        }
        return articulos;
    }
}