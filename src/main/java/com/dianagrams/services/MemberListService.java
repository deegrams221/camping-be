package com.dianagrams.services;

import java.util.ArrayList;

import com.dianagrams.models.MemberList;

public interface MemberListService {
    ArrayList<MemberList> findAll();

    MemberList findMemberListById(long id);

    MemberList findMemberListByName(String name);

    void delete(long id);

    MemberList save(MemberList memberList);

    MemberList update(MemberList memberList, long id);
}
