package com.kodilla.ecommercee.group.controller;

import com.kodilla.ecommercee.group.controller.GroupController;
import com.kodilla.ecommercee.group.dto.GroupDto;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class GroupControllerTest {
    private GroupController groupController = new GroupController();

    @Test
    public void testGetGroups() {
        //when
        List<GroupDto> groups = groupController.getGroups();

        //then
        Assert.assertEquals(0, groups.size());
    }

    @Test
    public void testGetGroup() {
        //when
        GroupDto groupDto = groupController.getGroup(1L);

        //then
        Assert.assertEquals(null, groupDto.getDescription());
        Assert.assertEquals(null, groupDto.getName());
        Assert.assertEquals(null, groupDto.getGroupId());

    }

}