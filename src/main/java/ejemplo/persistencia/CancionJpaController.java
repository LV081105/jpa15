package ejemplo.persistencia;

import java.util.List;

import ejemplo.logica.Cancion;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class CancionJpaController {

	// "Fábrica" de conexiones con la base de datos.
	// Nos permite crear EntityManager, que son los que realmente hacen el trabajo.
	private EntityManagerFactory emf = null;

	// En el constructor indicamos qué unidad de persistencia usar.
	// El nombre "ejemplojpa" debe coincidir con el que está en persistence.xml.
	public CancionJpaController() {
		emf = Persistence.createEntityManagerFactory("ejemplojpa");
	}

	// Método que devuelve un nuevo EntityManager (una conexión lista para
	// trabajar).
	// Podés pensarlo como: "dame un empleado que se conecte a la BD".
	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	// Método para crear (persistir) una Canción en la base de datos.
	public void crear(Cancion canc) {
		EntityManager em = null;
		try {
			// Abrimos la conexión (pedimos un empleado).
			em = getEntityManager();

			// Iniciamos una transacción: empieza la "promesa".
			em.getTransaction().begin();

			// Guardamos el objeto en la base (pero todavía no está confirmado).
			em.persist(canc);

			// Confirmamos la transacción: ahora sí el objeto queda guardado.
			em.getTransaction().commit();
		} finally {
			// Cerramos la conexión (el empleado ya no hace falta).
			if (em != null) {
				em.close();
			}
		}
	}
	
	// BUSCAR UNO: busca un artista por su id.
	// No hace falta transacción porque no modifica nada en la base.
	public Cancion buscarUno(int id) {
	EntityManager em = getEntityManager();
	try {
	// find busca por clave primaria. Si no existe, devuelve null.
	return em.find(Cancion.class, id);
	} finally {
	em.close();
	}
	}
	// BUSCAR TODOS: devuelve una lista con todos los artistas.
	public List<Cancion> buscarTodos() {
	EntityManager em = getEntityManager();
	try {
	// Consulta JPQL: "traeme todos los objetos Artista"
	return em.createQuery("SELECT a FROM Cancion a", Cancion.class)
	.getResultList();
	} finally {
	em.close();
	}
	}
}
