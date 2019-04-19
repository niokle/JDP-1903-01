package com.kodilla.ecommercee.group.service;

import com.kodilla.ecommercee.group.domain.Group;
import com.kodilla.ecommercee.group.repository.GroupRepository;
import com.kodilla.ecommercee.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {
    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<Group> getGroupList() {
        return groupRepository.findAll();
    }

    public Optional<Group> getGroupWithGroupId(final Long groupId) {
        return groupRepository.findById(groupId);
    }

    public void createGroup() {

    }

    public void updateGroup() {

    }
}
