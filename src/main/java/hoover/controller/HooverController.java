package hoover.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hoover.dto.HooverRequest;
import hoover.dto.HooverResponse;
import hoover.service.HooverService;

@RestController
public class HooverController {

    private HooverService hooverService;

    @Autowired
    public HooverController(HooverService hooverService) {
        this.hooverService = hooverService;
    }

    @RequestMapping("/")
    public ResponseEntity<HooverResponse> calculate(@RequestBody HooverRequest hooverRequest) {
        HooverResponse hooverResponse = hooverService.calculate(hooverRequest);
        return new ResponseEntity<>(hooverResponse, HttpStatus.OK);
    }
}
