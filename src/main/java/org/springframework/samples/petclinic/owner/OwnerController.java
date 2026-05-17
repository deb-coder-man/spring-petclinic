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

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.validation.Valid;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Spring MVC controller for managing {@link Owner} entities.
 *
 * Handles HTTP requests for creating, updating, finding, and displaying owners.
 * All routes are prefixed with {@code /owners}.
 *
 * @author Juergen Hoeller
 * @author Ken Krebs
 * @author Arjen Poutsma
 * @author Michael Isvy
 * @author Wick Dynex
 */
@Controller
class OwnerController {

	private static final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "owners/createOrUpdateOwnerForm";

	private final OwnerRepository owners;

	/**
	 * Constructs an {@code OwnerController} with the given owner repository.
	 * @param owners the repository used to persist and query {@link Owner} data
	 */
	public OwnerController(OwnerRepository owners) {
		this.owners = owners;
	}

	/**
	 * Prevents binding of the {@code id} and nested {@code id} fields from web requests,
	 * guarding against mass-assignment of internal identifiers.
	 * @param dataBinder the binder to configure
	 */
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id", "*.id");
	}

	/**
	 * Resolves the {@link Owner} model attribute for request handlers that include an
	 * {@code ownerId} path variable. Returns a new, empty {@link Owner} when no ID is
	 * present (e.g. on the creation form).
	 * @param ownerId the owner's primary key, or {@code null} if not present in the path
	 * @return the existing {@link Owner} for the given ID, or a new {@link Owner} instance
	 * @throws IllegalArgumentException if {@code ownerId} is provided but no matching owner exists
	 */
	@ModelAttribute("owner")
	public Owner findOwner(@PathVariable(name = "ownerId", required = false) Integer ownerId) {
		return ownerId == null ? new Owner()
				: this.owners.findById(ownerId)
					.orElseThrow(() -> new IllegalArgumentException("Owner not found with id: " + ownerId
							+ ". Please ensure the ID is correct " + "and the owner exists in the database."));
	}

	/**
	 * Displays the owner creation form.
	 * @return the logical view name for the create/update owner form
	 */
	@GetMapping("/owners/new")
	public String initCreationForm() {
		return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
	}

	/**
	 * Processes the owner creation form submission. Saves the new owner and redirects to
	 * their detail page on success, or redisplays the form with an error message on
	 * validation failure.
	 * @param owner the owner to create, populated and validated from the form
	 * @param result holds any validation errors
	 * @param redirectAttributes used to pass flash messages to the redirect target
	 * @return a redirect to the new owner's detail page, or the form view if there are errors
	 */
	@PostMapping("/owners/new")
	public String processCreationForm(@Valid Owner owner, BindingResult result, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("error", "There was an error in creating the owner.");
			return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
		}

		this.owners.save(owner);
		redirectAttributes.addFlashAttribute("message", "New Owner Created");
		return "redirect:/owners/" + owner.getId();
	}

	/**
	 * Displays the owner search form.
	 * @return the logical view name for the find-owners page
	 */
	@GetMapping("/owners/find")
	public String initFindForm() {
		return "owners/findOwners";
	}

	/**
	 * Searches for owners by last name with pagination. A missing or blank last name
	 * returns all owners. Redirects directly to the owner detail page when exactly one
	 * match is found; otherwise renders a paginated list.
	 * @param page the 1-based page number to display (defaults to {@code 1})
	 * @param owner an {@link Owner} whose {@code lastName} field drives the search
	 * @param result holds any field-level errors (e.g. when no owners are found)
	 * @param model the Spring MVC model populated for the list view
	 * @return a redirect to the single matching owner, the list view, or the find form
	 */
	@GetMapping("/owners")
	public String processFindForm(@RequestParam(defaultValue = "1") int page, Owner owner, BindingResult result,
			Model model) {
		// allow parameterless GET request for /owners to return all records
		String lastName = owner.getLastName();
		if (lastName == null) {
			lastName = ""; // empty string signifies broadest possible search
		}

		// find owners by last name
		Page<Owner> ownersResults = findPaginatedForOwnersLastName(page, lastName);
		if (ownersResults.isEmpty()) {
			// no owners found
			result.rejectValue("lastName", "notFound", "not found");
			return "owners/findOwners";
		}

		if (ownersResults.getTotalElements() == 1) {
			// 1 owner found
			owner = ownersResults.iterator().next();
			return "redirect:/owners/" + owner.getId();
		}

		// multiple owners found
		return addPaginationModel(page, model, ownersResults);
	}

	/**
	 * Populates the model with pagination metadata and the current page of owners, then
	 * returns the owners list view.
	 * @param page the current 1-based page number
	 * @param model the Spring MVC model to populate
	 * @param paginated the paginated query result
	 * @return the logical view name for the owners list
	 */
	private String addPaginationModel(int page, Model model, Page<Owner> paginated) {
		List<Owner> listOwners = paginated.getContent();
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", paginated.getTotalPages());
		model.addAttribute("totalItems", paginated.getTotalElements());
		model.addAttribute("listOwners", listOwners);
		return "owners/ownersList";
	}

	/**
	 * Queries the repository for a page of owners whose last name starts with the given
	 * string. Page size is fixed at 5 records.
	 * @param page the 1-based page number
	 * @param lastname the last-name prefix to filter by (empty string matches all)
	 * @return a {@link Page} of matching {@link Owner} records
	 */
	private Page<Owner> findPaginatedForOwnersLastName(int page, String lastname) {
		int pageSize = 5;
		Pageable pageable = PageRequest.of(page - 1, pageSize);
		return owners.findByLastNameStartingWith(lastname, pageable);
	}

	/**
	 * Displays the owner update form, pre-populated via the {@link #findOwner} model
	 * attribute.
	 * @return the logical view name for the create/update owner form
	 */
	@GetMapping("/owners/{ownerId}/edit")
	public String initUpdateOwnerForm() {
		return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
	}

	/**
	 * Processes the owner update form submission. Verifies that the form's owner ID
	 * matches the path variable before saving. Redirects to the owner's detail page on
	 * success, or redisplays the form with an error message on validation or ID-mismatch
	 * failure.
	 * @param owner the updated owner data, populated and validated from the form
	 * @param result holds any validation errors
	 * @param ownerId the owner's primary key from the URL path
	 * @param redirectAttributes used to pass flash messages to the redirect target
	 * @return a redirect to the owner's detail page, or the form view if there are errors
	 */
	@PostMapping("/owners/{ownerId}/edit")
	public String processUpdateOwnerForm(@Valid Owner owner, BindingResult result, @PathVariable("ownerId") int ownerId,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("error", "There was an error in updating the owner.");
			return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
		}

		if (!Objects.equals(owner.getId(), ownerId)) {
			result.rejectValue("id", "mismatch", "The owner ID in the form does not match the URL.");
			redirectAttributes.addFlashAttribute("error", "Owner ID mismatch. Please try again.");
			return "redirect:/owners/{ownerId}/edit";
		}

		owner.setId(ownerId);
		this.owners.save(owner);
		redirectAttributes.addFlashAttribute("message", "Owner Values Updated");
		return "redirect:/owners/{ownerId}";
	}

	/**
	 * Displays the detail page for a single owner.
	 * @param ownerId the ID of the owner to display
	 * @return a {@link ModelAndView} containing the owner object for the detail view
	 * @throws IllegalArgumentException if no owner exists with the given ID
	 */
	@GetMapping("/owners/{ownerId}")
	public ModelAndView showOwner(@PathVariable("ownerId") int ownerId) {
		ModelAndView mav = new ModelAndView("owners/ownerDetails");
		Optional<Owner> optionalOwner = this.owners.findById(ownerId);
		Owner owner = optionalOwner.orElseThrow(() -> new IllegalArgumentException(
				"Owner not found with id: " + ownerId + ". Please ensure the ID is correct "));
		mav.addObject(owner);
		return mav;
	}

}
