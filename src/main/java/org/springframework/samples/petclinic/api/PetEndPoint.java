package org.springframework.samples.petclinic.api;

import lombok.AllArgsConstructor;
import org.springframework.samples.petclinic.owner.PetRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class PetEndPoint {

	private final PetRepository petRepository;


}
