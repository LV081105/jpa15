package ejemplo.logica;

import java.util.List;

import ejemplo.persistencia.ControladoraPersistencia;

public class ControladoraLogica {

	// La controladora de persistencia se encarga de hablar con la BD.
	// Desde aquí la usamos como "puente" para guardar datos.
	ControladoraPersistencia controlPersis = new ControladoraPersistencia();

	// ----- ARTISTA -----
	// Método de la capa lógica para crear un artista.
	// Antes de guardarlo en la BD, se hacen validaciones.
	public void crearArtista(Artista art) throws Exception {
		// Validación: el artista debe tener un nombre
		if (art.getNombre() == null || art.getNombre().isEmpty()) {
			throw new Exception("El artista debe tener un nombre.");
		}
		// Validación: el artista debe tener un género musical
		if (art.getGeneroMusical() == null || art.getGeneroMusical().isEmpty()) {
			throw new Exception("El artista debe tener un género musical.");
		}
		// Validación: la edad debe ser positiva
		if (art.getEdad() <= 0) {
			throw new Exception("La edad del artista debe ser mayor a 0.");
		}

		// AGREGAR AL MENOS UNA VALIDACIÓN MAS
		// NUEVA VALIDACIÓN: Fecha de nacimiento no nula y en el pasado/presente
        if (art.getFechaNacimiento() == null) {
            throw new Exception("El artista debe tener una fecha de nacimiento.");
        }
		// Si pasa todas las validaciones, recién ahí se manda a la persistencia
		controlPersis.crearArtista(art);
	}

	// ----- CANCIÓN -----
	// Método de la capa lógica para crear una canción.
	// Se valida que tenga datos coherentes antes de persistirla.
	public void crearCancion(Cancion can) throws Exception {
		// Validación: el título no puede estar vacío
		if (can.getTitulo() == null || can.getTitulo().isEmpty()) {
			throw new Exception("La canción debe tener un título.");
		}
		// Validación: la duración debe ser mayor a cero
		if (can.getDuracion() <= 0) {
			throw new Exception("La duración debe ser mayor a 0 segundos.");
		}
		// Validación: el año debe tener sentido
		if (can.getAnio() < 1900) {
			throw new Exception("El año de la canción debe ser válido.");
		}
		// AGREGAR AL MENOS UNA VALIDACIÓN MAS
		//if (can.getAnio() > 2026) {
		//	throw new Exception("El año de la canción debe ser válido.");
		//}
		
		// Si pasa todas las validaciones, se guarda en la base de datos
		//controlPersis.crearCancion(can);
		controlPersis.crearCancion(can);
	}
	
	// BUSCAR UN ARTISTA por su id
	public Artista buscarUnArtista(int id) {
	return controlPersis.buscarArtista(id);
	}
	// LISTAR TODOS LOS ARTISTAS
	public List<Artista> listarArtistas() {
	return controlPersis.buscarTodosLosArtistas();
	}
	
	// BUSCAR UN cancion por su id
	public Cancion buscarUnaCancion(int id) {
	return controlPersis.buscarCancion(id);
	}
	// LISTAR TODOS LOS cancion
	public List<Cancion> listarCanciones() {
	return controlPersis.buscarTodasLasCanciones();
	}
}

