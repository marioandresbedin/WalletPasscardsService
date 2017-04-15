package walletpasscards.com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import walletpasscards.com.model.Socio;
import walletpasscards.com.service.dao.SocioDAO;

@Service
public class SocioServiceImpl implements SocioService {

	@Autowired
	private SocioDAO socioDAO;

	public void setPersonDAO(SocioDAO socioDAO) {
		this.socioDAO = socioDAO;
	}

	@Override
	@Transactional
	public void addPerson(Socio s) {
		this.socioDAO.addSocio(s);
	}

	@Override
	public void updatePerson(Socio s) {
		this.socioDAO.updateSocio(s);
	}

	@Override
	public List<Socio> listSocios() {
		return this.socioDAO.listSocios();
	}

	@Override
	@Transactional
	public Socio getSocioById(int id) {
		return this.socioDAO.getSocioById(id);
	}

	@Override
	@Transactional
	public void removeSocio(int id) {
		this.socioDAO.removeSocio(id);
	}

	@Override
	public Socio findSerialNumberByDNI(String dni) {
		return this.socioDAO.getSocioByDNI(dni);
	}

}
