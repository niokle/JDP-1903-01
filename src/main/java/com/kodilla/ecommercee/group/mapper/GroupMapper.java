package com.kodilla.ecommercee.group.mapper;

import com.kodilla.ecommercee.group.domain.Group;
import com.kodilla.ecommercee.group.dto.GroupDto;
import com.kodilla.ecommercee.product.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GroupMapper {
    @Autowired
    private ProductMapper productMapper;

    public Group mapGroupDtoToGroup(final GroupDto groupDto) {
        return new Group(groupDto.getGroupId(),
                            groupDto.getName(),
                            groupDto.getDescription(),
                            groupDto.getProductList().stream()
                                .map(productDto -> productMapper.mapProductDtoToProduct(productDto))
                                .collect(Collectors.toList())
        );
    }

    public GroupDto mapGroupToGroupDto(final Group group) {
        return new GroupDto(group.getGroupId(),
                            group.getGroupName(),
                            group.getDescription(),
                            group.getProductList().stream()
                                .map(product -> productMapper.mapProductToProductDto(product))
                                .collect(Collectors.toList())
        );
    }

    public List<GroupDto> mapGroupListToGropDtosList(final List<Group> groupList) {
        return groupList.stream()
                    .map(group -> mapGroupToGroupDto(group))
                    .collect(Collectors.toList());
    }
}
