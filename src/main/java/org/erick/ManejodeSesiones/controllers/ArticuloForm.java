package org.erick.ManejodeSesiones.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.erick.ManejodeSesiones.models.Articulo;
import org.erick.ManejodeSesiones.services.ArticuloService;
import org.erick.ManejodeSesiones.services.ArticuloServiceJdbcImplement;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/articulo/form")
public class ArticuloForm extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> errores = new HashMap<>();
        String idStr = req.getParameter("id");
        String idCategoriaStr = req.getParameter("idcategoria");
        String codigo = req.getParameter("codigo");
        String nombre = req.getParameter("nombre");
        String stockStr = req.getParameter("stock");
        String descripcion = req.getParameter("descripcion");
        String imagen = req.getParameter("imagen");

        // Validaciones
        if (idCategoriaStr == null || idCategoriaStr.isBlank()) errores.put("idcategoria", "La categoría es obligatoria");
        if (codigo == null || codigo.isBlank()) errores.put("codigo", "El código es obligatorio");
        if (nombre == null || nombre.isBlank()) errores.put("nombre", "El nombre es obligatorio");
        if (stockStr == null || stockStr.isBlank()) {
            errores.put("stock", "El stock es obligatorio");
        } else {
            try {
                Double.parseDouble(stockStr);
            } catch (NumberFormatException e) {
                errores.put("stock", "El stock debe ser un número");
            }
        }
        if (descripcion == null || descripcion.isBlank()) errores.put("descripcion", "La descripción es obligatoria");
        if (imagen == null || imagen.isBlank()) errores.put("imagen", "La imagen es obligatoria");

        Articulo articulo = new Articulo();
        // Si es edición, setear el id
        if (idStr != null && !idStr.isBlank()) {
            try {
                articulo.setIdarticulo(Long.parseLong(idStr));
            } catch (NumberFormatException ignored) {}
        }
        // Setear idcategoria si es válido
        if (idCategoriaStr != null && !idCategoriaStr.isBlank()) {
            try {
                articulo.setIdcategoria(Long.parseLong(idCategoriaStr));
            } catch (NumberFormatException ignored) {}
        }
        articulo.setCodigo(codigo);
        articulo.setNombre(nombre);
        if (stockStr != null && !stockStr.isBlank()) {
            try {
                articulo.setStock(Double.parseDouble(stockStr));
            } catch (NumberFormatException ignored) {}
        }
        articulo.setDescripcion(descripcion);
        articulo.setImagen(imagen);

        if (!errores.isEmpty()) {
            req.setAttribute("articulo", articulo);
            req.setAttribute("errores", errores);
            getServletContext().getRequestDispatcher("/formularioArticulo.jsp").forward(req, resp);
        } else {
            Connection conn = (Connection) req.getAttribute("conn");
            ArticuloService service = new ArticuloServiceJdbcImplement(conn);
            service.guardar(articulo);
            resp.sendRedirect(req.getContextPath() + "/articulo");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        Articulo articulo = new Articulo();
        if (idStr != null && !idStr.isBlank()) {
            try {
                Long id = Long.parseLong(idStr);
                Connection conn = (Connection) req.getAttribute("conn");
                ArticuloService service = new ArticuloServiceJdbcImplement(conn);
                articulo = service.porId(id).orElse(new Articulo());
            } catch (NumberFormatException ignored) {}
        }
        req.setAttribute("articulo", articulo);
        getServletContext().getRequestDispatcher("/formularioArticulo.jsp").forward(req, resp);
    }
}
