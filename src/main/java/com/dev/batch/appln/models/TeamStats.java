package com.dev.batch.appln.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeamStats {

	private String name;
	private int NoOfMatches;
	private int NoOfWins;
	private int NoOfLoss;
	private float successRatio;
	@Override
	public String toString() {
		return "name=" + name + ", NoOfMatches=" + NoOfMatches + ", NoOfWins=" + NoOfWins + ", NoOfLoss="
				+ NoOfLoss + ", successRatio=" + successRatio + "]\n";
	}
	
	
	
}
