package com.irfaan.api.inventory.repositories.impl;

import com.irfaan.api.inventory.entities.Item;
import com.irfaan.api.inventory.entities.Stock;
import com.irfaan.api.inventory.entities.StockSummary;
import com.irfaan.api.inventory.repositories.StockSummaryRepository;
import com.irfaan.api.inventory.services.ItemService;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class StockSummaryRepositoryImpl implements StockSummaryRepository {

    private EntityManager entityManager;

    private ItemService itemService;

    @Autowired
    public StockSummaryRepositoryImpl(EntityManager entityManager, ItemService itemService) {
        this.entityManager = entityManager;
        this.itemService = itemService;
    }

    @Override
    public List<StockSummary> findAllSummaries() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<StockSummary> criteria = builder.createQuery(StockSummary.class);
        Root<Stock> root = criteria.from(Stock.class);

        criteria.multiselect(root.get("item"), builder.sum(root.get("quantity")))
                .groupBy(root.get("item"));
        return entityManager.createQuery(criteria).getResultList();
    }

    @Override
    public StockSummary findSummaryByItemId(Integer id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<StockSummary> criteriaQuery = builder.createQuery(StockSummary.class);
        Root<Stock> root = criteriaQuery.from(Stock.class);

        Item item = itemService.findById(id);

        criteriaQuery.multiselect(
                root.get("item"), builder.sum(root.get("quantity")))
                .where(builder.equal(root.get("item"), builder.parameter(Item.class, "item")))
                .groupBy(root.get("item"));

        Query query = (Query) entityManager.createQuery(criteriaQuery);
        query.setParameter("item", item);

        return (StockSummary) query.getSingleResult();
    }

}


