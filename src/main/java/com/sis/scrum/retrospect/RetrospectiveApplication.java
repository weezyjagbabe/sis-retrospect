package com.sis.scrum.retrospect;

//import com.sis.scrum.retrospect.domain.hateoas.listener.PaginatedResultsRetrievedEventDiscoverabilityListener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * The type Retrospective application.
 */
@SpringBootApplication
@EnableJpaRepositories("com.sis.scrum.retrospect.repositories")
@EntityScan("com.sis.scrum.retrospect.models")
public class RetrospectiveApplication {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
		SpringApplication.run(RetrospectiveApplication.class, args);
	}

}
