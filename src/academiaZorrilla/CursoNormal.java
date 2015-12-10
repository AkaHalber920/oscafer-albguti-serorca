package academiaZorrilla;

import java.util.GregorianCalendar;
/**
 * Curso para toda clase de alumnos
 * @author pcp
 *
 */
public class CursoNormal extends Curso{
// Atributos
	/**
	 * Nivel del idioma
	 */
	private int nivel;
// Constructor
	public CursoNormal(String idCurso, String idioma, int nivel, GregorianCalendar fechaIni, GregorianCalendar fechaFin, GregorianCalendar horario,  int maxNalumnos, double precio) {
		super( idCurso, idioma, fechaIni, fechaFin, horario, maxNalumnos, precio);
		this.nivel=nivel;
	}
// Metodos
	// Nivel
	/**
	 * Devuelve el nivel
	 * @return nivel
	 */
	public int getNivel() {
		return nivel;
	}
	/**
	 * Cambia el nivel
	 * @param nivel
	 */
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
}