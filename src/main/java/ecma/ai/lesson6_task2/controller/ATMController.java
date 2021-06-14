package ecma.ai.lesson6_task2.controller;

import ecma.ai.lesson6_task2.Service.ATMservice;
import ecma.ai.lesson6_task2.Service.MainService;
import ecma.ai.lesson6_task2.payload.ATMWithdrawalDTO;
import ecma.ai.lesson6_task2.payload.AtmAddDTO;
import ecma.ai.lesson6_task2.payload.PulKiritDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;

@RestController
@RequestMapping("api/ATM")
public class ATMController {
    @Autowired
    ATMservice atMservice;
    @Autowired
    MainService mainService;

    @GetMapping
    public HttpEntity<?> getAtm() {
        return ResponseEntity.ok().body(atMservice.getAll());
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getone(@PathVariable Integer id) {
        return ResponseEntity.ok().body(atMservice.getOne(id));
    }

    @PostMapping
    public HttpEntity<?> addAtm(@RequestBody AtmAddDTO atmAddDTO) {
        return ResponseEntity.ok().body(atMservice.addAtm(atmAddDTO));
    }

    @PutMapping("/edit/{id}")
    public HttpEntity<?> put(@Valid @RequestBody AtmAddDTO atmAddDTO, @PathVariable Integer id) {
        return ResponseEntity.ok().body(atMservice.editAtm(atmAddDTO, id));
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> dell(@PathVariable Integer id) {
        return ResponseEntity.ok().body(atMservice.delete(id));
    }

    @PostMapping("/withdrawl")
    public HttpEntity<?> withdrawl(@Valid @RequestBody ATMWithdrawalDTO withdrawalDTO) throws MessagingException {
        return ResponseEntity.ok().body(mainService.withdrawal(withdrawalDTO));
    }

    @PostMapping("/paynet")
    public HttpEntity<?> hisobToldir(@Valid @RequestBody PulKiritDto kiritDto){
        return ResponseEntity.ok().body(mainService.cardniToldirish(kiritDto));
    }

}
