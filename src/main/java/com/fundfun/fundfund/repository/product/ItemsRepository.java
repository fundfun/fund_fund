package com.fundfun.fundfund.repository.product;

import com.fundfun.fundfund.domain.product.Items;
import com.fundfun.fundfund.domain.product.Product;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ItemsRepository extends JpaRepository<Items, Integer> {

    List<Items> findByProductTitle(String productTitle);
}