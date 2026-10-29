package com.app.servlets;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import com.app.logica.Artista;
import com.app.logica.ControladoraLogica;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "SvArtistaEditar", urlPatterns = { "/SvArtistaEditar" })
public class SvArtistaEditar extends HttpServlet {

    private static final String VISTA = "/WEB-INF/vistas/editarArtista.jsp";

    private ControladoraLogica control;

    @Override
    public void init() throws ServletException {
        control = new ControladoraLogica();
    }

    // GET: busca el artista, lo guarda en "artista" y hace forward al JSP de edición
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Artista artista;
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            artista = control.buscarUnArtista(id);
            if (artista == null) {
                redirigirConError(request, response, "El artista con id " + id + " no existe.");
                return;
            }
        } catch (NumberFormatException e) {
            redirigirConError(request, response, "El id del artista no es válido.");
            return;
        }

        request.setAttribute("artista", artista);
        request.getRequestDispatcher(VISTA).forward(request, response);
    }

    // POST: recibe el id y los datos, guarda los cambios y redirige al listado
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String destino = request.getContextPath() + "/SvArtistaListar";

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Artista artista = control.buscarUnArtista(id);
            if (artista == null) {
                throw new Exception("El artista con id " + id + " no existe.");
            }

            artista.setNombre(request.getParameter("nombre"));
            artista.setGeneroMusical(request.getParameter("generoMusical"));
            artista.setNacionalidad(request.getParameter("nacionalidad"));
            artista.setEdad(Integer.parseInt(request.getParameter("edad")));
            // fechaNacimiento se conserva como está guardada (el formulario no la envía)

            control.editarArtista(artista);

        } catch (NumberFormatException e) {
            destino += "?error=" + codificar("El id y la edad deben ser números válidos.");
        } catch (Exception e) {
            destino += "?error=" + codificar(e.getMessage());
        }

        response.sendRedirect(destino);
    }

    private void redirigirConError(HttpServletRequest request, HttpServletResponse response, String mensaje)
            throws IOException {
        response.sendRedirect(request.getContextPath() + "/SvArtistaListar?error=" + codificar(mensaje));
    }

    private String codificar(String mensaje) {
        return URLEncoder.encode(mensaje == null ? "Error inesperado." : mensaje, StandardCharsets.UTF_8);
    }
}