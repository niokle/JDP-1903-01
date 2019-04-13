package com.kodilla.ecommercee.group;

import com.kodilla.ecommercee.group.dao.GroupDao;
import com.kodilla.ecommercee.group.domain.Group;
import com.kodilla.ecommercee.product.domain.Product;
import com.kodilla.ecommercee.product.repository.ProductRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GroupEntityCrudOperationsTest {

    @Autowired
    private GroupDao groupDao;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void createGroupTest() {
        //given
        Group group1 = new Group("group 1", "group 1 desc");
        Group group2 = new Group("group 2", "group 2 desc");
        Group group3 = new Group("group 3", "group 3 desc");

        //when
        int sizeGroupBeforeSave = groupDao.findAll().size();
        groupDao.save(group1);
        groupDao.save(group2);
        groupDao.save(group3);

        //then
        Assert.assertEquals(sizeGroupBeforeSave + 3, groupDao.findAll().size());

        //cleanup
        groupDao.delete(group1);
        groupDao.delete(group2);
        groupDao.delete(group3);
    }

    @Test
    public void readGroupTest() {
        //given
        Group group1 = new Group("group 1", "group 1 desc");

        //when
        groupDao.save(group1);
        Long id1 = group1.getIdGroup();
        Group savedGroup = groupDao.findById(id1).get();

        //then
        Assert.assertEquals(savedGroup.getNameOfGroup(), group1.getNameOfGroup());

        //cleanup
        groupDao.delete(group1);
    }

    @Test
    public void readAllGroupTest() {
        //given
        Group group1 = new Group("group 1", "group 1 desc");
        Group group2 = new Group("group 2", "group 2 desc");
        Group group3 = new Group("group 3", "group 3 desc");

        //when
        groupDao.save(group1);
        groupDao.save(group2);
        groupDao.save(group3);
        List<Group> groups = groupDao.findAll();

        //then
        Assert.assertEquals(3, groups.size());

        //cleanup
        groupDao.delete(group1);
        groupDao.delete(group2);
        groupDao.delete(group3);
    }

    @Test
    public void updateGroupTest() {
        //given
        Group group1 = new Group("group 1", "group 1 desc");

        //when
        groupDao.save(group1);
        Long id1 = group1.getIdGroup();
        String newNameOfGroup = "renamed group 1";
        group1.setNameOfGroup(newNameOfGroup);
        groupDao.save(group1);

        //then
        Assert.assertEquals(newNameOfGroup, groupDao.findById(id1).get().getNameOfGroup());

        //cleanup
        groupDao.delete(group1);
    }

    @Test
    public void deleteGroupTest() {
        //given
        Group group1 = new Group("group 1", "group 1 desc");
        Group group2 = new Group("group 2", "group 2 desc");
        Long idGroup1 = group1.getIdGroup();
        Product product1 = new Product("product 1", "product 1 desc", 100.00, 20L, idGroup1);
        Long idGroup2 = group2.getIdGroup();
        Product product2 = new Product("product 2", "product 2 desc", 200.00, 20L, idGroup2);

        //when
        int sizeGroupsBeforeSave = groupDao.findAll().size();
        groupDao.save(group1);
        groupDao.save(group2);
        int sizeGroupsBeforeDelete = groupDao.findAll().size();
        productRepository.save(product1);
        productRepository.save(product2);
        int sizeProductsBeforeDeleteGroups = productRepository.findAll().size();
        groupDao.delete(group1);
        groupDao.delete(group2);
        int sizeGroupsAfterDelete = groupDao.findAll().size();
        int sizeProductsAfterDeleteGroups = productRepository.findAll().size();

        //then
        Assert.assertEquals(sizeGroupsBeforeSave + 2, sizeGroupsBeforeDelete);
        Assert.assertEquals(sizeGroupsBeforeSave, sizeGroupsAfterDelete);
        Assert.assertEquals(sizeProductsBeforeDeleteGroups, sizeProductsAfterDeleteGroups);

        //cleanup
        productRepository.delete(product1);
        productRepository.delete(product2);
    }
}
