# Spring PetClinic Functionalities

This list identifies 17 functionalities implemented in this Spring PetClinic repository.

| # | Functionality | Repo evidence |
|---:|---|---|
| 1 | Display home / welcome page | `src/main/java/org/springframework/samples/petclinic/system/WelcomeController.java` |
| 2 | Open the owner search page | `src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java` |
| 3 | Search owners by last-name prefix | `src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java` |
| 4 | List all owners when search is empty | `src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java` |
| 5 | Paginate owner search results | `src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java` |
| 6 | Create a new owner record | `src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java` |
| 7 | Validate owner details, including required fields and 10-digit phone number | `src/main/java/org/springframework/samples/petclinic/owner/Owner.java` |
| 8 | View owner details and contact information | `src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java` |
| 9 | Edit an existing owner | `src/main/java/org/springframework/samples/petclinic/owner/OwnerController.java` |
| 10 | Add a new pet to an owner | `src/main/java/org/springframework/samples/petclinic/owner/PetController.java` |
| 11 | Select pet type from a maintained pet-type catalogue | `src/main/java/org/springframework/samples/petclinic/owner/PetController.java` |
| 12 | Prevent duplicate pet names for the same owner | `src/main/java/org/springframework/samples/petclinic/owner/PetController.java` |
| 13 | Prevent future birth dates for pets | `src/main/java/org/springframework/samples/petclinic/owner/PetController.java` |
| 14 | Edit an existing pet's details | `src/main/java/org/springframework/samples/petclinic/owner/PetController.java` |
| 15 | View an owner's pets and their visit history | `src/main/resources/templates/owners/ownerDetails.html` |
| 16 | Add / book a visit for a pet | `src/main/java/org/springframework/samples/petclinic/owner/VisitController.java` |
| 17 | View veterinarians and their specialties with pagination | `src/main/java/org/springframework/samples/petclinic/vet/VetController.java` |

Optional replacement functionality: expose veterinarian data through the `/vets` JSON endpoint in `src/main/java/org/springframework/samples/petclinic/vet/VetController.java`.
