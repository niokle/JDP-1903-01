package com.kodilla.ecommercee.group.repository;

import com.kodilla.ecommercee.group.domain.Group;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface GroupRepository extends CrudRepository<Group, Long> {
    @Override
    List<Group> findAll();

    @Override
    Group save(Group group);

    @Override
    Optional<Group> findById(Long groupId);
}