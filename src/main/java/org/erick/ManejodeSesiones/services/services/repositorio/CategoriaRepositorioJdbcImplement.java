package org.erick.ManejodeSesiones.services.services.repositorio;


import org.erick.ManejodeSesiones.services.services.models.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaRepositorioJdbcImplement implements Repositorio<Categoria> {

    //Creamos una variable donde vamos a guardar la conexion
    private Connection conn;
    public CategoriaRepositorioJdbcImplement(Connection conn) {
        this.conn = conn;
    }
    @Override
    public List<Categoria> listar() throws SQLException {
        List<Categoria> categorias = new ArrayList<>();
        try(Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM categoria")){
            while(rs.next()){
                Categoria c = getCategoria(rs);
                categorias.add(c);
            }
        }
        return categorias;
    }

    @Override
    public Categoria porId(int id) throws SQLException {
        //Crea un objeto de tipo categoria null
        Categoria categoria = null;
        try(PreparedStatement stmt = conn.prepareStatement("SELECT * FROM categoria WHERE id = ?")){
            stmt.setInt(1, id);
            try(ResultSet rs = stmt.executeQuery()){
                categoria = getCategoria(rs);
            }
        }
        return categoria;
    }

    @Override
    public void guardar(Categoria categoria) throws SQLException {

        //Declaro una variable de tipo String
        String sql;
        if(categoria.getIdCategoria()>0){
            sql="update categoria set nombre=?, descripcion=? where idCategoria=?";
        }else{
            sql="insert into categoria(nombre, descripcion, condicion)values(?,?,1)";
        }
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, categoria.getNombre());
            stmt.setString(2, categoria.getDescripcion());
            stmt.setInt(3, categoria.getIdCategoria());
            stmt.executeUpdate();
        }
    }

    @Override
    public void eliminar(int id) throws SQLException {

    }
    private static Categoria getCategoria(ResultSet rs) throws SQLException {
        Categoria c = new Categoria();
        c.setNombre(rs.getString("nombre"));
        c.setDescripcion(rs.getString("descripcion"));
        c.setCondicion(rs.getInt("condicion"));
        c.setIdCategoria(rs.getInt("idCategoria"));
        return c;
    }
}
