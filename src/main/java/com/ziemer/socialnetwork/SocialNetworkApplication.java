package com.ziemer.socialnetwork;

import com.ziemer.socialnetwork.model.neo4j.Person;
import com.ziemer.socialnetwork.repository.Neo4JPersonRepository;
import com.ziemer.socialnetwork.repository.PostgresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StopWatch;

import java.util.*;

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

		List<Person> randoms = neo4JPersonRepository.find20randomPersons();


		StopWatch stopWatch = new StopWatch();

		HashMap<Integer, long[]> neo4jTimes = new HashMap<>();
		HashMap<Integer, long[]> postgresTimes = new HashMap<>();

		long[] neo4jTimesArray = new long[20];
		long[] postgresTimesArray = new long[20];

		int depth = 1;

//		Depth 1
		for (int j = 0; j < randoms.size(); j++) {
			stopWatch.start("Fetching Neo4J with depth 1");
			neo4JPersonRepository.findEndorsements(randoms.get(j).getName());
			stopWatch.stop();
			neo4jTimesArray[j] = stopWatch.getLastTaskTimeMillis();

			stopWatch.start("Fetching PostgreSQL with depth 1");
			postgresRepository.findEndorsementsByName(randoms.get(j).getName());
			stopWatch.stop();
			postgresTimesArray[j] = stopWatch.getLastTaskTimeMillis();
		}

		neo4jTimes.put(depth, neo4jTimesArray);
		postgresTimes.put(depth, postgresTimesArray);
		depth++;

		neo4jTimesArray = new long[20];
		postgresTimesArray = new long[20];

//		Depth 2
		for (int j = 0; j < randoms.size(); j++) {
			stopWatch.start("Fetching Neo4J with depth 2");
			neo4JPersonRepository.findEndorsementsDepth2(randoms.get(j).getName());
			stopWatch.stop();
			neo4jTimesArray[j] = stopWatch.getLastTaskTimeMillis();

			stopWatch.start("Fetching PostgreSQL with depth 2");
			postgresRepository.findEndorsementsByNameDepth2(randoms.get(j).getName());
			stopWatch.stop();
			postgresTimesArray[j] = stopWatch.getLastTaskTimeMillis();
		}


		neo4jTimes.put(depth, neo4jTimesArray);
		postgresTimes.put(depth, postgresTimesArray);
		depth++;

		neo4jTimesArray = new long[20];
		postgresTimesArray = new long[20];

		// Depth 3
		for (int j = 0; j < randoms.size(); j++) {
			stopWatch.start("Fetching Neo4J with depth 3");
			neo4JPersonRepository.findEndorsementsDepth3(randoms.get(j).getName());
			stopWatch.stop();
			neo4jTimesArray[j] = stopWatch.getLastTaskTimeMillis();

			stopWatch.start("Fetching PostgreSQL with depth 3");
			postgresRepository.findEndorsementsByNameDepth3(randoms.get(j).getName());
			stopWatch.stop();
			postgresTimesArray[j] = stopWatch.getLastTaskTimeMillis();
		}

		neo4jTimes.put(depth, neo4jTimesArray);
		postgresTimes.put(depth, postgresTimesArray);
		depth++;

		neo4jTimesArray = new long[20];
		postgresTimesArray = new long[20];

		// Depth 4
		for (int j = 0; j < randoms.size(); j++) {
			stopWatch.start("Fetching Neo4J with depth 1");
			neo4JPersonRepository.findEndorsementsDepth4(randoms.get(j).getName());
			stopWatch.stop();
			neo4jTimesArray[j] = stopWatch.getLastTaskTimeMillis();

			stopWatch.start("Fetching PostgreSQL with depth 4");
			postgresRepository.findEndorsementsByNameDepth4(randoms.get(j).getName());
			stopWatch.stop();
			postgresTimesArray[j] = stopWatch.getLastTaskTimeMillis();
		}

		neo4jTimes.put(depth, neo4jTimesArray);
		postgresTimes.put(depth, postgresTimesArray);
		depth++;

		neo4jTimesArray = new long[20];
		postgresTimesArray = new long[20];

		// Depth 5
		for (int j = 0; j < randoms.size(); j++) {
			stopWatch.start("Fetching Neo4J with depth " + depth);
			List<Person> persons = neo4JPersonRepository.findEndorsementsDepth5(randoms.get(j).getName());
			stopWatch.stop();
			neo4jTimesArray[j] = stopWatch.getLastTaskTimeMillis();

			stopWatch.start("Fetching PostgreSQL with depth " + depth);
			postgresRepository.findEndorsementsByNameDepth5(randoms.get(j).getName());
			stopWatch.stop();
			postgresTimesArray[j] = stopWatch.getLastTaskTimeMillis();
		}

		neo4jTimes.put(depth, neo4jTimesArray);
		postgresTimes.put(depth, postgresTimesArray);

		System.out.println("PostgreSQL ------------  Neo4j");
		System.out.println("avg -- median -------- avg -- median");
		for (int i = 1; i <= depth; i++) {

			long postgresAvg;
			long postgresMedian;
			long neo4jAvg;
			long neo4jMedian;

			long neoTotal = 0;
			long postTotal = 0;
			for (int j = 0; j < 20; j++) {
				neoTotal += neo4jTimes.get(i)[j];
				postTotal += postgresTimes.get(i)[j];
			}

			neo4jAvg = neoTotal / 20;
			postgresAvg = postTotal / 20;

			Arrays.sort(neo4jTimes.get(i));
			Arrays.sort(postgresTimes.get(i));

			assert(postgresTimes.get(i)[10] < postgresTimes.get(i)[11]);
			assert(neo4jTimes.get(i)[10] < neo4jTimes.get(i)[11]);
			postgresMedian = postgresTimes.get(i)[postgresTimes.get(i).length / 2];
			neo4jMedian = neo4jTimes.get(i)[neo4jTimes.get(i).length / 2];

			System.out.println("Depth " + i +  ":     " + postgresAvg + " -- " + postgresMedian + " ---- " + neo4jAvg + " -- " + neo4jMedian);
		}
	}
}
