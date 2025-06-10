package org.erick.ManejodeSesiones.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.erick.ManejodeSesiones.models.Categoria;
import org.erick.ManejodeSesiones.services.CategoriaService;
import org.erick.ManejodeSesiones.services.CategoriaServiceJdbcImplement;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/categoria/reactivar")
public class CategoriaReactivar extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        CategoriaService service = new CategoriaServiceJdbcImplement(conn);
        List<Categoria> categorias = service.listarDesactivadas();
        req.setAttribute("categorias", categorias);
        getServletContext().getRequestDispatcher("/reactivarCategoria.jsp").forward(req, resp);
    }
}