package it.gestionecurricula.test;

import java.util.Date;

import it.gestionecurricula.model.Curriculum;
import it.gestionecurricula.model.Esperienza;
import it.gestionecurricula.service.MyServiceFactory;
import it.gestionecurricula.service.curriculum.CurriculumService;
import it.gestionecurricula.service.esperienza.EsperienzaService;

public class TestCurriculum {
	public static void main(String[] args) {
		CurriculumService curriculumService = MyServiceFactory.getCurriculumService();
		EsperienzaService esperienzaService = MyServiceFactory.getEsperienzaService();
		
		try {
			
			testInsert(curriculumService);
			
			testDelete(curriculumService);
			
			testInsert(esperienzaService);
			
			testDelete(esperienzaService);

		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testInsert(CurriculumService curriculumService) throws Exception {
		System.out.println("............ INIZIO TEST INSERT ...................");

		int inseriti = curriculumService
				.inserisciNuovo(new Curriculum("matteo", "scarcella", new Date(), "38627", "dsjcnskj"));
		if (inseriti < 1)
			throw new RuntimeException("non è stato possibile inserire un record");

		System.out.println("............ FINE TEST INSERT: successo ...................");
	}
	
	private static void testDelete(CurriculumService curriculumService) throws Exception {
		System.out.println("............ INIZIO TEST DELETE ...................");

		// inserisco un nuovo record
		int inseriti = curriculumService
				.inserisciNuovo(new Curriculum("matteo", "scarcella", new Date(), "38627", "dsjcnskj"));
		if (inseriti < 1)
			throw new RuntimeException("non è stato possibile inserire un record");
		
		// lo cancello per testare il corretto funzionamento
		Curriculum ultimoInserito = curriculumService.listAll().get(curriculumService.listAll().size() - 1);
		int eliminati = curriculumService.rimuovi(ultimoInserito);
		if (eliminati < 1)
			throw new RuntimeException("non è stato possibile eliminare un record");

		System.out.println("............ FINE TEST DELETE: successo ...................");
	}


	private static void testInsert(EsperienzaService esperienzaService) throws Exception {
		System.out.println("............ INIZIO TEST INSERT ...................");

		int inseriti = esperienzaService
				.inserisciNuovo(new Esperienza("lavoro bello", new Date(), new Date(), "e stato bello"));
		if (inseriti < 1)
			throw new RuntimeException("non è stato possibile inserire un record");

		System.out.println("............ FINE TEST INSERT: successo ...................");
	}
	
	private static void testDelete(EsperienzaService esperienzaService) throws Exception {
		System.out.println("............ INIZIO TEST DELETE ...................");

		// inserisco un nuovo record
		int inseriti = esperienzaService
				.inserisciNuovo(new Esperienza("lavoro bello", new Date(), new Date(), "e stato bello"));
		if (inseriti < 1)
			throw new RuntimeException("non è stato possibile inserire un record");
		
		// lo cancello per testare il corretto funzionamento
		Esperienza ultimoInserito = esperienzaService.listAll().get(esperienzaService.listAll().size() - 1);
		int eliminati = esperienzaService.rimuovi(ultimoInserito);
		if (eliminati < 1)
			throw new RuntimeException("non è stato possibile eliminare un record");

		System.out.println("............ FINE TEST DELETE: successo ...................");
	}
}
