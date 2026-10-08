package ejemplo.persistencia;

import ejemplo.logica.Artista;
import ejemplo.logica.Cancion;

public class ControladoraPersistencia {
	// Artista: Creamos una instancia del controlador que sabe cómo hablar con la
	// base de datos
	ArtistaJpaController artistaJpa = new ArtistaJpaController();

	// Cancion: Creamos una instancia del controlador que sabe cómo hablar con la
	// base de datos
	// INSTANCIAR OBJETO DE CANCIONJPACONTROLER AL IGUAL QUE SE HIZO CON ARTISTA
	CancionJpaController cancionJpa = new CancionJpaController();
	// Este método es el que se usa desde otras capas para crear un ARTISTA
	public void crearArtista(Artista artista) {
		// Delegamos la tarea al controlador especializado
		artistaJpa.crear(artista);
	}
	public void crearCancion(Cancion cancion) {
		// Delegamos la tarea al controlador especializado
		cancionJpa.crear(cancion);
	}
// AL IGUAL QUE CON ARTISTA, GENERAR EL MÉTODO “crearCancion()”
}


