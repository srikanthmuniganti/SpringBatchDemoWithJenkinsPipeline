package com.dev.batch.appln.models;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class MatchOutput {
	
	@Id
	private long id;
	private String city;
	private LocalDate date;
	private String playerOfMatch;
	private String venue;
	private String  team1;
	private String team2;
	private String tossWinner;
	private String tossDecision;
	private String winner;
	private String result;
	private String resultMargin;
	private String umpire1;
	private String umpire2;

}
