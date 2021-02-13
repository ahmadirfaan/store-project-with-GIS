package com.irfaan.api.inventory.repositories;

import com.irfaan.api.inventory.entities.Stock;
import com.irfaan.api.inventory.entities.StockSummary;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@ExtendWith(MockitoExtension.class)
class StockSummaryRepositoryImplTest {

    @InjectMocks
    private StockRepository stockRepository;

    @Mock
    private EntityManager entityManager;

    @Mock
    private CriteriaBuilder cb;

    @Mock
    private CriteriaQuery<StockSummary> cq;

    @Mock
    private Root<Stock> rootStock;

//    @Test
//    public void findAllSummariesTest() {
//        when(entityManager.getCriteriaBuilder()).thenReturn(cb);
//        when(cb.createQuery(StockSummary.class)).thenReturn(cq);
//        when(cq.from(Stock.class)).thenReturn(rootStock);
//        List<StockSummary> stockSummaryList =
//                stockRepository.findAllSummaries();
//        when(stockRepository.findAllSummaries()).thenReturn(stockSummaryList);
//
//    }
}
