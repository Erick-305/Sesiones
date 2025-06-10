package org.erick.ManejodeSesiones.repositorio;

import org.erick.ManejodeSesiones.models.Articulo;
import org.erick.ManejodeSesiones.models.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticuloRepositoryJdbcImplement implements Repository<Articulo> {

    private Connection conn;

    public ArticuloRepositoryJdbcImplement(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Articulo> listar() throws SQLException {
        List<Articulo> articulos = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM articulo WHERE condicion=1")) {
            while (rs.next()) {
                Articulo a = getArticulo(rs);
                articulos.add(a);
            }
        }
        return articulos;
    }

    @Override
    public Articulo porId(Long id) throws SQLException {
        Articulo articulo = null;
        try (PreparedStatement stmt = conn.prepareStatement(
                "SELECT * FROM articulo WHERE idarticulo=?")) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    articulo = getArticulo(rs);
                }
            }
        }
        return articulo;
    }

    @Override
    public void guardar(Articulo articulo) throws SQLException {
        String sql;
        if (articulo.getIdarticulo() != null && articulo.getIdarticulo() > 0) {
            sql = "UPDATE articulo SET idcategoria=?, codigo=?, nombre=?, stock=?, descripcion=?, imagen=? WHERE idarticulo=?";
        } else {
            sql = "INSERT INTO articulo(idcategoria, codigo, nombre, stock, descripcion, imagen, condicion) VALUES (?, ?, ?, ?, ?, ?, 1)";
        }
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, articulo.getIdcategoria());
            stmt.setString(2, articulo.getCodigo());
            stmt.setString(3, articulo.getNombre());
            stmt.setDouble(4, articulo.getStock());
            stmt.setString(5, articulo.getDescripcion());
            stmt.setString(6, articulo.getImagen());
            if (articulo.getIdarticulo() != null && articulo.getIdarticulo() > 0) {
                stmt.setLong(7, articulo.getIdarticulo());
            }
            stmt.executeUpdate();
        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        // Implementación opcional: puedes dejarlo vacío o implementar borrado lógico
    }

    private static Articulo getArticulo(ResultSet rs) throws SQLException {
        Articulo a = new Articulo();
        a.setIdarticulo(rs.getLong("idarticulo"));
        a.setIdcategoria(rs.getLong("idcategoria"));
        a.setCodigo(rs.getString("codigo"));
        a.setNombre(rs.getString("nombre"));
        a.setStock(rs.getDouble("stock"));
        a.setDescripcion(rs.getString("descripcion"));
        a.setImagen(rs.getString("imagen"));
        a.setCondicion(rs.getInt("condicion"));
        return a;
    }
}