package com.inditex.hiring.infraestructure.configuration;

import com.inditex.hiring.application.service.OfferService;
import com.inditex.hiring.application.useCase.CreateOfferImpl;
import com.inditex.hiring.application.useCase.DeleteOfferImpl;
import com.inditex.hiring.application.useCase.RetrieveOfferImpl;
import com.inditex.hiring.domain.ports.OfferRepositoryPort;
import com.inditex.hiring.infraestructure.repository.OfferRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public OfferService offerService(OfferRepositoryPort offerRepositoryPort) {
        return new OfferService(
                new CreateOfferImpl(offerRepositoryPort),
                new RetrieveOfferImpl(offerRepositoryPort),
                new DeleteOfferImpl(offerRepositoryPort)
        );
    }

    @Bean
    public OfferRepositoryPort offerRepositoryPort(OfferRepositoryAdapter adapter) {
        return adapter;
    }

}
