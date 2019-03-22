package com.ezn.customer.portal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ezn.customer.portal.domain.Property;

/**
 * Spring Data JPA repository for the User entity.
 */
@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
    
    Optional<Property> findPropertyByEmail(String email);
    
    List<Property> findPropertiesByEmail(String email);

}
