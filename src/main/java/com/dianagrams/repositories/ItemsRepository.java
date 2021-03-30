package com.dianagrams.repositories;

import java.util.List;

import com.dianagrams.View.ItemCount;
import com.dianagrams.models.Items;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ItemsRepository extends CrudRepository<Items, Long> {
    Items findByItemtype(String type);

    @Query(value = "SELECT itemtype, count(a.itemid) as count FROM animal a JOIN memberlistitems z ON a.itemID=z.itemid GROUP BY a.itemid", nativeQuery = true)
    List<ItemCount> countItems();
}
