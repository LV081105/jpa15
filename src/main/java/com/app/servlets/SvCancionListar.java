package com.app.servlets;

import java.io.IOException;
import java.util.List;

import com.app.logica.Cancion;
import com.app.logica.ControladoraLogica;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "SvCancionListar", urlPatterns = { "/SvCancionListar" })
public class SvCancionListar extends HttpServlet {

    private static final String VISTA = "/WEB-INF/vistas/canciones.jsp";

    private ControladoraLogica control;

    @Override
    public void init() throws ServletException {
        control = new ControladoraLogica();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Cancion> canciones = control.listarCanciones();
        request.setAttribute("canciones", canciones);
        request.getRequestDispatcher(VISTA).forward(request, response);
    }
}