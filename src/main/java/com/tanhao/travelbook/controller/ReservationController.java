package com.tanhao.travelbook.controller;

import com.tanhao.travelbook.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("/reservation")
@Controller
class ReservationController {

    @Autowired
    ReservationService reservationService;

    @ResponseBody
    @RequestMapping( value="/flight",method = RequestMethod.GET)
    public ResponseEntity getFlightByParam(@RequestParam("flightNum") String flightNum, @RequestParam("custName") String custName, @RequestParam("date")String date) {
        try {
            custName = new String(custName.getBytes("ISO-8859-1"), "utf-8");
            reservationService.reservFlight(flightNum, custName, date);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    @ResponseBody
    @RequestMapping( value="/car",method = RequestMethod.GET)
    public ResponseEntity reservCar(@RequestParam("carNum") String carNum, @RequestParam("custName") String custName, @RequestParam("date")String date) {
        try {
            custName = new String(custName.getBytes("ISO-8859-1"), "utf-8");
            carNum = new String(carNum.getBytes("ISO-8859-1"), "utf-8");
            reservationService.reservCar(carNum, custName, date);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    @ResponseBody
    @RequestMapping( value="/flightCancel",method = RequestMethod.GET)
    public ResponseEntity flightReservCancel(@RequestParam("id") String id) {
        try {
            reservationService.flightReservCancel(id);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

}