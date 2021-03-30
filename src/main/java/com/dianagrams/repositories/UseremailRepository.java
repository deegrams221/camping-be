package com.dianagrams.repositories;

import java.util.List;

import com.dianagrams.models.Useremail;

import org.springframework.data.repository.CrudRepository;

public interface UseremailRepository extends CrudRepository<Useremail, Long> {
    List<Useremail> findAllByUser_Username(String name);
}
