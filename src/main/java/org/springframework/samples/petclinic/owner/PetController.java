/*
 * Copyright 2012-2025 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.owner;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Spring MVC controller for managing {@link Pet} entities belonging to an {@link Owner}.
 *
 * All routes are nested under {@code /owners/{ownerId}}, so every request handler
 * operates in the context of a specific owner.
 *
 * @author Juergen Hoeller
 * @author Ken Krebs
 * @author Arjen Poutsma
 * @author Wick Dynex
 */
@Controller
@RequestMapping("/owners/{ownerId}")
class PetController {

	private static final String VIEWS_PETS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm";

	private final OwnerRepository owners;

	private final PetTypeRepository types;

	/**
	 * Constructs a {@code PetController} with the given repositories.
	 * @param owners the repository used to load and persist {@link Owner} data
	 * @param types the repository used to populate the pet-type selection list
	 */
	public PetController(OwnerRepository owners, PetTypeRepository types) {
		this.owners = owners;
		this.types = types;
	}

	/**
	 * Provides all available {@link PetType} values for the {@code types} model attribute,
	 * used to populate the type drop-down on pet forms.
	 * @return a collection of all pet types
	 */
	@ModelAttribute("types")
	public Collection<PetType> populatePetTypes() {
		return this.types.findPetTypes();
	}

	/**
	 * Resolves the {@link Owner} model attribute from the {@code ownerId} path variable.
	 * @param ownerId the owner's primary key
	 * @return the matching {@link Owner}
	 * @throws IllegalArgumentException if no owner exists with the given ID
	 */
	@ModelAttribute("owner")
	public Owner findOwner(@PathVariable("ownerId") int ownerId) {
		Optional<Owner> optionalOwner = this.owners.findById(ownerId);
		Owner owner = optionalOwner.orElseThrow(() -> new IllegalArgumentException(
				"Owner not found with id: " + ownerId + ". Please ensure the ID is correct "));
		return owner;
	}

	/**
	 * Resolves the {@link Pet} model attribute. Returns a new, empty {@link Pet} when no
	 * {@code petId} is present in the path (e.g. on the creation form); otherwise looks up
	 * the pet on the owner.
	 * @param ownerId the owner's primary key
	 * @param petId the pet's primary key, or {@code null} if not present in the path
	 * @return the existing {@link Pet} for the given ID, or a new {@link Pet} instance
	 * @throws IllegalArgumentException if {@code ownerId} does not match an existing owner
	 */
	@ModelAttribute("pet")
	public Pet findPet(@PathVariable("ownerId") int ownerId,
			@PathVariable(name = "petId", required = false) Integer petId) {

		if (petId == null) {
			return new Pet();
		}

		Optional<Owner> optionalOwner = this.owners.findById(ownerId);
		Owner owner = optionalOwner.orElseThrow(() -> new IllegalArgumentException(
				"Owner not found with id: " + ownerId + ". Please ensure the ID is correct "));
		return owner.getPet(petId);
	}

	/**
	 * Prevents binding of {@code id} and nested {@code id} fields on the {@code owner}
	 * model object, guarding against mass-assignment of internal identifiers.
	 * @param dataBinder the binder to configure
	 */
	@InitBinder("owner")
	public void initOwnerBinder(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id", "*.id");
	}

	/**
	 * Registers the {@link PetValidator} and prevents binding of {@code id} fields on the
	 * {@code pet} model object.
	 * @param dataBinder the binder to configure
	 */
	@InitBinder("pet")
	public void initPetBinder(WebDataBinder dataBinder) {
		dataBinder.setValidator(new PetValidator());
		dataBinder.setDisallowedFields("id", "*.id");
	}

	/**
	 * Displays the pet creation form for the given owner.
	 * @return the logical view name for the create/update pet form
	 */
	@GetMapping("/pets/new")
	public String initCreationForm() {
		return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
	}

	/**
	 * Processes the pet creation form submission. Validates that the pet name is not a
	 * duplicate for this owner and that the birth date is not in the future. Adds the pet
	 * to the owner and saves on success; redisplays the form on validation failure.
	 * @param owner the owner to whom the new pet will be added
	 * @param pet the new pet, populated and validated from the form
	 * @param result holds any validation errors
	 * @param redirectAttributes used to pass a flash message to the redirect target
	 * @return a redirect to the owner's detail page, or the form view if there are errors
	 */
	@PostMapping("/pets/new")
	public String processCreationForm(Owner owner, @Valid Pet pet, BindingResult result,
			RedirectAttributes redirectAttributes) {

		if (StringUtils.hasText(pet.getName()) && pet.isNew() && owner.getPet(pet.getName(), true) != null) {
			result.rejectValue("name", "duplicate", "already exists");
		}

		LocalDate currentDate = LocalDate.now();
		if (pet.getBirthDate() != null && pet.getBirthDate().isAfter(currentDate)) {
			result.rejectValue("birthDate", "typeMismatch.birthDate");
		}

		if (result.hasErrors()) {
			return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
		}

		owner.addPet(pet);
		this.owners.save(owner);
		redirectAttributes.addFlashAttribute("message", "New Pet has been Added");
		return "redirect:/owners/{ownerId}";
	}

	/**
	 * Displays the pet update form, pre-populated via the {@link #findPet} model attribute.
	 * @return the logical view name for the create/update pet form
	 */
	@GetMapping("/pets/{petId}/edit")
	public String initUpdateForm() {
		return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
	}

	/**
	 * Processes the pet update form submission. Validates that the new name does not
	 * conflict with a different existing pet of the same owner, and that the birth date is
	 * not in the future. Delegates to {@link #updatePetDetails} on success; redisplays the
	 * form on validation failure.
	 * @param owner the owner of the pet being updated
	 * @param pet the pet with updated field values, populated and validated from the form
	 * @param result holds any validation errors
	 * @param redirectAttributes used to pass a flash message to the redirect target
	 * @return a redirect to the owner's detail page, or the form view if there are errors
	 */
	@PostMapping("/pets/{petId}/edit")
	public String processUpdateForm(Owner owner, @Valid Pet pet, BindingResult result,
			RedirectAttributes redirectAttributes) {

		String petName = pet.getName();

		// checking if the pet name already exists for the owner
		if (StringUtils.hasText(petName)) {
			Pet existingPet = owner.getPet(petName, false);
			if (existingPet != null && !Objects.equals(existingPet.getId(), pet.getId())) {
				result.rejectValue("name", "duplicate", "already exists");
			}
		}

		LocalDate currentDate = LocalDate.now();
		if (pet.getBirthDate() != null && pet.getBirthDate().isAfter(currentDate)) {
			result.rejectValue("birthDate", "typeMismatch.birthDate");
		}

		if (result.hasErrors()) {
			return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
		}

		updatePetDetails(owner, pet);
		redirectAttributes.addFlashAttribute("message", "Pet details has been edited");
		return "redirect:/owners/{ownerId}";
	}

	/**
	 * Applies updated field values to an existing pet on the owner, or adds the pet if it
	 * is not already associated. Persists the owner after the update.
	 * @param owner the owner whose pet list is being modified
	 * @param pet the pet carrying the updated name, birth date, and type
	 * @throws IllegalStateException if {@code pet.getId()} is {@code null}
	 */
	private void updatePetDetails(Owner owner, Pet pet) {
		Integer id = pet.getId();
		Assert.state(id != null, "'pet.getId()' must not be null");
		Pet existingPet = owner.getPet(id);
		if (existingPet != null) {
			// Update existing pet's properties
			existingPet.setName(pet.getName());
			existingPet.setBirthDate(pet.getBirthDate());
			existingPet.setType(pet.getType());
		}
		else {
			owner.addPet(pet);
		}
		this.owners.save(owner);
	}

}
