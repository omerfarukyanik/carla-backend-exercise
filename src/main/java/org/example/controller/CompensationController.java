package org.example.controller;


import org.example.service.CompensationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/compensation_data")
public class CompensationController {

    private final CompensationService compensationService;

    @Autowired
    public CompensationController(CompensationService compensationService) {
        this.compensationService = compensationService;
    }


    @GetMapping
    public ResponseEntity<String> getCompensations(@RequestParam Map<String, String> params) {
        String jsonCompensations = compensationService.getFilteredAndSortedCompensations(params);

        return ResponseEntity.status(HttpStatus.OK).body(jsonCompensations);
    }
}
