package com.test.controller;

import com.test.controller.response.UserSearchResponse;
import com.test.model.UserModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerIT {

    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate template;

    @Before
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port + "/user");
    }

    @Test
    public void getUserEntity() {
        ResponseEntity<UserModel> response = template.getForEntity(base.toString() + "/1", UserModel.class);
        assertTrue(response.getStatusCode().value() == 200);
        assertEquals(response.getBody().getId(), 1);
    }

    @Test
    public void getUserEntity_fail() {
        ResponseEntity<UserModel> response = template.getForEntity(base.toString() + "/99", UserModel.class);
        assertTrue(response.getStatusCode() == HttpStatus.NOT_FOUND);
    }

    @Test
    public void findUsersByFirstName() {
        //Note HSQLDB can be set to be case insensitive....
        String urlParam = "/find?firstName=Sam";
        ResponseEntity<UserSearchResponse> response = template.getForEntity(
                base.toString() + urlParam, UserSearchResponse.class);
        assertTrue(response.getStatusCode().value() == 200);
        assertTrue(response.getBody().getUserModels().size() > 0);
    }


}