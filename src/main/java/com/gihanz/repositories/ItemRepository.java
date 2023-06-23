package com.gihanz.repositories;

import com.gihanz.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item,String> {

    @Query(nativeQuery = true, value = "SELECT * FROM tbl_t_items i order by i.PUB_DATE desc limit 10")
    List<Item>getLastUpdatedOrSaveTenItem();
}
