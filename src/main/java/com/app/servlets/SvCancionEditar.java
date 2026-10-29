package com.app.servlets;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import com.app.logica.Cancion;
import com.app.logica.ControladoraLogica;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "SvCancionEditar", urlPatterns = { "/SvCancionEditar" })
public class SvCancionEditar extends HttpServlet {

    private static final String VISTA = "/WEB-INF/vistas/editarCancion.jsp";

    private ControladoraLogica control;

    @Override
    public void init() throws ServletException {
        control = new ControladoraLogica();
    }

    // GET: busca la canción, la guarda en "cancion" y hace forward al JSP de edición
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Cancion cancion;
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            cancion = control.buscarUnaCancion(id);
            if (cancion == null) {
                redirigirConError(request, response, "La canción con id " + id + " no existe.");
                return;
            }
        } catch (NumberFormatException e) {
            redirigirConError(request, response, "El id de la canción no es válido.");
            return;
        }

        request.setAttribute("cancion", cancion);
        request.getRequestDispatcher(VISTA).forward(request, response);
    }

    // POST: recibe el id y los datos, guarda los cambios y redirige al listado
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String destino = request.getContextPath() + "/SvCancionListar";

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Cancion cancion = control.buscarUnaCancion(id);
            if (cancion == null) {
                throw new Exception("La canción con id " + id + " no existe.");
            }

            cancion.setTitulo(request.getParameter("titulo"));
            cancion.setDuracion(Float.parseFloat(request.getParameter("duracion")));
            cancion.setAnio(Integer.parseInt(request.getParameter("anio")));

            control.editarCancion(cancion);

        } catch (NumberFormatException e) {
            destino += "?error=" + codificar("El id, la duración y el año deben ser números válidos.");
        } catch (Exception e) {
            destino += "?error=" + codificar(e.getMessage());
        }

        response.sendRedirect(destino);
    }

    private void redirigirConError(HttpServletRequest request, HttpServletResponse response, String mensaje)
            throws IOException {
        response.sendRedirect(request.getContextPath() + "/SvCancionListar?error=" + codificar(mensaje));
    }

    private String codificar(String mensaje) {
        return URLEncoder.encode(mensaje == null ? "Error inesperado." : mensaje, StandardCharsets.UTF_8);
    }
}