package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.model.Fleet;
import com.service.FleetService;

@RestController
public class FleetController {
	
	private final FleetService fleetService;
	
	@Autowired
	public FleetController(FleetService fleetService) {
		this.fleetService = fleetService;
	}
	
	@RequestMapping(value="/getFleetById", method= RequestMethod.GET, produces= "application/json")
	public Fleet getFleetById(@RequestParam(required=true) String id) {
		Fleet fleet = new Fleet();
		fleet.setId(id);
		fleet.setTargetCapacity(0);
		fleet.setFilter(false);
		fleetService.getFleet(fleet);
		return fleet;
	}
	
	@RequestMapping(value="/getFleet", method= RequestMethod.POST, produces= "application/json")
	public Fleet getFleetByPost(@RequestBody Fleet bodyFleet) {
		Fleet fleet = bodyFleet;
		fleetService.getFleet(fleet);
		return fleet;
	}

}
