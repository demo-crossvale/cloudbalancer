package com.service;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Fleet;

@Service
public class FleetService {

	private final KieContainer kieContainer;

	private final String[] GROUPS = {"initialize","override","default",
										"adhoc","overloaded","underloaded",
										"finetune","upperlimit","lowerlimit"};
	
	@Autowired
	public FleetService(KieContainer kieContainer) {
		this.kieContainer = kieContainer;
	}
	
	public Fleet getFleet(Fleet fleet) {
		KieSession kieSession = kieContainer.newKieSession("rulesSession");
		kieSession.insert(fleet);
		for(String group: GROUPS) {
			kieSession.getAgenda().getAgendaGroup(group).setFocus();
			kieSession.fireAllRules();
		}
		kieSession.dispose();
		return fleet;
	}

}
