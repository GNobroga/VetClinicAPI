package com.veterinary.care.api;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.veterinary.care.api.application.enums.AddressType;
import com.veterinary.care.api.application.enums.PersonType;
import com.veterinary.care.api.domain.entities.AddressEntity;
import com.veterinary.care.api.domain.entities.PersonEntity;
import com.veterinary.care.api.domain.entities.UserEntity;
import com.veterinary.care.api.insfrastructure.repositories.PersonJpaRepository;

@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);

	}

	@Bean
	CommandLineRunner commandLineRunner(PersonJpaRepository repository) {
		return (String ...args) -> {
			var person = PersonEntity.builder()
				.name("Gabriel")
				.type(PersonType.CLIENT)
				.document("17364509720")
				.birthDate(LocalDate.parse("2000-04-10"))
				.phone("28999505410")
				.email("gabrielcardoso_stelo@hotmail.com");

			var user = UserEntity.builder()
				.username("gabriel123")
				.password("camilo123")
				.build();

			person.user(user);


			var personBuilded = person.build();

			var address = AddressEntity.builder()
				.place("Avenida")
				.number("95")
				.type(AddressType.WORK)
				.zipCode("999")
				.complement("Não definido")
				.person(personBuilded)
				.build();

			var addresses = new ArrayList<AddressEntity>();
			addresses.add(address);

			personBuilded.setAddresses(addresses);
			
			repository.save(personBuilded);
			repository.flush();
		};
	}

}
