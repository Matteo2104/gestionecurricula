package it.gestionecurricula.service.esperienza;

import java.util.List;

import it.gestionecurricula.dao.esperienza.EsperienzaDAO;
import it.gestionecurricula.model.Esperienza;

public interface EsperienzaService {
	// questo mi serve per injection
	public void setEsperienzaDao(EsperienzaDAO esperienzaDao);

	public List<Esperienza> listAll() throws Exception;

	public Esperienza findById(Long idInput) throws Exception;

	public int aggiorna(Esperienza input) throws Exception;

	public int inserisciNuovo(Esperienza input) throws Exception;

	public int rimuovi(Esperienza input) throws Exception;

	public List<Esperienza> findByExample(Esperienza input) throws Exception;
	
	public int inserisciNuovoConControlli(Esperienza input, Long idInput) throws Exception;
}
