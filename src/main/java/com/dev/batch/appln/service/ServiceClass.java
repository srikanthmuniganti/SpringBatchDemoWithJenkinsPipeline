package com.dev.batch.appln.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.batch.appln.models.MatchOutput;
import com.dev.batch.appln.models.TeamStats;
import com.dev.batch.appln.repository.BatchRepository;

@Service
public class ServiceClass {

	@Autowired
	private BatchRepository repo;

	public List<TeamStats> getTeamStats() {
		List<MatchOutput> teamsDetails = getAll();
		List<String> teamNames = getTeamNames();
		List<TeamStats> teamstats = new ArrayList<>();
		for (String name : teamNames) {
			int noOfMatches = 0;
			int noOfWins=0;
			TeamStats team = new TeamStats();
			team.setName(name);
			for (MatchOutput match : teamsDetails) {
				if (name.equalsIgnoreCase(match.getTeam1()) || name.equalsIgnoreCase(match.getTeam2())) {
					++noOfMatches;
					if(name.equalsIgnoreCase(match.getWinner())) {
						++noOfWins;
					}					
				}
			}
			
			team.setNoOfMatches(noOfMatches);
			team.setNoOfWins(noOfWins);
			team.setNoOfLoss(noOfMatches-noOfWins);
			team.setSuccessRatio((noOfWins*100)/noOfMatches);	
			teamstats.add(team);
			System.err.println(team.toString());
		}

		return teamstats;
	}

	public List<MatchOutput> getAll() {
		return repo.findAll();
	}

	public List<String> getTeamNames() {
		return repo.findByTeam1();
	}

}
