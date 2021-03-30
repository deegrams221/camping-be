package com.dianagrams.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "item")
public class Items {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long itemid;

    private String itemtype;

    public long getItemid() {
        return itemid;
    }

    public void setItemid(long itemid) {
        this.itemid = itemid;
    }

    public String getItemtype() {
        return itemtype;
    }

    public void setItemtype(String itemtype) {
        this.itemtype = itemtype;
    }

    @Override
    public String toString() {
        return "Item{" + "itemid=" + itemid + ", itemtype='" + itemtype + '}';
    }
}
