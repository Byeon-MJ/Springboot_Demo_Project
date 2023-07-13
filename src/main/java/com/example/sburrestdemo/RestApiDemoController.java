package com.example.sburrestdemo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/coffees") // 반복 매핑 URI 제거
public class RestApiDemoController {
    // private List<Coffee> coffees = new ArrayList<>();
    
    // public RestApiDemoController(){
        //     coffees.addAll(List.of(
            //             new Coffee("Cafe Cereze"),
            //             new Coffee("Cafe Ganador"),
            //             new Coffee("Cafe Lareno"),
            //             new Coffee("Cafe Tres Pontas")
            //     ));
            // }

            
    // DB 연결 테스트
    private final CoffeeRepository coffeeRepository;

    public RestApiDemoController(CoffeeRepository coffeeRepository){
        this.coffeeRepository = coffeeRepository;
        this.coffeeRepository.saveAll(List.of(
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
    // @GetMapping
    // Iterable<Coffee> getCoffees(){
    //     return coffees;
    // }
    @GetMapping
    Iterable<Coffee> getCoffees(){
        return coffeeRepository.findAll();
    }


//    @GetMapping("/coffees/{id}")
    // @GetMapping("/{id}")
    // Optional<Coffee> getCoffeesById(@PathVariable String id){
    //     for (Coffee c: coffees){
    //         if (c.getId().equals(id)){
    //             return Optional.of(c);
    //         }
    //     }

    //     return Optional.empty();
    // }
    @GetMapping("/{id}")
    Optional<Coffee> getCoffeeById(@PathVariable String id){
        return coffeeRepository.findById(id);
    }
    


//    @PostMapping("/coffees")
    // @PostMapping
    // Coffee postcoffee(@RequestBody Coffee coffee){
    //     coffees.add(coffee);
    //     return coffee;
    // }
    @PostMapping
    Coffee postCoffee(@RequestBody Coffee coffee){
        return coffeeRepository.save(coffee);
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
    // @PutMapping("/{id}")
    // ResponseEntity<Coffee> putCoffee(@PathVariable String id, @RequestBody Coffee coffee){
    //     int coffeeIndex = -1;
        
    //     for (Coffee c: coffees){
    //         if (c.getId().equals(id)){
    //             coffeeIndex = coffees.indexOf(c);
    //             coffees.set(coffeeIndex, coffee);
    //         }
    //     }
    //     return (coffeeIndex == -1) ?
    //             new ResponseEntity<>(postcoffee(coffee), HttpStatus.CREATED) :
    //             new ResponseEntity<>(coffee, HttpStatus.OK);
    // }
    @PutMapping("/{id}")
    ResponseEntity<Coffee> putCoffee(@PathVariable String id, @RequestBody Coffee coffee){
        return (!coffeeRepository.existsById(id))
                ? new ResponseEntity<>(coffeeRepository.save(coffee), HttpStatus.CREATED)
                : new ResponseEntity<>(coffeeRepository.save(coffee), HttpStatus.OK);
    }


//    @DeleteMapping("/coffees/{id}")
    // @DeleteMapping("/{id}")
    // void deleteCoffee(@PathVariable String id){
    //     coffees.removeIf(c -> c.getId().equals(id));
    // }
    @DeleteMapping("/{id}")
    void deleteCoffee(@PathVariable String id){
        coffeeRepository.deleteById(id);
    }

}
