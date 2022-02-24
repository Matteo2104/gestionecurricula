package it.gestionecurricula.dao.curriculum;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.gestionecurricula.dao.AbstractMySQLDAO;
import it.gestionecurricula.model.Curriculum;

public class CurriculumDAOImpl extends AbstractMySQLDAO implements CurriculumDAO {

	@Override
	public void setConnection(Connection connection) throws Exception {
		this.connection = connection;	
	}
	
	@Override
	public int insert(Curriculum input) throws Exception {
		// prima di tutto cerchiamo di capire se possiamo effettuare le operazioni
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement(
				"INSERT INTO curriculum (nome, cognome, datadinascita, telefono, email) VALUES (?, ?, ?, ?, ?);")) {
			ps.setString(1, input.getNome());
			ps.setString(2, input.getCognome());
			ps.setDate(3, new java.sql.Date(input.getDataNascita().getTime()));
			ps.setString(4, input.getTelefono());
			ps.setString(5, input.getEmail());

			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public List<Curriculum> list() throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		List<Curriculum> result = new ArrayList<>();
		Curriculum temp = null;

		try (Statement ps = connection.createStatement(); ResultSet rs = ps.executeQuery("select * from curriculum;")) {

			while (rs.next()) {
				temp = new Curriculum();

				temp.setId(rs.getLong("id"));
				temp.setNome(rs.getString("nome"));
				temp.setCognome(rs.getString("cognome"));
				temp.setDataNascita(rs.getDate("datadinascita"));
				temp.setTelefono(rs.getString("telefono"));
				temp.setEmail(rs.getString("email"));

				result.add(temp);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int delete(Curriculum input) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement(
				"DELETE FROM curriculum WHERE id=?;")) {
			ps.setLong(1, input.getId());

			result = ps.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException("errore nell'esecuzione della delete");
		}
		return result;
	}

	@Override
	public int update(Curriculum input) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement(
				"UPDATE curriculum SET nome=?, cognome=?, datadinascita=?, telefono=?, email=?;")) {
			ps.setString(1, input.getNome());
			ps.setString(2, input.getCognome());
			ps.setDate(3, new java.sql.Date(input.getDataNascita().getTime()));
			ps.setString(4, input.getTelefono());
			ps.setString(5, input.getEmail());

			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public Curriculum get(Long idInput) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		Curriculum temp = null;

		try (PreparedStatement ps = connection.prepareStatement("select * from curriculum where id=?;");) {

			ps.setLong(1, idInput);

			try (ResultSet rs = ps.executeQuery();) {
				while (rs.next()) {
					temp = new Curriculum();

					temp.setId(rs.getLong("id"));
					temp.setNome(rs.getString("nome"));
					temp.setCognome(rs.getString("cognome"));
					temp.setDataNascita(rs.getDate("datadinascita"));
					temp.setTelefono(rs.getString("telefono"));
					temp.setEmail(rs.getString("email"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return temp;
	}

	@Override
	public List<Curriculum> findByExample(Curriculum example) throws Exception {
		List<Curriculum> resultList = new ArrayList<>();
		String sqlStatement = "SELECT * FROM televisore WHERE 1=1";
		
		if (example.getNome() != null) {
			sqlStatement += " AND marca='" + example.getNome() + "'";
		}
		if (example.getCognome() != null) {
			sqlStatement += " AND modello='" + example.getCognome() + "'";
		}
		if (example.getDataNascita() != null) {
			sqlStatement += " AND dataproduzione='" + example.getDataNascita() + "'";
		}
		if (example.getTelefono() != null) {
			sqlStatement += " AND modello='" + example.getTelefono() + "'";
		}
		if (example.getEmail() != null) {
			sqlStatement += " AND modello='" + example.getEmail() + "'";
		}
		
		try (PreparedStatement ps = connection.prepareStatement(sqlStatement);) {
			
			try (ResultSet rs = ps.executeQuery();) {
				Curriculum temp;
				while (rs.next()) {
					temp = new Curriculum();
					
					temp.setId(rs.getLong("id"));
					temp.setNome(rs.getString("nome"));
					temp.setCognome(rs.getString("cognome"));
					temp.setDataNascita(rs.getDate("datadinascita"));
					temp.setTelefono(rs.getString("telefono"));
					temp.setEmail(rs.getString("email"));
					
					resultList.add(temp);
				}
			}
			
		} catch (Exception e) {
			throw new RuntimeException("errore esecuzione query findByExample");
		}
		return resultList;
	}

	@Override
	public List<Curriculum> findEsperienze(Curriculum input) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		List<Curriculum> result = new ArrayList<>();
		Curriculum temp = null;

		try (PreparedStatement ps = connection.prepareStatement("select * from curriculum c INNER JOIN esperienza e ON c.id = e.curriculum_id WHERE c.id = ?;");) {
			ps.setLong(1, input.getId());
			
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					temp = new Curriculum();

					temp.setId(rs.getLong("id"));
					temp.setNome(rs.getString("nome"));
					temp.setCognome(rs.getString("cognome"));
					temp.setDataNascita(rs.getDate("datadinascita"));
					temp.setTelefono(rs.getString("telefono"));
					temp.setEmail(rs.getString("email"));

					result.add(temp);
				}
			}
			

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

}
