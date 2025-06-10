package org.erick.ManejodeSesiones.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.erick.ManejodeSesiones.services.ArticuloService;
import org.erick.ManejodeSesiones.services.ArticuloServiceJdbcImplement;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/articulo/desactivar")
public class ArticuloDesactivar extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        ArticuloService service = new ArticuloServiceJdbcImplement(conn);
        Long id = Long.parseLong(req.getParameter("id"));
        service.cambiarCondicion(id, 0); // 0 = desactivado
        resp.sendRedirect(req.getContextPath() + "/articulo");
    }
}
