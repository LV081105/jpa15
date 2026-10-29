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

@WebServlet(name = "SvCancionCrear", urlPatterns = { "/SvCancionCrear" })
public class SvCancionCrear extends HttpServlet {

    private ControladoraLogica control;

    @Override
    public void init() throws ServletException {
        control = new ControladoraLogica();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String destino = request.getContextPath() + "/SvCancionListar";

        try {
            String titulo = request.getParameter("titulo");
            float duracion = Float.parseFloat(request.getParameter("duracion"));
            int anio = Integer.parseInt(request.getParameter("anio"));

            // Se usa el constructor porque setDuracion(int) de Cancion truncaría los decimales.
            Cancion cancion = new Cancion(titulo, duracion, anio);
            control.crearCancion(cancion);

        } catch (NumberFormatException e) {
            destino += "?error=" + codificar("La duración y el año deben ser números válidos.");
        } catch (Exception e) {
            destino += "?error=" + codificar(e.getMessage());
        }

        response.sendRedirect(destino);
    }

    private String codificar(String mensaje) {
        return URLEncoder.encode(mensaje == null ? "Error inesperado." : mensaje, StandardCharsets.UTF_8);
    }
}