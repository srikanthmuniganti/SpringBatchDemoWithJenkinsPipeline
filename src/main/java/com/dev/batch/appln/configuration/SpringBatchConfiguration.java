package com.dev.batch.appln.configuration;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.dev.batch.appln.models.MatchInput;
import com.dev.batch.appln.models.MatchOutput;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfiguration {
	
	
	private static final String[] FIELD_NAMES = new String[] {
			
			"id",
			"city",
			"date",
			"player_of_match",
			"venue",
			"neutral_venue",
			" team1",
			"team2",
			"toss_winner",
			"toss_decision",
			"winner",
			"result",
			"result_margin",
			"eliminator",
			"method",
			"umpire1",
			"umpire2"
};

	@Autowired
	public JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	public StepBuilderFactory stepBuilderFactory;
		
	// 1. create a job
	
	@Bean
	public Job createJob(Step step1, JobCompletionNotificationListener  listener) {
				
		return jobBuilderFactory.get("firstJob")
				.incrementer(new RunIdIncrementer())
				.listener(listener)
				.flow(step1)
				.end()
				.build();
	}
	
	// 2. create a step
	
	@Bean
	public Step createStep(JdbcBatchItemWriter<MatchOutput> writer) {
		return stepBuilderFactory.get("step1")
				.<MatchInput, MatchOutput> chunk(25)
				.reader(reader())
				.processor(processor())
				.writer(writer)
				.build();
		
	}

	// Item Reader
	
	@Bean
	public FlatFileItemReader<MatchInput> reader() {

		return new FlatFileItemReaderBuilder<MatchInput>()
				.name("MatchInputReader")
				.resource(new ClassPathResource("IPL Matches.csv"))
				.delimited()
				.names(FIELD_NAMES)
				.fieldSetMapper(new BeanWrapperFieldSetMapper<MatchInput>() {
					{
						setTargetType(MatchInput.class);
					}
				})
				.build();				
				
	}
	
	//Item Processor
	
	@Bean
	public MatchOutputItemProcessor processor() {

		return new MatchOutputItemProcessor();
		
	}
	
	// Item Writer
	
	@Bean
	public JdbcBatchItemWriter<MatchOutput> writer(DataSource dataSource) {
	  return new JdbcBatchItemWriterBuilder<MatchOutput>()
	    .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
	    .sql("INSERT INTO match_output(id,city,date,player_of_match,result,result_margin,team1,team2,toss_decision,toss_winner,umpire1,umpire2,venue,winner) VALUES (:id,:city,:date,:playerOfMatch,:result,:resultMargin,:team1,:team2,:tossDecision,:tossWinner,:umpire1,:umpire2,:venue,:winner)")
	    .dataSource(dataSource)
	    .build();
	}
	
	
//create table match_output (id bigint not null, city varchar(255), date date, player_of_match varchar(255), result varchar(255), result_margin varchar(255), team1 varchar(255), team2 varchar(255), toss_decision varchar(255), toss_winner varchar(255), umpire1 varchar(255), umpire2 varchar(255), venue varchar(255), winner varchar(255), primary key (id))
	
	

}
