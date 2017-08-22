package hoover.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.ObjectMapper;

import hoover.dto.HooverRequest;
import hoover.dto.HooverResponse;
import hoover.service.HooverService;

public class HooverControllerTest {

    private HooverController hooverController;
    private ObjectMapper objectMapper;

    @Mock
    private HooverService hooverService;

    @Before
    public void setUp() {
        initMocks(this);
        hooverController = new HooverController(hooverService);
        objectMapper = new ObjectMapper();
    }

    @Test
    public void shouldResponseOk() throws IOException {

        HooverRequest request = getDefaultHooverRequest();
        HooverResponse response = getDefaultHooverResponse();

        when(hooverService.calculate(request)).thenReturn(response);

        ResponseEntity<HooverResponse> calculate = hooverController.calculate(request);

        assertEquals(HttpStatus.OK, calculate.getStatusCode());
        assertEquals(response, calculate.getBody());

        verify(hooverService, times(1)).calculate(request);
    }

    private HooverRequest getDefaultHooverRequest() throws IOException {
        String requestAsJson = readAsString("src/test/resource/hoover/request.json");
        return objectMapper.readValue(requestAsJson, HooverRequest.class);
    }

    private HooverResponse getDefaultHooverResponse() throws IOException {
        String responseAsJson = readAsString("src/test/resource/hoover/response.json");
        return objectMapper.readValue(responseAsJson, HooverResponse.class);
    }

    private String readAsString(String path) throws IOException {
        return new Scanner(new File(path)).useDelimiter("\\Z")
                .next();
    }

}