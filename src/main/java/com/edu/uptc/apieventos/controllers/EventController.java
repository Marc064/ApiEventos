package com.edu.uptc.apieventos.controllers;

import com.edu.uptc.apieventos.entities.Event;
import com.edu.uptc.apieventos.entities.EventUserRequest;
import com.edu.uptc.apieventos.entities.User;
import com.edu.uptc.apieventos.responses.ResponseHandler;
import com.edu.uptc.apieventos.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {
    @Autowired
    private EventService eventService;

    @GetMapping()
    public ResponseEntity<Object> findAll(){
        try {
            List<Event> result = eventService.findAll();
            return ResponseHandler.generateResponse("Success", HttpStatus.OK,result);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Integer id){
        try {
            Event result = eventService.findById(id);
            return ResponseHandler.generateResponse("Success", HttpStatus.OK,result);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }

    @PostMapping()
    public ResponseEntity<Object> save(@RequestBody EventUserRequest eventUserRequest){
        try {

            Event event = eventUserRequest.getEvent();
            User user = eventUserRequest.getUser();
            System.out.println(eventUserRequest.getUser().getId());
            if(user.getAdmin().equals("admin")) {
                event.setId_User(user.getId());
                Event result = eventService.save(event);
                return ResponseHandler.generateResponse("Success", HttpStatus.OK, result);
            } else {
                return ResponseHandler.generateResponse("User is not admin", HttpStatus.CONFLICT, null);
            }
        } catch(Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

}
