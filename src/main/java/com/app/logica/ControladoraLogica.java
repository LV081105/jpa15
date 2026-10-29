package com.app.logica;

import java.time.Year;
import java.util.List;

import com.app.persistencia.ControladoraPersistencia;

public class ControladoraLogica {

	// La controladora de persistencia se encarga de hablar con la BD.
	ControladoraPersistencia controlPersis = new ControladoraPersistencia();

	// ================= VALIDACIONES COMPARTIDAS =================
	// Las usan el alta y la edición, así las reglas son siempre las mismas.

	private void validarArtista(Artista art) throws Exception {
		if (art == null) {
			throw new Exception("El artista no puede ser nulo.");
		}
		// El nombre es obligatorio
		if (art.getNombre() == null || art.getNombre().trim().isEmpty()) {
			throw new Exception("El artista debe tener un nombre.");
		}
		// El género musical es obligatorio
		if (art.getGeneroMusical() == null || art.getGeneroMusical().trim().isEmpty()) {
			throw new Exception("El artista debe tener un género musical.");
		}
		// La nacionalidad solo puede tener letras (con tildes), espacios, puntos, apóstrofes y guiones
		if (art.getNacionalidad() == null
				|| !art.getNacionalidad().trim().matches("^[\\p{L}][\\p{L} .'-]*$")) {
			throw new Exception("La nacionalidad solo puede contener letras.");
		}
		// La edad debe ser positiva
		if (art.getEdad() <= 0) {
			throw new Exception("La edad del artista debe ser mayor a 0.");
		}
		// La fecha de nacimiento es obligatoria
		if (art.getFechaNacimiento() == null) {
			throw new Exception("El artista debe tener una fecha de nacimiento.");
		}
	}

	private void validarCancion(Cancion can) throws Exception {
		if (can == null) {
			throw new Exception("La canción no puede ser nula.");
		}
		// El título es obligatorio
		if (can.getTitulo() == null || can.getTitulo().trim().isEmpty()) {
			throw new Exception("La canción debe tener un título.");
		}
		// La duración debe ser mayor a cero (el formulario la pide en minutos)
		if (can.getDuracion() <= 0) {
			throw new Exception("La duración debe ser mayor a 0 minutos.");
		}
		// El año no puede ser anterior a 1900
		if (can.getAnio() < 1900) {
			throw new Exception("El año de la canción debe ser válido.");
		}
		// El año no puede ser futuro
		if (can.getAnio() > Year.now().getValue()) {
			throw new Exception("El año de la canción no puede ser futuro.");
		}
	}

	// ================= ARTISTA =================

	public void crearArtista(Artista art) throws Exception {
		validarArtista(art);
		controlPersis.crearArtista(art);
	}

	
	
	
	
	
	
	public void editarArtista(Artista art) throws Exception {
		// 1) Validar que exista
		if (art == null || controlPersis.buscarArtista(art.getId()) == null) {
			throw new Exception("El artista que quiere editar no existe.");
		}
		// 2) Mismas validaciones que el alta
		validarArtista(art);
		// 3) Recién ahí se guarda
		controlPersis.editarArtista(art);
	}

	
	
	public void eliminarArtista(int id) throws Exception {
		// Validar que exista antes de borrar
		if (controlPersis.buscarArtista(id) == null) {
			throw new Exception("El artista con id " + id + " no existe.");
		}
		controlPersis.eliminarArtista(id);
	}

	
	
	
	
	
	public Artista buscarUnArtista(int id) {
		return controlPersis.buscarArtista(id);
	}

	public List<Artista> listarArtistas() {
		return controlPersis.buscarTodosLosArtistas();
	}

	// ================= CANCIÓN =================

	public void crearCancion(Cancion can) throws Exception {
		validarCancion(can);
		controlPersis.crearCancion(can);
	}

	
	
	
	public void editarCancion(Cancion can) throws Exception {
		// 1) Validar que exista
		if (can == null || controlPersis.buscarCancion(can.getId()) == null) {
			throw new Exception("La canción que quiere editar no existe.");
		}
		// 2) Mismas validaciones que el alta
		validarCancion(can);
		// 3) Recién ahí se guarda
		controlPersis.editarCancion(can);
	}

	public void eliminarCancion(int id) throws Exception {
		// Validar que exista antes de borrar
		if (controlPersis.buscarCancion(id) == null) {
			throw new Exception("La canción con id " + id + " no existe.");
		}
		controlPersis.eliminarCancion(id);
	}

	
	
	
	
	public Cancion buscarUnaCancion(int id) {
		return controlPersis.buscarCancion(id);
	}

	public List<Cancion> listarCanciones() {
		return controlPersis.buscarTodasLasCanciones();
	}
}