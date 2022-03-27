package com.uni.jobfinder;
import org.springframework.boot.SpringApplication;
import com.uni.jobfinder.services.JobsBGService;
import com.uni.jobfinder.services.impl.JobsBGServiceImpl;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JobFinderApplication {



	public static void main(String[] args) {
//		SpringApplication.run(JobFinderApplication.class, args);

		JobsBGService jobsBGService = new JobsBGServiceImpl();

		System.out.println(jobsBGService.getAllOffers());

		System.out.println(jobsBGService.getLimited(5));
	}
}


