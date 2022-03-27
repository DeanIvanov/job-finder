package com.uni.jobfinder;
import org.springframework.boot.SpringApplication;
import com.uni.jobfinder.services.JobsBGService;
import com.uni.jobfinder.services.impl.JobsBGServiceImpl;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Scanner;

@SpringBootApplication
public class JobFinderApplication {



	public static void main(String[] args) {
//		SpringApplication.run(JobFinderApplication.class, args);

		// Pick the desired timeout
		Scanner scanner = new Scanner(System.in);  // Create a Scanner object
		System.out.println("Please choose the request timeout duration (recommended value: 5 or higher): ");
		int timeout = scanner.nextInt();
		System.out.println("\nTimeout duration has been set to " + timeout + ". Please wait. The offers are being processed...\n");

		JobsBGService jobsBGService = new JobsBGServiceImpl();

		System.out.println(jobsBGService.getAllOffers(timeout));

		System.out.println(jobsBGService.getLimited(5, timeout));
	}
}


