package ejemplo.logica;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.io.Serializable;
import java.util.Date;

@Entity //Esta anotación marca a la clase Artista como una entidad, lo que
//significa que JPA creará una tabla en la base de datos para representarla.
public class Artista implements Serializable {

    @Id//Identifica el atributo id como la clave primaria de la tabla, un campo único
    //para identificar cada registro.
    
    //Le dice a la base de datos que genere automáticamente los
    //valores para el campo id. La estrategia IDENTITY es común para bases de
    //datos como MySQL, que usan un valor autoincremental.
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Basic
    private String nombre;
    private String generoMusical;
    private String nacionalidad;
    private int edad;
   
    
    //Esta anotación le indica a JPA cómo mapear el objeto
    //java.util.Date a un tipo de dato de fecha en SQL.
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    

    public Artista() { }

    public Artista(String nombre, String generoMusical, String nacionalidad, int edad, Date fechaNacimiento) {
        this.nombre = nombre;
        this.generoMusical = generoMusical;
        this.nacionalidad = nacionalidad;
        this.edad = edad;
        this.fechaNacimiento = fechaNacimiento;
    }

    // getters/setters
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getGeneroMusical() { return generoMusical; }
    public void setGeneroMusical(String generoMusical) { this.generoMusical = generoMusical; }
    public String getNacionalidad() { return nacionalidad; }
    public void setNacionalidad(String nacionalidad) { this.nacionalidad = nacionalidad; }
    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }
 // NUEVOS: Getters y Setters para fechaNacimiento
    public Date getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(Date fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

	@Override
	public String toString() {
		return "ARTISTA: \n" + "ID: " + getId() + "\nNombre: " + getNombre() + "\nGenero musical: " + getGeneroMusical()
				+ "\nNacionalidad: " + getNacionalidad() + "\nEdad: " + getEdad();
	}
}


