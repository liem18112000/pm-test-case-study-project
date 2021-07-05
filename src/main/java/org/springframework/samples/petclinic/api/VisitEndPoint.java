package org.springframework.samples.petclinic.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.owner.PetRepository;
import org.springframework.samples.petclinic.visit.Visit;
import org.springframework.samples.petclinic.visit.VisitRepository;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
public class VisitEndPoint {

	private final VisitRepository visitRepository;
	private final PetRepository petRepository;

	@GetMapping("api/visit/petID/{petID}")
	public ResponseEntity<?> getByPetID(@PathVariable(name="petID") int petID) {
		final List<Visit> target = this.visitRepository.findByPetId(petID);
		if(target.isEmpty()) {
			return new ResponseEntity<>(
				"Visit with Pet ID '" + Integer.toString(petID) + "' is not found",
				HttpStatus.NOT_FOUND
			);
		}

		return ResponseEntity.ok(
			target
		);
	}

	@GetMapping("api/visit/{id}")
	public ResponseEntity<?> getByID(@PathVariable(name="id") int id) {
		final Visit target = this.visitRepository.findById(id);
		if(target == null) {
			return new ResponseEntity<>(
				"Visit with ID '" + Integer.toString(id) + "' is not found",
				HttpStatus.NOT_FOUND
			);
		}

		return ResponseEntity.ok(
			target
		);
	}

	@PostMapping("api/visit")
	public ResponseEntity<?> save(@RequestBody CreateVisitRequest request) {
		Visit newVisit = new Visit();
		newVisit.setDate(request.getDate());
		newVisit.setDescription(request.getDescription());

		if(this.petRepository.findById(request.getPetId()) == null) {
			return new ResponseEntity<>(
				"Pet with id '" + Integer.toString(request.getPetId()) + "' is not found",
				HttpStatus.NOT_FOUND
			);
		}else{
			newVisit.setPetId(request.getPetId());
		}

		this.visitRepository.save(newVisit);
		return ResponseEntity.ok("Create visit successfully");
	}

	@PutMapping("api/visit/{id}")
	public ResponseEntity<?> update(@RequestBody UpdateVisitRequest request, @PathVariable(name="id") int id) {
		Visit target = this.visitRepository.findById(id);

		if(target == null) {
			return new ResponseEntity<>(
				"Pet with id '" + Integer.toString(id) + "' is not found",
				HttpStatus.NOT_FOUND
			);
		}

		if(request.getPetId() != -1){
			if(this.petRepository.findById(request.getPetId()) == null) {
				return new ResponseEntity<>(
					"Pet with id '" + Integer.toString(request.getPetId()) + "' is not found",
					HttpStatus.NOT_FOUND
				);
			}else{
				target.setPetId(request.getPetId());
			}
		}

		target.setDate(request.getDate() != null ? request.getDate() : target.getDate());
		target.setDescription(request.getDescription() != null ? request.getDescription() : target.getDescription());

		this.visitRepository.save(target);
		return ResponseEntity.ok("Update visit successfully");
	}

	@AllArgsConstructor
	@Getter
	static private class CreateVisitRequest {

		@DateTimeFormat(pattern = "yyyy-MM-dd")
		private final LocalDate date;

		private final String description;

		private final Integer petId;
	}

	@AllArgsConstructor
	@Getter
	static private class UpdateVisitRequest {

		@DateTimeFormat(pattern = "yyyy-MM-dd")
		private LocalDate date = null;

		private String description = null;

		private Integer petId = -1;
	}
}
