package P1;

import java.util.GregorianCalendar;

/**
 * Cada uno de los cursos que organiza la academia.
 * 
 * @author oscafer
 * @author albguti
 * @author serorca
 * 
 */
public class Curso {

	private String idCurso;
	private String idioma;
	private int nivel;
	private GregorianCalendar fechaIni;
	private GregorianCalendar fechaFin;
	private GregorianCalendar horario;
	private int maxNalumnos;
	private double precio;
	
// Constructor
	/**
	 * Constructor de un curso
	 */
	public Curso(String idCurso, String idioma, int nivel, GregorianCalendar fechaIni, GregorianCalendar fechaFin, GregorianCalendar horario,  int maxNalumnos, double precio) {
		this.idCurso = idCurso;
		this.idioma = idioma;
		this.nivel = nivel;
		this.fechaIni = fechaIni;
		this.fechaFin = fechaFin;
		this.horario = horario;
		this.maxNalumnos = maxNalumnos;
		this.precio = precio;
		
	}

	/**
	 * Devuelve identificador del curso
	 * 
	 * @return identificador del curso
	 */
	public String getIdCurso() {
		return idCurso;
	}
	
	/**
	 * Devuelve el idioma del curso
	 * 
	 * @return idioma del curso
	 */
	public String getIdioma() {
		return idioma;
	}
	/**
	 * Cambia el idioma del curso
	 * 
	 * @param idioma
	 */
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	/**
	 * Devuelve el nivel del curso.
	 * 
	 * @return nivel del curso
	 */
	public int getNivel() {
		return nivel;
	}
	
	/**
	 * Cambia el nivel del curso
	 * 
	 * @param nivel
	 */
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	/**
	 * Devuelve la fecha de inicio del curso
	 * 
	 * @return fehaIni
	 */
	public GregorianCalendar getFechaIni() {
		return fechaIni;
	}
	/**
	 * Cambia la fecha de inicio del curso
	 * 
	 * @param fechaIni
	 */
	public void setFechaIni(GregorianCalendar fechaIni) {
		GregorianCalendar actual = (GregorianCalendar) GregorianCalendar.getInstance();
		if (actual.before(fechaIni)){
			this.fechaIni = fechaIni;
		} else {
			System.out.println("**AVISO** La fecha de inicio del curso "+ getIdCurso() + " no es valida.");
		}
	}
	
	/**
	 * Comprueba que la fecha de inicio no sea anterior a la actual.
	 * 
	 * @param ano
	 * @param mes
	 * @param dia
	 * 
	 * @return true/false
	 * 
	 */
	public boolean comprobarFechaIni(int ano,int mes,int dia){
		GregorianCalendar actual = (GregorianCalendar) GregorianCalendar.getInstance();
		GregorianCalendar inicio = new GregorianCalendar(ano, mes, dia);
		if(actual.before(inicio)){
			return true;
		}else {
		return false;
		}
	}
	
	/**
	 * Devuelve la fecha de fin del curso
	 * 
	 * @return fehaFin
	 */
	public GregorianCalendar getFechaFin() {
		return fechaFin;
	}
		
	/**
	 * Cambia la fecha de fin del curso
	 * 
	 * @param fechaFin
	 */
	public void setFechaFin(GregorianCalendar fechaFin) {
		if (getFechaIni().before(fechaFin)){
			this.fechaFin = fechaFin;
		}else {
			System.out.println("**AVISO** La fecha de finalizacion del curso "+ getIdCurso() + " no es valida.");
		}
	}
	
	/**
	 * Comprueba que la fecha de finalizacion sea posterior a la de inicio.
	 * 
	 * @param ano
	 * @param mes
	 * @param dia
	 * 
	 * @return true/false
	 * 
	 */
	public boolean comprobarFechaFin(int ano,int mes,int dia){
		GregorianCalendar inicio = getFechaIni();
		GregorianCalendar fin = new GregorianCalendar (ano, mes, dia);
		if(inicio.before(fin)){
			return true;
		} else {
			return false;
		}
	}
			
	/**
	 * Devuelve la hora en que se imparte el curso
	 * 
	 * @return horario
	 * 
	 */
	public GregorianCalendar getHorario() {
		return horario;
	}
	
	/**
	 * Cambia la hora en que se imparte el curso
	 * 
	 * @param horario
	 * 
	 */
	public void setHorario(GregorianCalendar horario) {
		this.horario = horario;
	}
	
	/**
	 * Devuelve el numero maximo de alumnos que se pueden matricular en el
	 * curso.
	 * 
	 * @return maxNalumnos
	 * 
	 */
	public int getMaxNalumnos() {
		return maxNalumnos;
	}
	
	/**
	 * Cambia el numero maximo de alumnos que se pueden matricular en el curso
	 * 
	 * @param maxNalumnos
	 * 
	 */
	public void setMaxNalumnos(int maxNalumnos) {
		this.maxNalumnos = maxNalumnos;
	}
		
	/**
	 * Devuelve el precio de matriculacion del curso.
	 * 
	 * @return precio 
	 * 
	 */
	public double getPrecio() {
		return precio;
	}
	
	/**
	 * Cambia el precio de matriculacion del curso.
	 * 
	 * @param precio
	 * 
	 */
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
}
