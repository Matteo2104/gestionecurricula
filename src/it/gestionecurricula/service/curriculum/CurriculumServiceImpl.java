package it.gestionecurricula.service.curriculum;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import it.gestionecurricula.connection.MyConnection;
import it.gestionecurricula.dao.Constants;
import it.gestionecurricula.dao.curriculum.CurriculumDAO;
import it.gestionecurricula.dao.esperienza.EsperienzaDAO;
import it.gestionecurricula.model.Curriculum;

public class CurriculumServiceImpl implements CurriculumService {

	CurriculumDAO curriculumDAO;
	EsperienzaDAO esperienzaDAO;
	
	@Override
	public void setCurriculumDao(CurriculumDAO curriculumDAO) {
		this.curriculumDAO = curriculumDAO;
	}
	
	@Override
	public void setEsperienzaDao(EsperienzaDAO esperienzaDAO) {
		this.esperienzaDAO = esperienzaDAO;
	}

	@Override
	public List<Curriculum> listAll() throws Exception {
		List<Curriculum> result = new ArrayList<>();
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER, Constants.CONNECT)) {

			// inietto la connection nel dao
			curriculumDAO.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = curriculumDAO.list();

		} catch (Exception e) {
			throw new RuntimeException("errore connessione");
		}
		return result;
	}

	@Override
	public Curriculum findById(Long idInput) throws Exception {
		Curriculum result = null;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER, Constants.CONNECT)) {

			// inietto la connection nel dao
			curriculumDAO.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = curriculumDAO.get(idInput);

		} catch (Exception e) {
			throw new RuntimeException("errore connessione");
		}
		return result;
	}

	@Override
	public int aggiorna(Curriculum input) throws Exception {
		int result = 0;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER, Constants.CONNECT)) {
			 
			curriculumDAO.setConnection(connection);
			result = curriculumDAO.update(input);
			
		} catch (Exception e) {
			throw new RuntimeException("errore nella connessione");
		}
		return result;
	}

	@Override
	public int inserisciNuovo(Curriculum input) throws Exception {
		int result = 0;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER, Constants.CONNECT)) {
			 
			curriculumDAO.setConnection(connection);
			result = curriculumDAO.insert(input);
			
		} catch (Exception e) {
			throw new RuntimeException("errore nella connessione");
		}
		return result;
	}

	@Override
	public int rimuovi(Curriculum input) throws Exception {
		int result = 0;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER, Constants.CONNECT)) {
			 
			curriculumDAO.setConnection(connection);
			esperienzaDAO.setConnection(connection);
			
			if (!esperienzaDAO.findAllByIdCurriculum(input.getId()).isEmpty()) 
				throw new RuntimeException("non si può eliminare un curriculum che ha almeno 1 esperienza");
			
			result = curriculumDAO.delete(input);
			
			
				
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	@Override
	public List<Curriculum> findByExample(Curriculum input) throws Exception {
		List<Curriculum> result = new ArrayList<>();
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER, Constants.CONNECT)) {

			// inietto la connection nel dao
			curriculumDAO.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = curriculumDAO.findByExample(input);

		} catch (Exception e) {
			throw new RuntimeException("errore connessione");
		}
		return result;
	}

	

}
