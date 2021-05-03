/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Landlord;
import domain.Property;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author sarahaverill
 */
public class PropertyJdbcDAOTest {

    PropertyJdbcDAO p = new PropertyJdbcDAO("jdbc:h2:mem:tests;INIT=runscript from 'src/main/java/dao/databaseSchema.sql'");
    LandlordJdbcDAO l = new LandlordJdbcDAO("jdbc:h2:mem:tests;INIT=runscript from 'src/main/java/dao/databaseSchema.sql'");

    private Property p1;
    private Property p2;
    private Property p3;
    private Landlord l1;
    private Landlord l2;
    private Landlord l3;

    public PropertyJdbcDAOTest() {
    }

    @BeforeEach
    public void setUp() {

        l1 = new Landlord();
        l1.setLandlordId(12345);
        l1.setLandlordPassword("password1");
        l1.setUserName("username1");
        l1.setLandlordPhone("phone1");
        l1.setLandlordEmail("email1@email.com");

        l2 = new Landlord();
        l2.setLandlordEmail("email2@email.com");
        l2.setLandlordPassword("password2");
        l2.setLandlordPhone("phone2");
        l2.setUserName("username2");

        l3 = new Landlord();
        l3.setLandlordEmail("email3@email.com");
        l3.setLandlordPassword("password3");
        l3.setLandlordPhone("phone3");
        l3.setUserName("username3");

        l.saveLandlord(l1);
        l.saveLandlord(l2);
        l.saveLandlord(l3);

        p1 = new Property();
        p2 = new Property();
        p3 = new Property();

        p1.setBedrooms(1);
        p1.setLandlordId(l.getLandlord("username1").getLandlordId());
        p1.setAddress("A1");
        p1.setStatus("D1");
        p1.setPropertyId(12345);

        p2.setBedrooms(3);
        p2.setLandlordId(l.getLandlord("username2").getLandlordId());
        p2.setAddress("A2");
        p2.setStatus("D2");
        p2.setPropertyId(4567);

        p3.setBedrooms(3);
        p3.setLandlordId(l.getLandlord("username3").getLandlordId());
        p3.setAddress("A2");
        p3.setStatus("D2");
        p3.setPropertyId(7895);

        p.saveProperty(p1);
        p.saveProperty(p2);

    }

    @AfterEach
    public void tearDown() {
        p.removeProperty(p1);
        p.removeProperty(p2);
    }

//    @Test
//    public void testFilterByBedroom() {
//        assertThat(p.filterByBedroom(p1.getBedrooms()), hasItem(p1));
//        assertThat(p.filterByBedroom(p1.getBedrooms()), not(hasItem(p2)));
//    }
//    @Test
//    public void testGetAllProperties() {
//        assertThat(p.getAllProperties(), hasItem(p1));
//        assertThat(p.getAllProperties(), hasItem(p2));
//    }
//
//    @Test
//    public void testGetBedrooms() {
//        assertThat(p.getBedrooms(), hasItem(p1.getBedrooms()));
//        assertThat(p.getBedrooms(), hasItem(p2.getBedrooms()));
//    }
//
//    @Test
//    public void testRemoveProperty() {
//        p.removeProperty(p1);
//        assertThat(p.getAllProperties(), hasSize(1));
//        assertThat(p.getAllProperties(), not(hasItem(p1)));
//    }
    @Test
    public void testSaveProperty() {

        p.saveProperty(p3);
        assertThat(p.getAllProperties(), hasSize(3));
        assertThat(p.getPropertyById(12345), samePropertyValuesAs(p1));
        assertThat(p.getPropertyById(4567), samePropertyValuesAs(p2));
        assertThat(p.getPropertyById(7895), samePropertyValuesAs(p3));
    }

//    @Test
//    public void testAddToWishList() {
//    }
}
