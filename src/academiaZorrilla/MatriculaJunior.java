package academiaZorrilla;


public class MatriculaJunior extends Matricula {
	/**
	 * Telefono de contacto
	 */
	private int telefono;
	public MatriculaJunior(int idMatricula, Alumno alumno, Curso curso,int telefono){
		super(idMatricula, alumno, curso);
		this.telefono=telefono;
	}
	/**
	 * Devuelve el telefono
	 * @return
	 */
	public int getTelefono() {
		return telefono;
	}
	/**
	 * Cambia el telefono
	 * @param telefono
	 */
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

}
