package org.springframework.samples.petclinic.api;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.vet.VetRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class VetEndPoint {

	private final VetRepository vetRepository;

	@GetMapping("api/vet")
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(this.vetRepository.findAll());
	}

}
