package org.erick.ManejodeSesiones.repositorio;

import org.erick.ManejodeSesiones.models.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaEstadoRepository {
    private Connection conn;

    public CategoriaEstadoRepository(Connection conn) {
        this.conn = conn;
    }

    public void cambiarCondicion(Long id, int condicion) throws SQLException {
        String sql = "UPDATE categoria SET condicion=? WHERE idcategoria=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, condicion);
            stmt.setLong(2, id);
            stmt.executeUpdate();
        }
    }

    public List<Categoria> listarDesactivadas() throws SQLException {
        List<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT * FROM categoria WHERE condicion=0";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Categoria c = new Categoria(
                        rs.getLong("idCategoria"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getInt("condicion")
                );
                categorias.add(c);
            }
        }
        return categorias;
    }
}