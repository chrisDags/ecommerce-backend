package com.dags.ecommerce.repositories;

import com.dags.ecommerce.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
}
