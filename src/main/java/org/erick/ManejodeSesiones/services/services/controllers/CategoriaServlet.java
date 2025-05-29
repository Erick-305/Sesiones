package org.erick.ManejodeSesiones.services.services.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.erick.ManejodeSesiones.services.services.models.Categoria;
import org.erick.ManejodeSesiones.services.services.services.CategoriaService;
import org.erick.ManejodeSesiones.services.services.services.CategoriaServiceJdbcImplement;
import org.erick.ManejodeSesiones.services.services.services.LoginService;
import org.erick.ManejodeSesiones.services.services.services.LoginServiceSessionImplement;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

@WebServlet("/categoria")
public class CategoriaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Creamos la conexion
        Connection conn = (Connection) req.getAttribute("conn");
        //Creamos el nuevo objeto de Categoria
        CategoriaService service = new CategoriaServiceJdbcImplement(conn);
        Long id;
        //Validamos que el campo ingresado sea un numero
        try {
            //En la variable id guardamos lo que estamos mapeando por el metodo get id Categoria
            id = Long.parseLong(req.getParameter("idCategoria"));
        } catch (NumberFormatException e) {
            id = 0L;
            //Creamos un objeto Categoria vacio
            Categoria categorias = new Categoria();
            //Verificamos si el id >0
            if (id > 0) {
                //Creamos una variable de tipo optional para obtener la categoria por id
                Optional<Categoria> optionalCategoria = service.porId(id);
                //Si la variable optional esta presente obtenemos todos los valores
                if (optionalCategoria.isPresent()) {
                    categorias = optionalCategoria.get();
                }
            }
            //Seteamos los atributos en el alcance de request
            req.setAttribute("categorias", categorias);
            getServletContext().getRequestDispatcher("/formularioCategoria.jsp").forward(req, resp);
            List<Categoria> categorias1 = service.Listar();

            //Obtengo el username
            LoginService auth = new LoginServiceSessionImplement();
            Optional<String> username = auth.getUserName(req);

            //Seteamos los parametros
            req.setAttribute("categorias", categorias);
            req.setAttribute("username", username);
            //redireccionames a la vista de categoria
            getServletContext().getRequestDispatcher("/categoriaListar.jsp").forward(req, resp);
        }
    }
        //Sobrescribimos el metodo Post
        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            Connection conn = (Connection) req.getAttribute("conn");
            CategoriaService service = new CategoriaServiceJdbcImplement(conn);
            String nombre = req.getParameter("nombre");
            String descripcion = req.getParameter("descripcion");
            //Obtenemos el idCategoria
            Long idCategoria;
            try{
                idCategoria = Long.parseLong(req.getParameter("idCategoria"));
            }catch(NumberFormatException e){
                idCategoria = 0L;
            }
            Categoria categoria = new Categoria();
            categoria.setIdCategoria(idCategoria);
            categoria.setNombre(nombre);
            categoria.setDescripcion(descripcion);
            service.guardar(categoria);
            //Redireccionamos al listado para no nos ejecute el motodo doPost
            resp.sendRedirect(req.getContextPath()+"/categoria");

        }
}
