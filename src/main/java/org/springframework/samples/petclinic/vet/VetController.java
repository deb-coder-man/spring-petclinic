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
package org.springframework.samples.petclinic.vet;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Spring MVC controller for displaying {@link Vet} information.
 *
 * Exposes two endpoints: a paginated HTML list at {@code /vets.html} and a JSON resource
 * at {@code /vets}.
 *
 * @author Juergen Hoeller
 * @author Mark Fisher
 * @author Ken Krebs
 * @author Arjen Poutsma
 */
@Controller
class VetController {

	private final VetRepository vetRepository;

	/**
	 * Constructs a {@code VetController} with the given vet repository.
	 * @param vetRepository the repository used to query {@link Vet} data
	 */
	public VetController(VetRepository vetRepository) {
		this.vetRepository = vetRepository;
	}

	/**
	 * Renders the paginated vet list HTML page.
	 * @param page the 1-based page number to display (defaults to {@code 1})
	 * @param model the Spring MVC model populated for the view
	 * @return the logical view name for the vet list page
	 */
	@GetMapping("/vets.html")
	public String showVetList(@RequestParam(defaultValue = "1") int page, Model model) {
		Page<Vet> paginated = findPaginated(page);
		return addPaginationModel(page, paginated, model);
	}

	/**
	 * Populates the model with pagination metadata and the current page of vets, then
	 * returns the vet list view.
	 * @param page the current 1-based page number
	 * @param paginated the paginated query result
	 * @param model the Spring MVC model to populate
	 * @return the logical view name for the vet list
	 */
	private String addPaginationModel(int page, Page<Vet> paginated, Model model) {
		List<Vet> listVets = paginated.getContent();
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", paginated.getTotalPages());
		model.addAttribute("totalItems", paginated.getTotalElements());
		model.addAttribute("listVets", listVets);
		return "vets/vetList";
	}

	/**
	 * Queries the repository for a single page of vets. Page size is fixed at 5 records.
	 * @param page the 1-based page number
	 * @return a {@link Page} of {@link Vet} records
	 */
	private Page<Vet> findPaginated(int page) {
		int pageSize = 5;
		Pageable pageable = PageRequest.of(page - 1, pageSize);
		return vetRepository.findAll(pageable);
	}

	/**
	 * Returns all vets as a JSON resource. The {@link Vets} wrapper type is used instead
	 * of a plain collection to simplify JSON/XML marshalling.
	 * @return a {@link Vets} object containing the full list of vets
	 */
	@GetMapping({ "/vets" })
	public @ResponseBody Vets showResourcesVetList() {
		// Here we are returning an object of type 'Vets' rather than a collection of Vet
		// objects so it is simpler for JSon/Object mapping
		Vets vets = new Vets();
		vets.getVetList().addAll(this.vetRepository.findAll());
		return vets;
	}

}
