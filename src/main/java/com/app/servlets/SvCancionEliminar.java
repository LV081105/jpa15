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

@WebServlet(name = "SvCancionEliminar", urlPatterns = { "/SvCancionEliminar" })
public class SvCancionEliminar extends HttpServlet {

    private ControladoraLogica control;

    @Override
    public void init() throws ServletException {
        control = new ControladoraLogica();
    }

    // POST: recibe el id, da de baja y redirige al listado
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String destino = request.getContextPath() + "/SvCancionListar";

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            control.eliminarCancion(id);
        } catch (NumberFormatException e) {
            destino += "?error=" + codificar("El id de la canción no es válido.");
        } catch (Exception e) {
            destino += "?error=" + codificar(e.getMessage());
        }

        response.sendRedirect(destino);
    }

    private String codificar(String mensaje) {
        return URLEncoder.encode(mensaje == null ? "Error inesperado." : mensaje, StandardCharsets.UTF_8);
    }
}