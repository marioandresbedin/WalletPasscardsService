package walletpasscards.com.service;

import java.util.List;

import walletpasscards.com.model.Socio;

public interface SocioService {

	public void addPerson(Socio p);

	public void updatePerson(Socio p);

	public List<Socio> listSocios();

	public Socio getSocioById(int id);

	public void removeSocio(int id);

	public Socio findSerialNumberByDNI(String dni);

}
