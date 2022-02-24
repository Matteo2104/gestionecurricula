package it.gestionecurricula.dao.esperienza;

import java.sql.Connection;
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Esperienza> list() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(Esperienza input) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Esperienza input) throws Exception {
		// TODO Auto-generated method stub
		return 0;
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
