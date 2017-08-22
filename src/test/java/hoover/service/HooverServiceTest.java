package hoover.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import hoover.dto.HooverRequest;
import hoover.dto.HooverResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static org.mockito.MockitoAnnotations.initMocks;

public class HooverServiceTest {

    private HooverService hooverService;
    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        initMocks(this);
        hooverService = new HooverService();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void shouldCalculate() throws IOException {
        HooverRequest request = getDefaultHooverRequest();
        HooverResponse shouldRespond = getDefaultHooverResponse();

        HooverResponse response = hooverService.calculate(request);

        response.equals(shouldRespond);

        System.out.println(response.toString());



    }

    private HooverRequest getDefaultHooverRequest() throws IOException {
        String requestAsJson = readAsString("src/test/resource/hoover/corridorPatchesRequest.json");
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
