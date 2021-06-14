package ecma.ai.lesson6_task2.controller;

import ecma.ai.lesson6_task2.Service.CardService;
import ecma.ai.lesson6_task2.Service.UserService;
import ecma.ai.lesson6_task2.entity.Card;
import ecma.ai.lesson6_task2.entity.User;
import ecma.ai.lesson6_task2.payload.CardDto;
import ecma.ai.lesson6_task2.payload.UserDto;
import ecma.ai.lesson6_task2.payload.types.ApiResponse;
import ecma.ai.lesson6_task2.payload.ClientDto;
import ecma.ai.lesson6_task2.repository.CardRepository;
import ecma.ai.lesson6_task2.repository.RoleRepository;
import ecma.ai.lesson6_task2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/card")
public class CardController {

    @Autowired
    CardRepository cardRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    CardService service;
    @PostMapping
    public HttpEntity<?> cardAddToClient(@RequestBody ClientDto clientDto) {

//        List<Card> all = cardRepository.findAll();
        List<Card> allByActiveIsTrue = cardRepository.findAllByActiveIsFalse();

        Card card = allByActiveIsTrue.get(0);

        User user = new User();
        user.setFullName(clientDto.getFullName());
        user.setRole(roleRepository.getOne(3));

        userRepository.save(user);
        card.setUser(user);
        card.setActive(true);
        card.setFullName(clientDto.getFullName());
        cardRepository.save(card);

        return ResponseEntity.ok().body(new ApiResponse("Success!", true));
    }



    @GetMapping("/{id}")
    public HttpEntity<?> getone(@PathVariable Integer id) {
        return ResponseEntity.ok().body(service.getId(id));
    }

    @PostMapping("/addCard")
    public HttpEntity<?> addAtm(@RequestBody CardDto dto) {
        return ResponseEntity.ok().body(service.addCard(dto));
    }

    @PutMapping("/{id}")
    public HttpEntity<?> put(@RequestBody CardDto dto, @PathVariable Integer id) {
        return ResponseEntity.ok().body(service.editCard( id,dto));
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> dell(@PathVariable Integer id) {
        return ResponseEntity.ok().body(service.delete(id));
    }

}
