package com.example.webapp.controller;

import org.example.webapp.controller.GameRestController;
import org.example.webapp.gamedemo.GameDemoApplication;
import org.example.webapp.models.Point;
import org.example.webapp.service.GetAdjuscentNodeService;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@WebMvcTest(value = GameRestController.class, secure = false)
@ContextConfiguration(classes=GameDemoApplication.class)
public class GameRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetAdjuscentNodeService getAdjuscentNodeService;

    @Before
    public void init(){
        getAdjuscentNodeService =  new GetAdjuscentNodeService();
    }

    @Test
    public void testGetAdjuscentNodesNonEmpty() {
        Map<Point, List<Point>> map = getAdjuscentNodeService.getPoints();
        assertTrue(map.size()>0);
    }

    @Test
    public void testInitializeGameAndReturnSuccessResponseBody() throws Exception {
        String expectedInitializedJSON = "{ \"msg\": \"INITIALIZE\", \"body\": { \"newLine\": null, \"heading\": \"Player 1\", \"message\": \"Awaiting Player 1's Move\" } }";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/initialize").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        JSONAssert.assertEquals(expectedInitializedJSON, result.getResponse().getContentAsString(), false);
    }

    @Test
    public void testGameErrorWithSuccessCase() throws Exception {
        String expectedResponse = "{\"error\": \"Invalid type for `id`: Expected INT but got a STRING\"}";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/error").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse().getContentAsString());
        JSONAssert.assertEquals(expectedResponse, result.getResponse().getContentAsString(), false);
    }

}
