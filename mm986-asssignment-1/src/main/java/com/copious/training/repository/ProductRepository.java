package com.copious.training.repository;

import com.copious.training.entity.Item;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * @author Mahesh More.
 * <p>
 * Repository class to query Item
 */
@Cacheable("OrderCartCache")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@Repository
public interface ProductRepository extends JpaRepository<Item, String> {

    Item getItemBySku(@Param("sku") String sku) throws Exception;
}
