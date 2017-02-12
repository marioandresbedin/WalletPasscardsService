package walletpasscards.com.model;

import java.util.Date;

public class Pass {

	static final String INSURANCE_TEMPLATE_ID = "5681803151278080";
	static final String GYM_TEMPLATE_ID = "5343826923487232";

	static final String AUTH_HEADER_ID = "zYdWCkbJKfOCxAgRYAwAYpcoZujHaZghUELUuhgMgUPoDMpmmZWuWmrnDZcItBWG";

	private String nameInsures;
	private String policyNumber;
	private Date effectiveDate;

	private String nombreSocio;
	private String plan;
	private Date fechaVto;

	public Date getFechaVto() {
		return fechaVto;
	}

	public void setFechaVto(Date fechaVto) {
		this.fechaVto = fechaVto;
	}

	public String getNameInsures() {
		return nameInsures;
	}

	public void setNameInsures(String nameInsures) {
		this.nameInsures = nameInsures;
	}

	public String getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getNombreSocio() {
		return nombreSocio;
	}

	public void setNombreSocio(String nombreSocio) {
		this.nombreSocio = nombreSocio;
	}

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

}
