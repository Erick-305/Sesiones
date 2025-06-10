package org.erick.ManejodeSesiones.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.erick.ManejodeSesiones.models.Articulo;
import org.erick.ManejodeSesiones.services.ArticuloService;
import org.erick.ManejodeSesiones.services.ArticuloServiceJdbcImplement;
import org.erick.ManejodeSesiones.services.LoginService;
import org.erick.ManejodeSesiones.services.LoginServiceSessionImplement;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

@WebServlet("/articulo")
public class ArticuloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        ArticuloService service = new ArticuloServiceJdbcImplement(conn);
        List<Articulo> Articulo = service.listar();

        LoginService auth = new LoginServiceSessionImplement();
        Optional<String> userName = auth.getUserName(req);

        req.setAttribute("articulos", Articulo);
        req.setAttribute("username", userName);
        getServletContext().getRequestDispatcher("/articuloListar.jsp").forward(req, resp);
    }
}