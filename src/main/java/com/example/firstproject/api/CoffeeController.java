package com.example.firstproject.api;

import com.example.firstproject.dto.CoffeeDto;
import com.example.firstproject.entity.Coffee;
import com.example.firstproject.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@RestController
public class CoffeeController {

    @Autowired
    private CoffeeRepository coffeeRepository;

    //get
    @GetMapping("/api/coffees")
    public List<Coffee> index(){
        return coffeeRepository.findAll();
    }
    
    //단일 조회
    @GetMapping("/api/coffees/{id}")
    public Coffee show(@PathVariable Long id){
        return coffeeRepository.findById(id).orElse(null);
    }


    //post(생성)

    @PostMapping("/api/coffees")
    public ResponseEntity<Coffee> post(@RequestBody CoffeeDto dto){
        Coffee coffee = dto.toEntity();
        log.info("coffee: {}",coffee.toString());
        if (coffee.getId() != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        Coffee saved = coffeeRepository.save(coffee);
        log.info("saved: {}",saved.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    //patch
    @PatchMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> update(@PathVariable Long id, @RequestBody CoffeeDto dto){

        Coffee coffee = dto.toEntity();
        log.info("id: {} Coffee: {}",id,coffee.toString());

        Coffee target = coffeeRepository.findById(id).orElse(null);

        if(target == null || id != coffee.getId()){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        target.patch(coffee);
        Coffee update = coffeeRepository.save(target);
        return ResponseEntity.status(HttpStatus.OK).body(update);
    }
    //delete(삭제)

    @DeleteMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> delete(@PathVariable Long id){

        Coffee target = coffeeRepository.findById(id).orElse(null);

        if(target == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        coffeeRepository.delete(target);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

}
