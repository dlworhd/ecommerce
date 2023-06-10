package com.hexagonal.product.adapter.out;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductInventoryEntityRepository extends JpaRepository<ProductInventoryEntity, Long> {
}
