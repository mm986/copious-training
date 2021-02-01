package com.copious.training.repository;

import com.copious.training.entity.CartAndBillingDetails;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Mahesh More.
 * <p>
 * Repository class to query BillingDetails
 */
@Cacheable("OrderCartCache")
@Cache( usage = CacheConcurrencyStrategy.READ_ONLY)
@Repository
public interface CartAndBillingRepository extends CrudRepository<CartAndBillingDetails, String> {
}
