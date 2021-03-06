package com.kodilla.ecommercee.group.controller;

import com.kodilla.ecommercee.group.dto.GroupDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("ecommercee/group/")
public class GroupController {
    @GetMapping(value = "groups")
    public List<GroupDto> getGroups() {
        return new ArrayList<>();
    }

    @GetMapping(value = "group")
    public GroupDto getGroup(@RequestParam Long id) {
        return new GroupDto();
    }

    @PostMapping(value = "createGroup", consumes = APPLICATION_JSON_VALUE)
    public void createGroup(@RequestBody GroupDto groupDto) {
    }

    @PutMapping(value = "updateGroup", consumes = APPLICATION_JSON_VALUE)
    public void updateGroup(@RequestBody GroupDto groupDto) {

    }

}