package com.app.servlets;

import java.io.IOException;
import java.util.List;

import com.app.logica.Artista;
import com.app.logica.ControladoraLogica;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "SvArtistaListar", urlPatterns = { "/SvArtistaListar" })
public class SvArtistaListar extends HttpServlet {

    private static final String VISTA = "/WEB-INF/vistas/artistas.jsp";

    private ControladoraLogica control;

    @Override
    public void init() throws ServletException {
        control = new ControladoraLogica();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Artista> artistas = control.listarArtistas();
        request.setAttribute("artistas", artistas);
        request.getRequestDispatcher(VISTA).forward(request, response);
    }
}