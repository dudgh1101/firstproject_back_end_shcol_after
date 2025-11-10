package com.example.firstproject.Service;

import com.example.firstproject.dto.CoffeeDto;
import com.example.firstproject.entity.Coffee;
import com.example.firstproject.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CoffeeService {

    @Autowired
    private CoffeeRepository coffeeRepository;

    public List<Coffee> index() {
        return coffeeRepository.findAll();
    }

    public Coffee index(Long id) {
        return coffeeRepository.findById(id).orElse(null);
    }

    public Coffee create(CoffeeDto dto) {

        Coffee coffee = dto.toEntity();

        if (coffee.getId() != null) {
            return null;
        }
        return coffeeRepository.save(coffee);
    }

    public Coffee update(Long id,CoffeeDto dto) {
        Coffee coffee = dto.toEntity();
        Coffee target = coffeeRepository.findById(id).orElse(null);

        if(target == null || id != coffee.getId()){
            return null;
        }
        target.patch(coffee);
        return coffeeRepository.save(target);
    }

    public Coffee delete(Long id) {
        Coffee target = coffeeRepository.findById(id).orElse(null);

        if(target == null){
            return null;
        }
        coffeeRepository.delete(target);
        return target;
    }
}
