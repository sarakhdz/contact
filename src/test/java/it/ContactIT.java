package it;

import com.mongodb.client.MongoClients;
import com.khodadadzade.contact.ContactApplication;
import com.khodadadzade.contact.DataSetUtil;
import io.restassured.RestAssured;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

@RunWith(SpringRunner.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {ContactApplication.class, DataSetUtil.class})
public class ContactIT {

    @LocalServerPort
    String port;
    @Autowired
    MongoOperations mongoOperation;
    @Autowired
    DataSetUtil dataSetUtil;
    String createData;

    @Before
    public void init() {
        RestAssured.port = Integer.valueOf(port);
        RestAssured.basePath = "/resources";
        this.mongoOperation = new MongoTemplate(MongoClients.create(), "test");
        createData = dataSetUtil.getJsonSample("CreateContact.json");

    }

    @Test
    public void testCreateContactShouldReturnCreatedStatus() {
        given().basePath("/contact").header("Content-Type", "application/json").body(createData).put().then().statusCode(201);
    }

    @Test
    public void testFilterContactShouldReturnLastCreatedContact(){
        final JSONObject jsonObject = new JSONObject(createData);
        given().basePath("/contact/search")
                .queryParam("name", jsonObject.get("name"))
                .get()
                .then().statusCode(200).body(containsString("name")).body(containsString("email")).body(containsString("organization"))
                .body(containsString("phoneNumber")).body(containsString("github")).body(containsString("repository"));
    }

}
