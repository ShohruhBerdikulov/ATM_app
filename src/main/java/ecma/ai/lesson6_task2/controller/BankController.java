package ecma.ai.lesson6_task2.controller;

import ecma.ai.lesson6_task2.Service.ATMservice;
import ecma.ai.lesson6_task2.Service.BankService;
import ecma.ai.lesson6_task2.entity.Bank;
import ecma.ai.lesson6_task2.payload.AtmAddDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/BANK")
public class BankController {
    @Autowired
    BankService service;

    @GetMapping("/{id}")
    public HttpEntity<?> getone(@PathVariable Integer id) {
        return ResponseEntity.ok().body(service.getId(id));
    }

    @PostMapping
    public HttpEntity<?> addAtm(@Valid @RequestBody Bank dto) {
        return ResponseEntity.ok().body(service.addBank(dto));
    }

    @PutMapping("/{id}")
    public HttpEntity<?> put(@Valid @RequestBody Bank dto, @PathVariable Integer id) {
        return ResponseEntity.ok().body(service.editBank( id,dto));
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> dell(@PathVariable Integer id) {
        return ResponseEntity.ok().body(service.delete(id));
    }


}
