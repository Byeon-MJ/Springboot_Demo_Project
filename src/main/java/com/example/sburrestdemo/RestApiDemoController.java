package com.example.sburrestdemo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/coffees") // 반복 매핑 URI 제거
public class RestApiDemoController {
    private List<Coffee> coffees = new ArrayList<>();

    public RestApiDemoController(){
        coffees.addAll(List.of(
                new Coffee("Cafe Cereze"),
                new Coffee("Cafe Ganador"),
                new Coffee("Cafe Lareno"),
                new Coffee("Cafe Tres Pontas")
        ));
    }

//    @RequestMapping(value = "/coffes", method = RequestMethod.GET)
//    Iterable<Coffee> getCoffees(){
//        return coffees;
//    }

    // annotation 변경으로 가독성 향상
//    @GetMapping("/coffees")
    @GetMapping
    Iterable<Coffee> getCoffees(){
        return coffees;
    }

//    @GetMapping("/coffees/{id}")
    @GetMapping("/{id}")
    Optional<Coffee> getCoffeesById(@PathVariable String id){
        for (Coffee c: coffees){
            if (c.getId().equals(id)){
                return Optional.of(c);
            }
        }

        return Optional.empty();
    }

//    @PostMapping("/coffees")
    @PostMapping
    Coffee postcoffee(@RequestBody Coffee coffee){
        coffees.add(coffee);
        return coffee;
    }

//    @PutMapping("/coffees/{id}")
    /* 
    @PutMapping("/{id}")
    Coffee putCoffee(@PathVariable String id, @RequestBody Coffee coffee){
        int coffeeIndex = -1;

        for (Coffee c: coffees){
            if (c.getId().equals(id)){
                coffeeIndex = coffees.indexOf(c);
                coffees.set(coffeeIndex, coffee);
            }
        }
        return (coffeeIndex == -1) ? postCoffee(coffee) : coffee;
    }
    */
    
    //Put HTTP 상태코드 반영
    @PutMapping("/{id}")
    ResponseEntity<Coffee> putCoffee(@PathVariable String id, @RequestBody Coffee coffee){
        int coffeeIndex = -1;
        
        for (Coffee c: coffees){
            if (c.getId().equals(id)){
                coffeeIndex = coffees.indexOf(c);
                coffees.set(coffeeIndex, coffee);
            }
        }
        return (coffeeIndex == -1) ?
                new ResponseEntity<>(postcoffee(coffee), HttpStatus.CREATED) :
                new ResponseEntity<>(coffee, HttpStatus.OK);
    }

//    @DeleteMapping("/coffees/{id}")
    @DeleteMapping("/{id}")
    void deleteCoffee(@PathVariable String id){
        coffees.removeIf(c -> c.getId().equals(id));
    }

}
