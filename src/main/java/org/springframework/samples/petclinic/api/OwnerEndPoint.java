package org.springframework.samples.petclinic.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.samples.petclinic.owner.OwnerRepository;
import org.springframework.samples.petclinic.owner.Pet;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;;
import java.util.Set;

@RestController
@AllArgsConstructor
public class OwnerEndPoint {

	private final OwnerRepository ownerRepository;

	@GetMapping("api/owner/{id}")
	public ResponseEntity<?> getByID(@PathVariable(name = "id") int id) {
		final Owner target = this.ownerRepository.findById(id);
		if (target == null) {
			return new ResponseEntity<>("Owner with ID '" + id + "' is not found", HttpStatus.NOT_FOUND);
		}

		return ResponseEntity.ok(target);
	}

	@GetMapping("api/owner/find")
	public ResponseEntity<?> findByLastName(@RequestParam(name = "lastname", defaultValue = "") String lastName) {
		final Collection<Owner> target = this.ownerRepository.findByLastName(lastName);
		if (target.isEmpty()) {
			return new ResponseEntity<>("Owner with last name like '" + lastName + "' is not found",
					HttpStatus.NOT_FOUND);
		}

		return ResponseEntity.ok(target);
	}

	@PostMapping("api/owner")
	public ResponseEntity<?> save(@RequestBody CreateOwnerRequest request) {
		Owner newOwner = new Owner();
		newOwner.setFirstName(request.getFirstName());
		newOwner.setLastName(request.getLastName());
		newOwner.setAddress(request.getAddress());
		newOwner.setCity(request.getCity());
		newOwner.setTelephone(request.getTelephone());
		this.ownerRepository.save(newOwner);

		return new ResponseEntity("Create Owner successfully", HttpStatus.CREATED);
	}

	@PutMapping("api/owner/{id}")
	public ResponseEntity<?> save(@PathVariable(name = "id") int id, @RequestBody CreateOwnerRequest request) {
		Owner target = this.ownerRepository.findById(id);
		if (target == null) {
			return new ResponseEntity<>("Owner with ID '" + id + "' is not found", HttpStatus.NOT_FOUND);
		}

		target.setFirstName(request.getFirstName() != null ? request.getFirstName() : target.getFirstName());
		target.setLastName(request.getLastName() != null ? request.getLastName() : target.getLastName());
		target.setAddress(request.getAddress() != null ? request.getAddress() : target.getAddress());
		target.setTelephone(request.getTelephone() != null ? request.getTelephone() : target.getTelephone());
		target.setCity(request.getCity() != null ? request.getCity() : target.getCity());

		this.ownerRepository.save(target);

		return ResponseEntity.ok("Update Owner successfully");
	}

	@AllArgsConstructor
	@Getter
	static private class CreateOwnerRequest {

		private final String firstName;

		private final String lastName;

		private final String address;

		private final String city;

		private final String telephone;

		private final Set<Pet> pets;

	}

	@AllArgsConstructor
	@Getter
	static private class UpdateOwnerRequest {

		private String firstName = null;

		private String lastName = null;

		private String address = null;

		private String city = null;

		private String telephone = null;

		private Set<Pet> pets = null;

	}

}
