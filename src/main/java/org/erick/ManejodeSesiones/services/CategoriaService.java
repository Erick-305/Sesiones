package org.erick.ManejodeSesiones.services;

import org.erick.ManejodeSesiones.models.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaService {
    List<Categoria> listar();
    Optional<Categoria> porId(Long id);
    void guardar(Categoria categoria);
    void cambiarCondicion(Long id, int condicion);
    List<Categoria> listarDesactivadas();

    void desactivar(Long id);

    void activar(Long idCategoria);
}