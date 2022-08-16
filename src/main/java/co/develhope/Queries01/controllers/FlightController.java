package co.develhope.Queries01.controllers;

import co.develhope.Queries01.entities.Flight;
import co.develhope.Queries01.entities.Status;
import co.develhope.Queries01.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightRepository flightRepository;

    public String getRandomString(){
        int leftLimit = 97;
        int rightLimit = 122;
        int targetStringLength = 10;
        Random random = new Random();
        String generatedString = random.ints(leftLimit, rightLimit + 1)
            .limit(targetStringLength)
            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
            .toString();
        return generatedString;
    }

    @GetMapping("")
    public List<Flight> getFlightList(){
        List<Flight> flightList = new ArrayList<>();
        for(int i = 0; i <= 50; i++)
            flightList.add(new Flight(i,getRandomString(),getRandomString(),getRandomString(),Status.ONTIME));
        flightRepository.saveAllAndFlush(flightList);
        return flightList;
    }
}
