package com.kodilla.ecommercee.group.controller;

import com.kodilla.ecommercee.group.dto.GroupDto;
import com.kodilla.ecommercee.group.exception.GroupNotFoundException;
import com.kodilla.ecommercee.group.mapper.GroupMapper;
import com.kodilla.ecommercee.group.repository.GroupRepository;
import com.kodilla.ecommercee.group.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("ecommercee/group/")
public class GroupController {
    @Autowired
    private GroupMapper groupMapper;

    @Autowired
    private GroupService groupService;


    @GetMapping(value = "groups")
    public List<GroupDto> getGroups() {
        return groupMapper.mapGroupListToGropDtosList(groupService.getGroupList());
    }

    @GetMapping(value = "group")
    public GroupDto getGroup(@RequestParam Long id) throws GroupNotFoundException {
        return groupMapper.mapGroupToGroupDto(groupService.getGroupWithGroupId(id).orElseThrow(GroupNotFoundException::new));
    }

    @PostMapping(value = "createGroup", consumes = APPLICATION_JSON_VALUE)
    public void createGroup(@RequestBody GroupDto groupDto) {
        groupService.createGroup();
    }

    @PutMapping(value = "updateGroup", consumes = APPLICATION_JSON_VALUE)
    public void updateGroup(@RequestBody GroupDto groupDto) {
        groupService.updateGroup();
    }

}