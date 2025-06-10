package org.erick.ManejodeSesiones.services;

import org.erick.ManejodeSesiones.models.Productos;
import java.util.List;

public interface ProductoService {
    // Método para obtener la lista de productos
    List<Productos>listar();
}