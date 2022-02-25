package it.gestionecurricula.service;

import it.gestionecurricula.dao.curriculum.CurriculumDAOImpl;
import it.gestionecurricula.dao.esperienza.EsperienzaDAOImpl;
import it.gestionecurricula.service.curriculum.CurriculumService;
import it.gestionecurricula.service.curriculum.CurriculumServiceImpl;
import it.gestionecurricula.service.esperienza.EsperienzaService;
import it.gestionecurricula.service.esperienza.EsperienzaServiceImpl;

public class MyServiceFactory {
	public static CurriculumService getCurriculumService() {
		CurriculumService curriculumService = new CurriculumServiceImpl();
		curriculumService.setCurriculumDao(new CurriculumDAOImpl());
		curriculumService.setEsperienzaDao(new EsperienzaDAOImpl());
		return curriculumService;
	}
	
	public static EsperienzaService getEsperienzaService() {
		EsperienzaService esperienzaService = new EsperienzaServiceImpl();
		esperienzaService.setEsperienzaDao(new EsperienzaDAOImpl());
		return esperienzaService;
	}
	
}
