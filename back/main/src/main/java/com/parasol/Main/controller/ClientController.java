package com.parasol.Main.controller;

import com.parasol.Main.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/client")
public class ClientController {
    // TODO : 일단 오류 나는 거 다 주석처리 했습니다 (굿굿)
    @Autowired
    private ClientService clientService;

    @PostMapping("client")
    public String createClient(
            @RequestParam("name") String name,
            @RequestParam("residentNumber") String residentNumber
    ) {
//        String result = clientService.create(name, residentNumber);

//        return result;
        return null;
    }

//    @GetMapping("client")
//    public Client getClient(
//            @RequestParam("id") String id
//    ) {
//        Client result = clientService.findById(id);
//
//        return result;
//    }

}
