package com.inditex.hiring.infraestructure.repository;

import com.inditex.hiring.domain.dto.OfferDto;
import com.inditex.hiring.infraestructure.entity.Offer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

@RunWith(MockitoJUnitRunner.class)
public class OfferRepositoryAdapterTest {

    @InjectMocks
    private OfferRepositoryAdapter offerRepositoryAdapter;

    @Mock
    private OfferRepository offerRepository;

    private List<Offer> offerList;

    private Offer offerObject;

    @Before
    public void setUp() {

    }

    @Test
    public void should_get_all_data() {

    }

    @Test
    public void should_save_data_correctly() {

    }
}
