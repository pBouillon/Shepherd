package com.fisæ.shepherd.application.media;

import com.fisæ.shepherd.domain.entities.Media;
import com.fisæ.shepherd.infrastructure.mapping.MediaMapper;
import com.fisæ.shepherd.infrastructure.persistence.repository.MediaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Unit test suite for the {@link MediaQueryService}
 */
@ExtendWith(MockitoExtension.class)
public class MediaQueryServiceTest {

    /**
     * Mocked MediaRepository, acting as the {@link Media} DAO
     */
    @Mock
    private MediaRepository repository;

    /**
     * Instance of the service to be tested, cleaned-up before each test
     */
    private MediaQueryService service;

    /**
     * Setup method, executed before each test
     */
    @BeforeEach
    public void setup() {
        MediaMapper mapper = Mappers.getMapper(MediaMapper.class);

        service = new MediaService(mapper, repository);
    }
    
}
