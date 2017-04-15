package walletpasscards.com.model;

public class Pass {

	static final String GYM_TEMPLATE_ID = "5343826923487232";

	private String nombreSocio;
	private String dni;
	private String nroSocio;
	private String clases;
	private String cantClases;
	private String email;
	private String fechaVto;
	private String estado;
	private String serialNumber;
	private String cantCuotas;


	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	public String getNombreSocio() {
		return nombreSocio;
	}

	public void setNombreSocio(String nombreSocio) {
		this.nombreSocio = nombreSocio;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNroSocio() {
		return nroSocio;
	}

	public void setNroSocio(String nroSocio) {
		this.nroSocio = nroSocio;
	}

	public String getClases() {
		return clases;
	}

	public void setClases(String clases) {
		this.clases = clases;
	}

	public String getFechaVto() {
		return fechaVto;
	}

	public void setFechaVto(String fechaVto) {
		this.fechaVto = fechaVto;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCantCuotas() {
		return cantCuotas;
	}

	public void setCantCuotas(String cantCuotas) {
		this.cantCuotas = cantCuotas;
	}

	public String getCantClases() {
		return cantClases;
	}

	public void setCantClases(String cantClases) {
		this.cantClases = cantClases;
	}


}
