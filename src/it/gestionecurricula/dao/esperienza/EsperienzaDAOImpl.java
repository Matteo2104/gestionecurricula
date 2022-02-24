package it.gestionecurricula.dao.esperienza;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
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
				"INSERT INTO esperienza (descrizione, datainizio, datafine, conoscenzeacquisite, curriculum_id) VALUES (?, ?, ?, ?, ?);")) {
			
			if (input.getDescrizione() == null) {
				ps.setNull(1, Types.VARCHAR);
			} else {
				ps.setString(1, input.getDescrizione());
			}
			
			if (input.getDataInizio() == null) {
				ps.setNull(2, Types.DATE);
			} else {
				ps.setDate(2, new java.sql.Date(input.getDataInizio().getTime()));
			}
			
			if (input.getDataFine() == null) {
				ps.setNull(3, Types.DATE);
			} else {
				ps.setDate(3, new java.sql.Date(input.getDataFine().getTime()));
			}
			
			if (input.getConoscenzeAcquisite() == null) {
				ps.setNull(4, Types.VARCHAR);
			} else {
				ps.setString(4, input.getConoscenzeAcquisite());
			}
			
			if (input.getCurriculum() == null) {
				ps.setNull(5, Types.INTEGER);
			} else {
				ps.setLong(5, input.getCurriculum().getId());
			}

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
				"UPDATE esperienza SET descrizione=?, datainizio=?, datafine=?, conoscenzeacquisite=? WHERE id=?;")) {
			if (input.getDescrizione() == null) {
				ps.setNull(1, Types.VARCHAR);
			} else {
				ps.setString(1, input.getDescrizione());
			}
			
			if (input.getDataInizio() == null) {
				ps.setNull(2, Types.DATE);
			} else {
				ps.setDate(2, new java.sql.Date(input.getDataInizio().getTime()));
			}
			
			if (input.getDataFine() == null) {
				ps.setNull(3, Types.DATE);
			} else {
				ps.setDate(3, new java.sql.Date(input.getDataFine().getTime()));
			}
			
			if (input.getConoscenzeAcquisite() == null) {
				ps.setNull(4, Types.VARCHAR);
			} else {
				ps.setString(4, input.getConoscenzeAcquisite());
			}
			
			
			ps.setLong(5, input.getId());

			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public Esperienza get(Long idInput) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		Esperienza temp = null;

		try (PreparedStatement ps = connection.prepareStatement("select * from esperienza where id=?;");) {

			ps.setLong(1, idInput);

			try (ResultSet rs = ps.executeQuery();) {
				while (rs.next()) {
					temp = new Esperienza();

					temp.setId(rs.getLong("id"));
					temp.setDescrizione(rs.getString("descrizione"));
					temp.setDataInizio(rs.getDate("datafine"));
					temp.setDataFine(rs.getDate("datafine"));
					temp.setConoscenzeAcquisite(rs.getString("conoscenzeacquisite"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return temp;
	}

	@Override
	public List<Esperienza> findByExample(Esperienza example) throws Exception {
		List<Esperienza> resultList = new ArrayList<>();
		Esperienza temp = null;
		String sqlStatement = "SELECT * FROM televisore WHERE 1=1";
		
		if (example.getDescrizione() != null) {
			sqlStatement += " AND marca='" + example.getDescrizione() + "'";
		}
		if (example.getDataInizio() != null) {
			sqlStatement += " AND modello='" + example.getDataInizio() + "'";
		}
		if (example.getDataFine() != null) {
			sqlStatement += " AND dataproduzione='" + example.getDataFine() + "'";
		}
		if (example.getConoscenzeAcquisite() != null) {
			sqlStatement += " AND modello='" + example.getConoscenzeAcquisite() + "'";
		}
		
		try (PreparedStatement ps = connection.prepareStatement(sqlStatement);) {
			
			try (ResultSet rs = ps.executeQuery();) {
				
				while (rs.next()) {
					temp = new Esperienza();
					
					temp.setId(rs.getLong("id"));
					temp.setDescrizione(rs.getString("descrizione"));
					temp.setDataInizio(rs.getDate("datafine"));
					temp.setDataFine(rs.getDate("datafine"));
					temp.setConoscenzeAcquisite(rs.getString("conoscenzeacquisite"));
					
					resultList.add(temp);
				}
			}
			
		} catch (Exception e) {
			throw new RuntimeException("errore esecuzione query findByExample");
		}
		return resultList;
	}

	public List<Esperienza> findAllByIdCurriculum(Long id) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		List<Esperienza> result = new ArrayList<>();
		Esperienza temp = null;

		try (PreparedStatement ps = connection.prepareStatement("select * from esperienza e INNER JOIN curriculum c ON e.curriculum_id = c.id WHERE c.id = ?;");) {
			ps.setLong(1, id);
			
			try (ResultSet rs = ps.executeQuery();) {
				while (rs.next()) {
					temp = new Esperienza();

					temp.setId(rs.getLong("e.id"));
					temp.setDescrizione(rs.getString("e.descrizione"));
					temp.setDataInizio(rs.getDate("e.datainizio"));
					temp.setDataFine(rs.getDate("e.datafine"));
					temp.setConoscenzeAcquisite(rs.getString("e.conoscenzeacquisite"));
					
					//System.out.println(temp);
					result.add(temp);
				}
			}
			

		} catch (Exception e) {
			throw new RuntimeException("impossibile eseguire query");
		}
		return result;
	}

}
