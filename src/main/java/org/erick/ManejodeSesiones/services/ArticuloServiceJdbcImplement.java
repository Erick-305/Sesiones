package org.erick.ManejodeSesiones.services;

import org.erick.ManejodeSesiones.models.Articulo;
import org.erick.ManejodeSesiones.repositorio.ArticuloEstadoRepository;
import org.erick.ManejodeSesiones.repositorio.ArticuloRepositoryJdbcImplement;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ArticuloServiceJdbcImplement implements ArticuloService {

    private ArticuloRepositoryJdbcImplement repositoryJdbc;
    private ArticuloEstadoRepository estadoRepository;

    public ArticuloServiceJdbcImplement(Connection conn) {
        this.repositoryJdbc = new ArticuloRepositoryJdbcImplement(conn);
        this.estadoRepository = new ArticuloEstadoRepository(conn);
    }

    @Override
    public List<Articulo> listar() {
        try {
            return repositoryJdbc.listar();
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Articulo> porId(Long id) {
        try {
            return Optional.ofNullable(repositoryJdbc.porId(id));
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void guardar(Articulo articulo) {
        try {
            repositoryJdbc.guardar(articulo);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
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
    public List<Articulo> listarDesactivadas() {
        try {
            return estadoRepository.listarDesactivadas();
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }
}
