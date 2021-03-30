package com.dianagrams.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "memberlist")
public class MemberList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long memberlistid;

    private String memberlistname;

    @OneToMany(mappedBy = "memberlist", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("memberlists")
    private List<Items> items = new ArrayList<>();

    public long getMemberid() {
        return memberlistid;
    }

    public void setMemberid(long memberlistid) {
        this.memberlistid = memberlistid;
    }

    public String getMemberlistname() {
        return memberlistname;
    }

    public void setMemberlistname(String memberlistname) {
        this.memberlistname = memberlistname;
    }

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Member List{" + "memberlistid=" + memberlistid + ", memberlistname='" + memberlistname + ", items="
                + items + '}';
    }
}
