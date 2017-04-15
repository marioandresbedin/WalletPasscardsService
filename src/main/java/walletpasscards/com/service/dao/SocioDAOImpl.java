package walletpasscards.com.service.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import walletpasscards.com.model.Socio;

@Repository
@Transactional
public class SocioDAOImpl implements SocioDAO {

	private static final Logger logger = LoggerFactory.getLogger(SocioDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	/*
	 * public void setSessionFactory(SessionFactory sf) { this.sessionFactory =
	 * sf; }
	 */

	@Override
	public void addSocio(Socio s) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(s);
		logger.info("Socio saved successfully, Person Details=" + s);
	}

	@Override
	public void updateSocio(Socio s) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(s);
		logger.info("Socio saved successfully, Person Details=" + s);
	}

	@Override
	public List<Socio> listSocios() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Socio> personsList = session.createQuery("from Socio").list();
		for (Socio p : personsList) {
			logger.info("Person List::" + p);
		}
		return personsList;
	}

	@Override
	public Socio getSocioById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Socio p = (Socio) session.load(Socio.class, new Integer(id));
		logger.info("Socio loaded successfully, Person details=" + p);
		return p;
	}

	@Override
	public void removeSocio(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Socio p = (Socio) session.load(Socio.class, new Integer(id));
		if (null != p) {
			session.delete(p);
		}
		logger.info("Socio deleted successfully, person details=" + p);
	}

	@Override
	public Socio getSocioByDNI(String dni) {
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Socio where dni = :dni ");
		query.setParameter("dni", dni);
		List<Socio> personsList = query.list();
		if (!personsList.isEmpty()) {
			logger.info("Socio encontrado con dni: " + dni);
			return personsList.get(0);
		} else {
			logger.info("No se encontro socio para dni: " + dni);
			return null;
		}
	}

}
