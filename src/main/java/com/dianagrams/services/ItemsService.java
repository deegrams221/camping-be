package com.dianagrams.services;

import java.util.ArrayList;
import java.util.List;

import com.dianagrams.View.ItemCount;
import com.dianagrams.models.Items;

public interface ItemsService {
    ArrayList<Items> findAll();

    Items findItemByType(String type);

    List<ItemCount> getItemCount();
}
