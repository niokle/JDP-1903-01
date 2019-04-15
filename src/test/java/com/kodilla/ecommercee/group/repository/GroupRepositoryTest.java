package com.kodilla.ecommercee.group.repository;

import com.kodilla.ecommercee.group.domain.Group;
import com.kodilla.ecommercee.group.repository.GroupRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class GroupRepositoryTest {
    @Autowired
    private GroupRepository groupRepository;

    @Test
    public void saveGroupRepository() {
        //given
        Group group = new Group("test", "test");

        //when
        groupRepository.save(group);

        //then
        Assert.assertNotNull(group.getGroupId());

        //cleanup
        groupRepository.delete(group);
    }

    @Test
    public void testFindAll() {
        //given
        Group group = new Group("test", "test");
        Group group1 = new Group("test1", "test1");
        Group group2 = new Group("test2", "test2");
        int sizeGroupBeforeSave = groupRepository.findAll().size();
        groupRepository.save(group);
        groupRepository.save(group1);
        groupRepository.save(group2);

        //when
        List<Group> showGroups = groupRepository.findAll();

        //then
        Assert.assertEquals(sizeGroupBeforeSave + 3, showGroups.size());

        //cleanUp
        groupRepository.deleteAll(showGroups);

    }
}