package com.projectone.springbootreimbursement;

import com.projectone.springbootreimbursement.models.Rb;
import com.projectone.springbootreimbursement.repositories.Rbrepository;
import com.projectone.springbootreimbursement.service.RbService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RbModelTests {
    private Rbrepository rbRepository;


    private RbService rbService;

    @BeforeEach
    public void initBeforeTest() {
        rbRepository = mock(Rbrepository.class);
        rbService = new RbService();
        rbService.setRbRepository(rbRepository);
    }

    @Test
    public void shouldReturnAllRbs() {
        when(rbRepository.findAll()).thenReturn(Collections.emptyList());
        List<Rb> rbs = rbService.getAllRbs();
        assertTrue(rbs.isEmpty());
    }
}
