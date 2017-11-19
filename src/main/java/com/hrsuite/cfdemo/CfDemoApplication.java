package com.hrsuite.cfdemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.stream.Stream;

@SpringBootApplication
public class CfDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CfDemoApplication.class, args);
	}
}


@RestController
class Greetoings{

	@GetMapping("/hi")
	String hi(){
		return "Hello, Cloud Foundry from Windows";
	}
}


@Component
class SampleDataCLR implements CommandLineRunner{

    private final CarRepository carRepository;

    public SampleDataCLR(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Stream.of("Mazda", "Toyota", "Nissan")
        .forEach(name -> carRepository.save(new Car(name)));

        carRepository.findAll().forEach(System.out::println);

    }
}



@Repository
interface CarRepository extends JpaRepository<Car, Long>{

}



@Entity
class Car{

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    public Car() { //for JPA
    }

    public Car(String name) {
        this.name = name;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}