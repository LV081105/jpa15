package ejemplo.persistencia;

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
}
