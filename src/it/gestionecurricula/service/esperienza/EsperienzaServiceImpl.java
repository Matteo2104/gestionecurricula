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
		Esperienza result = null;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER, Constants.CONNECT)) {

			// inietto la connection nel dao
			esperienzaDAO.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = esperienzaDAO.get(idInput);

		} catch (Exception e) {
			throw new RuntimeException("errore connessione");
		}
		return result;
	}

	@Override
	public int aggiorna(Esperienza input) throws Exception {
		int result = 0;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER, Constants.CONNECT)) {
			 
			esperienzaDAO.setConnection(connection);
			result = esperienzaDAO.update(input);
			
		} catch (Exception e) {
			throw new RuntimeException("errore nella connessione");
		}
		return result;
	}

	@Override
	public int inserisciNuovo(Esperienza input) throws Exception {
		int result = 0;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER, Constants.CONNECT)) {
			 
			esperienzaDAO.setConnection(connection);
			
			result = esperienzaDAO.insert(input);
			
		} catch (Exception e) {
			throw new RuntimeException("errore di connessione");
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
		List<Esperienza> result = new ArrayList<>();
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER, Constants.CONNECT)) {

			// inietto la connection nel dao
			esperienzaDAO.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = esperienzaDAO.findByExample(input);

		} catch (Exception e) {
			throw new RuntimeException("errore connessione");
		}
		return result;
	}

	@Override
	public int inserisciNuovoConControlli(Esperienza input, Long idInput) throws Exception {
		int result = 0;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER, Constants.CONNECT)) {
			 
			esperienzaDAO.setConnection(connection);
			
			List<Esperienza> listaDiEsperienze = esperienzaDAO.findAllByIdCurriculum(idInput);
			
			if (listaDiEsperienze != null && !listaDiEsperienze.isEmpty()) {
				for (Esperienza e : listaDiEsperienze) {

					if (e.getDataFine() == null) {
						e.setDataFine(input.getDataInizio());
						esperienzaDAO.update(e);
					}
					
					if (e.getDataFine().compareTo(input.getDataInizio()) > 0) {
						throw new RuntimeException("non si pu?? inserire un'esperienza che coincida temporalmente con una gia esistente");
					}
				}
			}
			
			result = esperienzaDAO.insert(input);
			
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

}
