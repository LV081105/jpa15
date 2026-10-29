package com.app.servlets;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import com.app.logica.ControladoraLogica;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "SvArtistaEliminar", urlPatterns = { "/SvArtistaEliminar" })
public class SvArtistaEliminar extends HttpServlet {

    private ControladoraLogica control;

    @Override
    public void init() throws ServletException {
        control = new ControladoraLogica();
    }

    // POST: recibe el id, da de baja y redirige al listado
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String destino = request.getContextPath() + "/SvArtistaListar";

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            control.eliminarArtista(id);
        } catch (NumberFormatException e) {
            destino += "?error=" + codificar("El id del artista no es válido.");
        } catch (Exception e) {
            destino += "?error=" + codificar(e.getMessage());
        }

        response.sendRedirect(destino);
    }

    private String codificar(String mensaje) {
        return URLEncoder.encode(mensaje == null ? "Error inesperado." : mensaje, StandardCharsets.UTF_8);
    }
}