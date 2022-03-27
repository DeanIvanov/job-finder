package com.uni.jobfinder;
import org.springframework.boot.SpringApplication;
import com.uni.jobfinder.services.JobsBGService;
import com.uni.jobfinder.services.impl.JobsBGServiceImpl;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JobFinderApplication {



	public static void main(String[] args) {
//		SpringApplication.run(JobFinderApplication.class, args);

		// Pick the desired timeout
		int timeout = 5;

		JobsBGService jobsBGService = new JobsBGServiceImpl();

		System.out.println(jobsBGService.getAllOffers(timeout));

		System.out.println(jobsBGService.getLimited(5, timeout));
	}
}


