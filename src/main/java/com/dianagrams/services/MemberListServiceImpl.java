package com.dianagrams.services;

import java.util.ArrayList;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import com.dianagrams.models.MemberList;
import com.dianagrams.repositories.MemberListRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "memberlistService")
public class MemberListServiceImpl implements MemberListService {
    private static final Logger logger = LoggerFactory.getLogger(MemberListServiceImpl.class);

    @Autowired
    private MemberListRepository memberlistrepos;

    @Override
    public ArrayList<MemberList> findAll() {
        ArrayList<MemberList> list = new ArrayList<>();
        memberlistrepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public MemberList findMemberListById(long id) {
        return memberlistrepos.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
    }

    @Override
    public MemberList findMemberListByName(String name) throws EntityNotFoundException {
        return null;
    }

    @Transactional
    @Override
    public void delete(long id) throws EntityNotFoundException {
        if (memberlistrepos.findById(id).isPresent()) {
            memberlistrepos.deleteMemberListFromMemberListItems(id);
            memberlistrepos.deleteById(id);

            logger.info("Member List Deleted");
        } else {
            throw new EntityNotFoundException(Long.toString(id));
        }
    }

    @Transactional
    @Override
    public MemberList save(MemberList memberList) {
        MemberList newMemberList = new MemberList();

        newMemberList.setMemberlistname(memberList.getMemberlistname());

        logger.info("Updating a MemberList");
        return memberlistrepos.save(newMemberList);
    }

    @Transactional
    @Override
    public MemberList update(MemberList memberList, long id) {
        MemberList currentMemberList = memberlistrepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));

        if (memberList.getMemberlistname() != null) {
            currentMemberList.setMemberlistname(memberList.getMemberlistname());
        }

        logger.info("Creating a Member List");
        return memberlistrepos.save(currentMemberList);
    }
}
