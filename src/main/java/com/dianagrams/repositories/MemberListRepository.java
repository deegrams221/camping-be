package com.dianagrams.repositories;

import com.dianagrams.View.JustTheCount;
import com.dianagrams.models.MemberList;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface MemberListRepository extends CrudRepository<MemberList, Long> {
    @Modifying
    @Query(value = "DELETE FROM memberlistitems WHERE memberlistid = :memberlistid", nativeQuery = true)
    void deleteMemberListFromMemberListItems(long memberlistid);

    @Query(value = "SELECT COUNT(*) as count FROM memberlistitems WHERE memberlistid = :memberlistid AND itemid = :itemid", nativeQuery = true)
    JustTheCount checkMemberListItemCombo(long memberlistid, long itemid);

    MemberList findMemberListbyMemberListname(String name);
}
