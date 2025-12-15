package com.logistique.gestion_colis.service;

import com.logistique.gestion_colis.dto.ColisResponse;
import com.logistique.gestion_colis.mapper.ColisMapper;
import com.logistique.gestion_colis.models.Colis;
import com.logistique.gestion_colis.models.ColisStandard;
import com.logistique.gestion_colis.repository.ColisRepository;
import com.logistique.gestion_colis.repository.UserRepository;
import com.logistique.gestion_colis.service.impl.ColisServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ColisServiceImplTest {

    @Mock
    private ColisRepository colisRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ColisMapper colisMapper;

    @InjectMocks
    private ColisServiceImpl colisService;


    @Test
    void getAllColis_ShouldReturnPageOfResponses() {
        // Arrange
        Pageable pageable = mock(Pageable.class);
        ColisStandard colis = new ColisStandard(10.0, "Tetouan");
        Page<Colis> colisPage = new PageImpl<>(Collections.singletonList(colis));

        ColisResponse response = ColisResponse.builder().id("1").build();

        when(colisRepository.findAll(pageable)).thenReturn(colisPage);
        when(colisMapper.toResponse(colis)).thenReturn(response);

        // Act
        Page<ColisResponse> result = colisService.getAllColis(null, pageable);

        // Assert
        assertEquals(1, result.getTotalElements());
        verify(colisRepository).findAll(pageable);
    }

    @Test
    void deleteColis_ShouldCallRepository() {
        // Act
        colisService.deleteColis("123");

        verify(colisRepository, times(1)).deleteById("123");
    }
}