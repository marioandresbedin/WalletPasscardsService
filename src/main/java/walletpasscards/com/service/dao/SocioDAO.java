package walletpasscards.com.service.dao;

import java.util.List;

import walletpasscards.com.model.Socio;

public interface SocioDAO {

	public void addSocio(Socio s);

	public void updateSocio(Socio s);

	public List<Socio> listSocios();

	public Socio getSocioById(int id);

	public void removeSocio(int id);

	public Socio getSocioByDNI(String dni);

}
