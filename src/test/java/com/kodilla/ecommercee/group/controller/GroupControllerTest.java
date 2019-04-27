package com.kodilla.ecommercee.group.controller;

import com.kodilla.ecommercee.cart.controller.CartControllerTest;
import com.kodilla.ecommercee.group.domain.Group;
import com.kodilla.ecommercee.group.dto.GroupDto;
import com.kodilla.ecommercee.group.exception.GroupNotFoundException;
import com.kodilla.ecommercee.group.repository.GroupRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GroupControllerTest {
    @Autowired
    GroupController groupController;

    @Autowired
    GroupRepository groupRepository;

    Logger LOGGER = LoggerFactory.getLogger(CartControllerTest.class);

    @Test
    public void testGetGroups() {
        //Given
        Group testGroup1 = new Group(1l, "TestGroup1", "Test1", new ArrayList<>());
        Group testGroup2 = new Group(2L, "TestGroup2", "Test2", new ArrayList<>());
        groupRepository.save(testGroup1);
        groupRepository.save(testGroup2);
        //when
        List<GroupDto> groups = groupController.getGroups();
        //then
        Assert.assertEquals(2, groups.size());
        //CleanUp
        groupRepository.delete(testGroup1);
        groupRepository.delete(testGroup2);
    }

    @Test
    public void testGetGroup() {
        //Given
        Group testGroup1 = new Group(1l, "TestGroup1", "Test1", new ArrayList<>());
        //When
        groupRepository.save(testGroup1);
        //Then
        try {
            GroupDto groupDto = groupController.getGroup(testGroup1.getGroupId());
            Assert.assertEquals("Test1", groupDto.getDescription());
            Assert.assertEquals("TestGroup1", groupDto.getName());
            Assert.assertEquals(1L, groupDto.getGroupId(), 0.001);
            LOGGER.info("TEST OK");
        } catch (GroupNotFoundException e) {
            LOGGER.error("TestFailed");
        } finally {
            groupRepository.delete(testGroup1);
        }
    }


    @Test
    public void createGroup() {
        //Given
        GroupDto testGroupDto1 = new GroupDto(1l, "TestGroup1", "Test1", new ArrayList<>());
        //When
        groupController.createGroup(testGroupDto1);
        //Then
        try {
            GroupDto groupDto = groupController.getGroup(testGroupDto1.getGroupId());
            assertEquals("Test1", groupDto.getDescription());
            LOGGER.info("TEST OK");
        } catch (GroupNotFoundException e) {
            LOGGER.error("TestFailed");
        } finally {
            groupRepository.deleteAll();
        }
    }

    @Test
    public void updateGroup() {
        //Given
        Group testGroup1 = new Group(1l, "TestGroup1", "Test1", new ArrayList<>());
        GroupDto testGroupDto1 = new GroupDto(1L, "TestGroup2", "Test2", new ArrayList<>());
        //When
        groupRepository.save(testGroup1);
        //Then
        groupController.updateGroup(testGroupDto1);
        try {
            GroupDto groupDto = groupController.getGroup(testGroupDto1.getGroupId());
            assertEquals("TestGroup2", groupDto.getName());
            LOGGER.info("TEST OK");
        } catch (GroupNotFoundException e) {
            LOGGER.error("Test failed");
        } finally {
            groupRepository.deleteAll();
        }
    }
}