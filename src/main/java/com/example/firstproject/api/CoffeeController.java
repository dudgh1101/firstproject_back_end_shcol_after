package com.example.firstproject.api;

import com.example.firstproject.Service.CoffeeService;
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
    private CoffeeService coffeeService;

    //get
    @GetMapping("/api/coffees")
    public List<Coffee> index(){
        return coffeeService.index();
    }
    
    //단일 조회
    @GetMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> show(@PathVariable Long id){
        Coffee coffee = coffeeService.index(id);
        return (coffee != null) ?
                ResponseEntity.status(HttpStatus.OK).body(coffee) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
//
//
//    //post(생성)

    @PostMapping("/api/coffees")
    public ResponseEntity<Coffee> post(@RequestBody CoffeeDto dto){

        Coffee created = coffeeService.create(dto);  // ✅ 소문자 coffeeService (인스턴스)

        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
//
    //patch
    @PatchMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> update(@PathVariable Long id, @RequestBody CoffeeDto dto){

        Coffee update = coffeeService.update(id,dto);
        return (update != null) ?
                ResponseEntity.status(HttpStatus.OK).body(update) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
//    //delete(삭제)
//
    @DeleteMapping("/api/coffees/{id}")
    public ResponseEntity<Coffee> delete(@PathVariable Long id){
        Coffee delete = coffeeService.delete(id);

        return (delete != null) ?
                ResponseEntity.status(HttpStatus.OK).body(delete) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

}
