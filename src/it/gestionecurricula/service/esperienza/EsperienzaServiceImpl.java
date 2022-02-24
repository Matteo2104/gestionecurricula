package it.gestionecurricula.service.esperienza;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import it.gestionecurricula.connection.MyConnection;
import it.gestionecurricula.dao.Constants;
import it.gestionecurricula.dao.esperienza.EsperienzaDAO;
import it.gestionecurricula.model.Esperienza;

public class EsperienzaServiceImpl implements EsperienzaService {

	EsperienzaDAO esperienzaDAO;
	
	@Override
	public void setEsperienzaDao(EsperienzaDAO esperienzaDao) {
		this.esperienzaDAO = esperienzaDao;
	}

	@Override
	public List<Esperienza> listAll() throws Exception {
		List<Esperienza> result = new ArrayList<>();
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER, Constants.CONNECT)) {

			// inietto la connection nel dao
			esperienzaDAO.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = esperienzaDAO.list();

		} catch (Exception e) {
			throw new RuntimeException("errore connessione");
		}
		return result;
	}

	@Override
	public Esperienza findById(Long idInput) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int aggiorna(Esperienza input) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int inserisciNuovo(Esperienza input) throws Exception {
		int result = 0;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER, Constants.CONNECT)) {
			 
			esperienzaDAO.setConnection(connection);
			result = esperienzaDAO.insert(input);
			
		} catch (Exception e) {
			throw new RuntimeException("errore nella connessione");
		}
		return result;
	}

	@Override
	public int rimuovi(Esperienza input) throws Exception {
		int result = 0;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER, Constants.CONNECT)) {
			 
			esperienzaDAO.setConnection(connection);
			result = esperienzaDAO.delete(input);
			
		} catch (Exception e) {
			throw new RuntimeException("errore nella connessione");
		}
		return result;
	}

	@Override
	public List<Esperienza> findByExample(Esperienza input) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
