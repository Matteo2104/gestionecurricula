package it.gestionecurricula.dao;

import java.sql.Connection;
import java.util.List;

public interface IBaseDAO<T>  {
	public int insert(T input) throws Exception;
	
	public List<T> list() throws Exception;
	
	public int delete(T input) throws Exception;
	
	public int update(T input) throws Exception;
	
	public T get(Long idInput) throws Exception;
	
	public List<T> findByExample(T example) throws Exception;
	
	public void setConnection(Connection connection) throws Exception;
}
