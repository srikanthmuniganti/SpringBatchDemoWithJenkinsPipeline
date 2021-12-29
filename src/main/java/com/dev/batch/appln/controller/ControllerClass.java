package com.dev.batch.appln.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.batch.appln.models.MatchOutput;
import com.dev.batch.appln.models.TeamStats;
import com.dev.batch.appln.service.ServiceClass;

@RestController
public class ControllerClass {
	
	@Autowired
	private ServiceClass service;
	
	
	@GetMapping("/")
	public List<MatchOutput> getAll(){
		
		return service.getAll();
	}
		
	@GetMapping("/team-names")
	public List<String> getTeamNames(){
		
		return service.getTeamNames();
	}
	
	@GetMapping("/stats-by-team")
	public List<TeamStats> getTeamStats(){
		return service.getTeamStats();
	}
	
	
	
	
}
