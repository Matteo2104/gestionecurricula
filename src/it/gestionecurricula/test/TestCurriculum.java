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
			
			// CRUD CURRICULUM
			testInsertCurriculum(curriculumService);
			
			testDeleteCurriculum(curriculumService);
			
			// CRUD ESPERIENZA
			testInsertEsperienza(esperienzaService);
			
			testDeleteEsperienza(esperienzaService);
			
			
			
			testInsertEsperienzaExtended1(esperienzaService, curriculumService);
			
			testInsertEsperienzaExtended2(esperienzaService, curriculumService);
			
			testInsertCurriculumExtended(curriculumService, esperienzaService);
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void testInsertCurriculum(CurriculumService curriculumService) throws Exception {
		System.out.println("............ INIZIO TEST INSERT ...................");

		int inseriti = curriculumService
				.inserisciNuovo(new Curriculum("matteo", "scarcella", new Date(), "38627", "dsjcnskj"));
		if (inseriti < 1)
			throw new RuntimeException("non è stato possibile inserire un record");

		System.out.println("............ FINE TEST INSERT: successo ...................");
	}
	
	private static void testDeleteCurriculum(CurriculumService curriculumService) throws Exception {
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


	private static void testInsertEsperienza(EsperienzaService esperienzaService) throws Exception {
		System.out.println("............ INIZIO TEST INSERT ...................");

		int inseriti = esperienzaService
				.inserisciNuovo(new Esperienza("lavoro bello", new Date(), new Date(), "e stato bello"));
		if (inseriti < 1)
			throw new RuntimeException("non è stato possibile inserire un record");

		System.out.println("............ FINE TEST INSERT: successo ...................");
	}
	
	private static void testDeleteEsperienza(EsperienzaService esperienzaService) throws Exception {
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
	
	private static void testInsertEsperienzaExtended1(EsperienzaService esperienzaService, CurriculumService curriculumService) throws Exception {
		System.out.println("............ INIZIO TEST INSERT EXTENDED ...................");
		
		// inserisco un nuovo curriculum
		int curriculumInseriti = curriculumService.inserisciNuovo(new Curriculum("matteo", "scarcella", new Date(), "2983923", "email"));
		if (curriculumInseriti < 1)
			throw new RuntimeException("non è stato possibile inserire un record");
		
		Curriculum mioCurriculum = curriculumService.listAll().get(curriculumService.listAll().size() - 1);
		
		// inserisco 3 esperienze
		int Esperienzeinserite = esperienzaService.inserisciNuovo(new Esperienza("lavoro bello", new Date(), new Date(), "e stato bello", mioCurriculum));
		Esperienzeinserite += esperienzaService.inserisciNuovo(new Esperienza("lavoro carino", new Date(), new Date(), "e stato carino", mioCurriculum));
		Esperienzeinserite += esperienzaService.inserisciNuovo(new Esperienza("lavoro brutto", new Date(), null, "e stato brutto", mioCurriculum));
		if (Esperienzeinserite < 3)
			throw new RuntimeException("non è stato possibile inserire un record");
		
		// inserisco una nuova esperienza, che però verrà inserita solo dopo aver chiuso eventuali esperienze aperte
		Esperienzeinserite = esperienzaService.inserisciNuovoConControlli(new Esperienza("nuovo lavoro", new Date(), new Date(), "si saprà come sarà"), mioCurriculum.getId());
		if (Esperienzeinserite < 1) 
			throw new RuntimeException("test inserimento con controllo fallito");
		
		System.out.println("............ FINE TEST INSERT EXTENDED ...................");
	}
	
	private static void testInsertEsperienzaExtended2(EsperienzaService esperienzaService, CurriculumService curriculumService) throws Exception {
		System.out.println("............ INIZIO TEST INSERT EXTENDED 2 ...................");
		
		// inserisco un nuovo curriculum
		int curriculumInseriti = curriculumService.inserisciNuovo(new Curriculum("matteo", "scarcella", new Date(), "2983923", "email"));
		if (curriculumInseriti < 1)
			throw new RuntimeException("non è stato possibile inserire un record");
		
		Curriculum mioCurriculum = curriculumService.listAll().get(curriculumService.listAll().size() - 1);
		
		// inserisco 3 esperienze
		int Esperienzeinserite = esperienzaService.inserisciNuovo(new Esperienza("lavoro bello", new Date(1000000), new Date(90000000), "e stato bello", mioCurriculum));
		Esperienzeinserite += esperienzaService.inserisciNuovo(new Esperienza("lavoro carino", new Date(100000000), new Date(1000000000), "e stato carino", mioCurriculum));
		Esperienzeinserite += esperienzaService.inserisciNuovo(new Esperienza("lavoro brutto", new Date(1000000000), new Date(2100000000), "e stato brutto", mioCurriculum));
		if (Esperienzeinserite < 3)
			throw new RuntimeException("non è stato possibile inserire un record");
		
		// inserisco una nuova esperienza, che però ha la data di inizio nel periodo della 3 esperienza
		Esperienzeinserite = esperienzaService.inserisciNuovoConControlli(new Esperienza("nuovo lavoro", new Date(1500000000), new Date(2110000000), "si saprà come sarà"), mioCurriculum.getId());
		if (Esperienzeinserite < 1) 
			throw new RuntimeException("test inserimento con controllo fallito");
		
		System.out.println("............ FINE TEST INSERT EXTENDED 2 ...................");
	}
	
	private static void testInsertCurriculumExtended(CurriculumService curriculumService, EsperienzaService esperienzaService) throws Exception {
		System.out.println("............ INIZIO TEST INSERT CURRICULUM ...................");

		int inseriti = curriculumService.inserisciNuovo(new Curriculum("matteo", "scarcella", new Date(), "38627", "dsjcnskj"));
		if (inseriti < 1)
			throw new RuntimeException("non è stato possibile inserire un record");
		
		Curriculum appenaInserito = curriculumService.listAll().get(curriculumService.listAll().size() - 1);
		
		// inserisco 1 esperienza
		int Esperienzeinserite = esperienzaService.inserisciNuovo(new Esperienza("lavoro bello", new Date(), new Date(), "e stato bello", appenaInserito));
		if (Esperienzeinserite < 1)
			throw new RuntimeException("non è stato possibile inserire un record");
		
		// provo a eliminare il curriculum ma devo ottenere un errore perchè ho collegata un'esperienza
		inseriti = curriculumService.rimuovi(appenaInserito);
		if (inseriti < 1)
			throw new RuntimeException();

		System.out.println("............ FINE TEST INSERT CURRICULUM: successo ...................");
	}

	
}
