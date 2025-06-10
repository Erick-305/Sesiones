package org.erick.ManejodeSesiones.services;

import org.erick.ManejodeSesiones.models.Articulo;

import java.util.List;
import java.util.Optional;

public interface ArticuloService {
    List<Articulo> listar();
    Optional<Articulo> porId(Long id);
    void guardar(Articulo categoria);
    void cambiarCondicion(Long id, int condicion);
    List<Articulo> listarDesactivadas();
}
