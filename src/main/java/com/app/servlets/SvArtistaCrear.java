package com.app.servlets;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import com.app.logica.Artista;
import com.app.logica.ControladoraLogica;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "SvArtistaCrear", urlPatterns = { "/SvArtistaCrear" })
public class SvArtistaCrear extends HttpServlet {

    private ControladoraLogica control;

    @Override
    public void init() throws ServletException {
        control = new ControladoraLogica();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String destino = request.getContextPath() + "/SvArtistaListar";

        try {
            String nombre = request.getParameter("nombre");
            String generoMusical = request.getParameter("generoMusical");
            String nacionalidad = request.getParameter("nacionalidad");
            int edad = Integer.parseInt(request.getParameter("edad"));

            // El formulario no envía fechaNacimiento, pero la lógica la exige.
            // Se calcula de forma aproximada a partir de la edad.
            Date fechaNacimiento = Date.from(LocalDate.now().minusYears(edad)
                    .atStartOfDay(ZoneId.systemDefault()).toInstant());

            Artista artista = new Artista(nombre, generoMusical, nacionalidad, edad, fechaNacimiento);
            control.crearArtista(artista);

        } catch (NumberFormatException e) {
            destino += "?error=" + codificar("La edad debe ser un número válido.");
        } catch (Exception e) {
            destino += "?error=" + codificar(e.getMessage());
        }

        response.sendRedirect(destino);
    }

    private String codificar(String mensaje) {
        return URLEncoder.encode(mensaje == null ? "Error inesperado." : mensaje, StandardCharsets.UTF_8);
    }
}