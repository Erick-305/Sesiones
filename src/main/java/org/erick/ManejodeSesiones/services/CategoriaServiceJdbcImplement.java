package org.erick.ManejodeSesiones.services;

import org.erick.ManejodeSesiones.models.Categoria;
import org.erick.ManejodeSesiones.repositorio.CategoriaEstadoRepository;
import org.erick.ManejodeSesiones.repositorio.CategoriaRepositoryJdbcImplement;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CategoriaServiceJdbcImplement implements CategoriaService {

    private CategoriaRepositoryJdbcImplement repositoryJdbc;
    private CategoriaEstadoRepository estadoRepository;

    public CategoriaServiceJdbcImplement(Connection conn) {
        this.repositoryJdbc = new CategoriaRepositoryJdbcImplement(conn);
        this.estadoRepository = new CategoriaEstadoRepository(conn);
    }

    @Override
    public List<Categoria> listar() {
        try {
            return repositoryJdbc.listar();
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public Optional<Categoria> porId(Long id) {
        try {
            return Optional.ofNullable(repositoryJdbc.porId(id));
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public void guardar(Categoria categoria) {
        try {
            repositoryJdbc.guardar(categoria);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public void cambiarCondicion(Long id, int condicion) {
        try {
            estadoRepository.cambiarCondicion(id, condicion);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<Categoria> listarDesactivadas() {
        try {
            return estadoRepository.listarDesactivadas();
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void desactivar(Long id) {

    }

    @Override
    public void activar(Long idCategoria) {

    }
}