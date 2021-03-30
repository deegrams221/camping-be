package com.dianagrams.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.dianagrams.View.ItemCount;
import com.dianagrams.models.Items;
import com.dianagrams.repositories.ItemsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "itemService")
public class ItemsServiceImpl implements ItemsService {
    @Autowired
    private ItemsRepository itemrepos;

    @Override
    public ArrayList<Items> findAll() {
        ArrayList<Items> list = new ArrayList<>();
        itemrepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Items findItemByType(String type) throws EntityNotFoundException {
        Items items = itemrepos.findByItemtype(type);
        if (items == null) {
            throw new EntityNotFoundException("Item " + type + " not found!");
        }
        return items;
    }

    @Override
    public List<ItemCount> getItemCount() {
        return itemrepos.countItems();
    }
}
