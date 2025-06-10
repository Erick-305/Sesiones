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
import java.util.List;

@WebServlet("/articulo/reactivar")
public class ArticuloReactivar extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        ArticuloService service = new ArticuloServiceJdbcImplement(conn);
        List<Articulo> articulos = service.listarDesactivadas();
        req.setAttribute("articulos", articulos);
        getServletContext().getRequestDispatcher("/reactivarArticulo.jsp").forward(req, resp);
    }
}