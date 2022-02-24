package it.gestionecurricula.dao.esperienza;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.gestionecurricula.dao.AbstractMySQLDAO;
import it.gestionecurricula.model.Esperienza;

public class EsperienzaDAOImpl extends AbstractMySQLDAO implements EsperienzaDAO {
	
	@Override
	public void setConnection(Connection connection) throws Exception {
		this.connection = connection;	
	}	
	
	@Override
	public int insert(Esperienza input) throws Exception {
		// prima di tutto cerchiamo di capire se possiamo effettuare le operazioni
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement(
				"INSERT INTO esperienza (descrizione, datainizio, datafine, conoscenzeacquisite) VALUES (?, ?, ?, ?);")) {
			ps.setString(1, input.getDescrizione());
			ps.setDate(2, new java.sql.Date(input.getDataInizio().getTime()));
			ps.setDate(3, new java.sql.Date(input.getDataFine().getTime()));
			ps.setString(4, input.getConoscenzeAcquisite());

			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public List<Esperienza> list() throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		List<Esperienza> result = new ArrayList<>();
		Esperienza temp = null;

		try (Statement ps = connection.createStatement(); ResultSet rs = ps.executeQuery("select * from esperienza;")) {

			while (rs.next()) {
				temp = new Esperienza();

				temp.setId(rs.getLong("id"));
				temp.setDescrizione(rs.getString("descrizione"));
				temp.setDataInizio(rs.getDate("datafine"));
				temp.setDataFine(rs.getDate("datafine"));
				temp.setConoscenzeAcquisite(rs.getString("conoscenzeacquisite"));

				result.add(temp);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int delete(Esperienza input) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement(
				"DELETE FROM esperienza WHERE id=?;")) {
			ps.setLong(1, input.getId());

			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int update(Esperienza input) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement(
				"UPDATE esperienza SET descrizione=?, datainizio=?, datafine=?, conoscenzeacquisite=?;")) {
			ps.setString(1, input.getDescrizione());
			ps.setDate(2, new java.sql.Date(input.getDataInizio().getTime()));
			ps.setDate(3, new java.sql.Date(input.getDataFine().getTime()));
			ps.setString(4, input.getConoscenzeAcquisite());

			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public Esperienza get(Long idInput) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Esperienza> findByExample(Esperienza example) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	

}
