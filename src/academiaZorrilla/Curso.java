package academiaZorrilla;


import java.util.GregorianCalendar;

/**
 * Curso que organiza la academia.
 * 
 * @author oscafer
 * @author alberto
 * @author sergio
 */
public class Curso {
// Atributos
	/**
	 * Código identificador
	 */
	protected String idCurso;
	/**
	 * Idioma.
	 */
	protected String idioma;
	/**
	 * Fecha de inicio.
	 */
	protected GregorianCalendar fechaIni;
	/**
	 * Fecha de fin.
	 */
	protected GregorianCalendar fechaFin;
	/**
	 * Hora en que se imparte.
	 */
	protected GregorianCalendar horario;
	/**
	 * Número de alumnos máximo.
	 */
	protected int maxNalumnos;
	/**
	 * Precio de matrícula.
	 */
	protected double precio;
// Constructor
	/**
	 * Constructor de un curso
	 */
	public Curso(String idCurso, String idioma, GregorianCalendar fechaIni, GregorianCalendar fechaFin, GregorianCalendar horario,  int maxNalumnos, double precio) {
		this.idCurso = idCurso;
		this.idioma = idioma;
		this.fechaIni = fechaIni;
		this.fechaFin = fechaFin;
		this.horario = horario;
		this.maxNalumnos = maxNalumnos;
		this.precio = precio;
		
	}
// Metodos
	// idCurso
	/**
	 * Devuelve identificador del curso
	 * 
	 * @return identificador del curso
	 */
	public String getIdCurso() {
		return idCurso;
	}
	/**
	 * Cambia el identificador del curso
	 * 
	 * @param idCurso
	 */
	public void setIdCurso(String idCurso) {
		this.idCurso = idCurso;
	}
	
	// idioma
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


	// fechaIni
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
		this.fechaIni = fechaIni;
	}
	
	// fechaFin
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
		 * @param fechaIni
		 */
		public void setFechaFin(GregorianCalendar fechaFin) {
			this.fechaFin = fechaFin;
		}
			
	/**
	 * Comprueba que la fecha de inicion no sea posterior a la de finalizacion.// Esto aqui?
	 * @param ano
	 * @param mes
	 * @param dia
	 * @return true/false
	 */
	public boolean comprobarFechaIni(int ano,int mes,int dia){
		GregorianCalendar actual = (GregorianCalendar) GregorianCalendar.getInstance();
		GregorianCalendar inicio = getFechaIni();
		if(actual.before(inicio)){
			return true;
		}
		return false;
	}

	// horario
	/**
	 * Devuelve la hora en que se imparte el curso
	 * 
	 * @return horario
	 */
	public GregorianCalendar getHorario() {
		return horario;
	}
	/**
	 * Cambia la hora en que se imparte el curso
	 * 
	 * @param horario
	 */
	public void setHorario(GregorianCalendar horario) {
		this.horario = horario;
	}
	
	// maxNalumnos
	/**
	 * Devuelve el numero maximo de alumnos que se pueden matricular en el
	 * curso.
	 * 
	 * @return maxNalumnos
	 */
	public int getMaxNalumnos() {
		return maxNalumnos;
	}
	/**
	 * Cambia el numero maximo de alumnos que se pueden matricular en el curso
	 * 
	 * @param maxNalumnos
	 */
	public void setMaxNalumnos(int maxNalumnos) {
		this.maxNalumnos = maxNalumnos;
	}
		
	// precio
	/**
	 * Devuelve el precio de matriculacion del curso.
	 * 
	 * @return
	 */
	public double getPrecio() {
		return precio;
	}
	/**
	 * Cambia el precio de matriculacion del curso.
	 * 
	 * @param precio
	 */
	public void setPrecio(double precio) {
		this.precio = precio;
	}

	
	

	/**
	 * Cambia la fecha de finalizacion de un curso
	 * @param dia
	 * @param mes
	 * @param ano
	 */
	public void setFechaFin(int dia, int mes, int ano) {
		if (fechaFin == null) {
			GregorianCalendar fecha = (GregorianCalendar) GregorianCalendar.getInstance();
			this.fechaFin = fecha;
		}
		if (comprobarFechaFin(ano, mes - 1, dia))
		{
			fechaFin.set(ano, mes - 1, dia);
		}else{
			System.out.println("** Fecha finalización no valida **");
		}
	}
	public boolean comprobarFechaFin(int ano,int mes,int dia){
		GregorianCalendar inicio = getFechaIni();
		GregorianCalendar fin = getFechaFin();
		if(fin.before(inicio)){
			return false;
		}
		return true;
	}
	
}