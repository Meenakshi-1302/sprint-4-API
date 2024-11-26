package com.rts.tap.service.impl.clientpartner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.rts.tap.dao.MRFDao;
import com.rts.tap.model.MRF;
import com.rts.tap.serviceimplementation.MRFServiceImpl;

@ExtendWith(MockitoExtension.class)
public class MRFServiceImplTest {

    @Mock
    private MRFDao mrfDao;

    @InjectMocks
    private MRFServiceImpl mrfService;

    private MRF mrf;

    @BeforeEach
    void setUp() {
        mrf = new MRF();
        mrf.setMrfId(1L);
    }

    @Test
    void testAddMrf() {
        when(mrfDao.mrfSave(mrf)).thenReturn(mrf);
        MRF savedMrf = mrfService.addMrf(mrf);
        assertEquals(mrf, savedMrf);
        verify(mrfDao, times(1)).mrfSave(mrf);
    }

    @Test
    void testUpdateMrf() {
        when(mrfDao.mrfUpdate(1L, mrf)).thenReturn(mrf);
        MRF updatedMrf = mrfService.updateMrf(1L, mrf);
        assertEquals(mrf, updatedMrf);
        verify(mrfDao, times(1)).mrfUpdate(1L, mrf);
    }

    @Test
    void testDeleteMrfById() {
        when(mrfDao.mrfDelete(1L)).thenReturn("Deleted");
        String result = mrfService.deleteMrfById(1L);
        assertEquals("Deleted", result);
        verify(mrfDao, times(1)).mrfDelete(1L);
    }

    @Test
    void testGetMrfById() {
        when(mrfDao.getMrf(1L)).thenReturn(mrf);
        MRF foundMrf = mrfService.getMrfById(1L);
        assertEquals(mrf, foundMrf);
        verify(mrfDao, times(1)).getMrf(1L);
    }

    @Test
    void testGetAllMrf() {
        List<MRF> mrfList = Arrays.asList(mrf);
        when(mrfDao.getAllMRF()).thenReturn(mrfList);
        List<MRF> result = mrfService.getAllMrf();
        assertEquals(mrfList, result);
        verify(mrfDao, times(1)).getAllMRF();
    }
}
