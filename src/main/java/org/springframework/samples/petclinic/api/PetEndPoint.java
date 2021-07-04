package org.springframework.samples.petclinic.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.owner.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@AllArgsConstructor
public class PetEndPoint {

	private final PetRepository petRepository;

	private final OwnerRepository ownerRepository;

	@GetMapping("api/pet/pet-type")
	public ResponseEntity<?> getPetType() {
		return ResponseEntity.ok(this.petRepository.findPetTypes());
	}

	@GetMapping("api/pet/{id}")
	public ResponseEntity<?> getByID(@PathVariable(name = "id") int id) {
		final Pet target = this.petRepository.findById(id);
		if (target == null) {
			return new ResponseEntity<>("Pet with ID '" + Integer.toString(id) + "' is not found",
					HttpStatus.NOT_FOUND);
		}

		return ResponseEntity.ok(target);
	}

	@PostMapping("api/pet")
	public ResponseEntity<?> save(@RequestBody CreatePetRequest request) {
		Pet newPet = new Pet();
		newPet.setName(request.getName());
		newPet.setBirthDate(request.getBirth_date());

		final PetType petType = this.petRepository.findPetTypeByID(request.getType_id());
		if (petType == null) {
			return new ResponseEntity<>(
					"Pet type with id '" + Integer.toString(request.getType_id()) + "' is not found",
					HttpStatus.NOT_FOUND);
		}
		else {
			newPet.setType(petType);
		}

		// final Owner owner = this.ownerRepository.findById(request.getOwner_id());
		// if(owner == null) {
		// return new ResponseEntity<>(
		// "Owner with id '" + Integer.toString(request.getOwner_id()) + "' is not found",
		// HttpStatus.NOT_FOUND
		// );
		// }else{
		// newPet.setOwner()
		// }

		this.petRepository.save(newPet);
		return ResponseEntity.ok("Create pet successfully");
	}

	@AllArgsConstructor
	@Getter
	static private class CreatePetRequest {

		private final String name;

		private final LocalDate birth_date;

		private final int type_id;

		private final int owner_id;

	}

	@AllArgsConstructor
	@Getter
	static private class UpdatePetRequest {

		private String name = null;

		private LocalDate birth_date = null;

		private int type_id = -1;

		private int owner_id = -1;

	}

}
