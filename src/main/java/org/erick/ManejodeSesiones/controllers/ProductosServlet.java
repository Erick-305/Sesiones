package org.erick.ManejodeSesiones.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.erick.ManejodeSesiones.models.Productos;
import org.erick.ManejodeSesiones.services.ProductoService;
import org.erick.ManejodeSesiones.services.ProductosServiceImplement;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


// Definición del Servlet que manejará la ruta /products
@WebServlet("/productos")
public class ProductosServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductoService service = new ProductosServiceImplement();
        List<Productos> productos = service.listar();

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        //Creo la plantilla html
        out.print("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset=\"utf-8\">");
        out.println("<title>Listar Producto</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Listar producto</h1>");
        out.println("<table>");
        out.println("<tr>");
        out.println("<th>ID PRODUCTO</th>");
        out.println("<th>NOMBRE</th>");
        out.println("<th>CATEGORIA</th>");
        out.println("<th>PRECIO</th>");
        out.println("</tr>");
        productos.forEach(p->{
            out.println("<tr>");
            out.println("<td>"+p.getIdProducto()+"</td>");
            out.println("<td>"+p.getNombreProducto()+"</td>");
            out.println("<td>"+p.getCategoria()+"</td>");
            out.println("<td>"+p.getPrecioProducto()+"</td>");
            out.println("</tr>");

        });
        out.println("<link href=\"" + req.getContextPath()+ "/bootstrap.min.css\" rel=\"stylesheet\">");
        out.println("</table>");
        out.println("</body>");
        out.println("</html>");
    }
}