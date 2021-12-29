package com.dev.batch.appln.configuration;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.dev.batch.appln.models.MatchInput;
import com.dev.batch.appln.models.MatchOutput;

public class MatchOutputItemProcessor implements ItemProcessor<MatchInput, MatchOutput> {

	
		//private static final Logger log = LoggerFactory.getLogger(MatchOutputItemProcessor.class);

		@Override
		public MatchOutput process(MatchInput input) throws Exception {
		    		    
			MatchOutput output=new MatchOutput();
			output.setId(Long.valueOf(input.getId()));
			output.setCity(input.getCity()); 
			output.setDate(LocalDate.parse(input.getDate())); 
			output.setPlayerOfMatch(input.getPlayer_of_match());
			output.setVenue(input.getVenue());
			output.setTeam1(input.getTeam1());
			output.setTeam2(input.getTeam2());
			output.setTossDecision(input.getToss_decision());
			output.setTossWinner(input.getToss_winner());
			output.setUmpire1(input.getUmpire1());
			output.setUmpire2(input.getUmpire2());
			output.setResult(input.getResult());
			output.setWinner(input.getWinner());
			output.setResultMargin(input.getResult_margin());
			
			return output;
		   
		  }

		}