package P2;

public class Adulto extends Alumno{

	private String DNI;
	
	
	public Adulto(String DNI,String nombre,String apellidos){
		super(nombre, apellidos);
		this.DNI=DNI;
	}
	
	public String getDNI(){
		return DNI;		
	}
	
	
	
}
