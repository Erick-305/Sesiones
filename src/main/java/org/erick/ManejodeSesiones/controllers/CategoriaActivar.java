package org.erick.ManejodeSesiones.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.erick.ManejodeSesiones.services.CategoriaService;
import org.erick.ManejodeSesiones.services.CategoriaServiceJdbcImplement;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/categoria/activar")
public class CategoriaActivar extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        CategoriaService service = new CategoriaServiceJdbcImplement(conn);
        Long id = Long.parseLong(req.getParameter("id"));
        service.cambiarCondicion(id, 1); // 1 = activo
        resp.sendRedirect(req.getContextPath() + "/categoria/reactivar");
    }
}