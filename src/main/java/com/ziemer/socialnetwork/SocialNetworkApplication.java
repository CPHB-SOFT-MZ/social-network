package com.ziemer.socialnetwork;

import com.ziemer.socialnetwork.model.neo4j.Person;
import com.ziemer.socialnetwork.repository.Neo4JPersonRepository;
import com.ziemer.socialnetwork.repository.PostgresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;

@SpringBootApplication
public class SocialNetworkApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SocialNetworkApplication.class, args).close();
	}

	@Autowired
	Neo4JPersonRepository neo4JPersonRepository;

	@Autowired
	PostgresRepository postgresRepository;

	@Override
	public void run(String... args) throws Exception {

		List<Person> randoms = neo4JPersonRepository.find10randomPersons();


		StopWatch stopWatch = new StopWatch();

		HashMap<Integer, long[]> neo4jTimes = new HashMap<>();
		HashMap<Integer, long[]> postgresTimes = new HashMap<>();

		long[] neo4jTimesArray = new long[20];
		long[] postgresTimesArray = new long[20];

		int depth = 1;

		// Depth 1
//		for (int j = 0; j < randoms.size(); j++) {
//			stopWatch.start("Fetching Neo4J with depth 1");
//			neo4JPersonRepository.findEndorsements(randoms.get(j).getName());
//			stopWatch.stop();
//			neo4jTimesArray[j] = stopWatch.getLastTaskTimeMillis();
//
//			stopWatch.start("Fetching PostgreSQL with depth 1");
//			postgresRepository.findEndorsements(randoms.get(j).getName());
//			stopWatch.stop();
//			postgresTimesArray[j] = stopWatch.getLastTaskTimeMillis();
//		}
//
//		neo4jTimes.put(depth, neo4jTimesArray);
//		postgresTimes.put(depth, postgresTimesArray);
//		depth++;
//
//		neo4jTimesArray = new long[20];
//		postgresTimesArray = new long[20];

//		// Depth 2
		for (int j = 0; j < randoms.size(); j++) {
			stopWatch.start("Fetching Neo4J with depth 1");
			neo4JPersonRepository.findEndorsementsDepth2(randoms.get(j).getName());
			stopWatch.stop();
			neo4jTimesArray[j] = stopWatch.getLastTaskTimeMillis();

			stopWatch.start("Fetching PostgreSQL with depth 1");
			postgresRepository.findEndorsementsDepth2(randoms.get(j).getName());
			stopWatch.stop();
			postgresTimesArray[j] = stopWatch.getLastTaskTimeMillis();
		}

		for (int i = 0; i < neo4jTimesArray.length; i++) {
			System.out.println("Neo4J time: " + neo4jTimesArray[i]);
			System.out.println("Postgres time: " + postgresTimesArray[i]);
		}
//
//		neo4jTimes.put(depth, neo4jTimesArray);
//		postgresTimes.put(depth, postgresTimesArray);
//		depth++;
//
//		neo4jTimesArray = new long[20];
//		postgresTimesArray = new long[20];
//
//		// Depth 3
//		for (int j = 0; j < randoms.size(); j++) {
//			stopWatch.start("Fetching Neo4J with depth 1");
//			neo4JPersonRepository.findEndorsementsDepth3(randoms.get(j).getName());
//			stopWatch.stop();
//			neo4jTimesArray[j] = stopWatch.getLastTaskTimeMillis();
//
//			stopWatch.start("Fetching PostgreSQL with depth 1");
//			postgresRepository.findEndorsements(randoms.get(j).getName());
//			stopWatch.stop();
//			postgresTimesArray[j] = stopWatch.getLastTaskTimeMillis();
//		}
//
//		neo4jTimes.put(depth, neo4jTimesArray);
//		postgresTimes.put(depth, postgresTimesArray);
//		depth++;
//
//		neo4jTimesArray = new long[20];
//		postgresTimesArray = new long[20];
//
//		// Depth 4
//		for (int j = 0; j < randoms.size(); j++) {
//			stopWatch.start("Fetching Neo4J with depth 1");
//			neo4JPersonRepository.findEndorsementsDepth4(randoms.get(j).getName());
//			stopWatch.stop();
//			neo4jTimesArray[j] = stopWatch.getLastTaskTimeMillis();
//
//			stopWatch.start("Fetching PostgreSQL with depth 1");
//			postgresRepository.findEndorsements(randoms.get(j).getName());
//			stopWatch.stop();
//			postgresTimesArray[j] = stopWatch.getLastTaskTimeMillis();
//		}
//
//		neo4jTimes.put(depth, neo4jTimesArray);
//		postgresTimes.put(depth, postgresTimesArray);
//		depth++;
//
//		neo4jTimesArray = new long[20];
//		postgresTimesArray = new long[20];
//
//		// Depth 5
//		for (int j = 0; j < randoms.size(); j++) {
//			stopWatch.start("Fetching Neo4J with depth 1");
//			neo4JPersonRepository.findEndorsementsDepth5(randoms.get(j).getName());
//			stopWatch.stop();
//			neo4jTimesArray[j] = stopWatch.getLastTaskTimeMillis();
//
//			stopWatch.start("Fetching PostgreSQL with depth 1");
//			postgresRepository.findEndorsements(randoms.get(j).getName());
//			stopWatch.stop();
//			postgresTimesArray[j] = stopWatch.getLastTaskTimeMillis();
//		}
//
//		neo4jTimes.put(depth, neo4jTimesArray);
//		postgresTimes.put(depth, postgresTimesArray);



		// Calculate average for each depth and each tech

		// Calculate median for each depth and each tech
	}
}
