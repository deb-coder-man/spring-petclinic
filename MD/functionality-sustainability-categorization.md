# Functionality Sustainability Categorization

This table categorizes each identified Spring PetClinic functionality using the five sustainability dimensions from the course slides and the SusAF capture step: identify positive and negative effects, classify them by sustainability dimension, and estimate likelihood.

## Slide Basis

- `1.1.sustainabilityDimentions.pdf`: defines the five sustainability dimensions: individual, social, environmental, economic, and technical.
- `8.1.sustainabilityAwarenessFramework.pdf`: SusAF capture asks teams to brainstorm positive and negative effects by sustainability dimension and place effects in a likelihood/impact matrix.
- `8.1.sustainabilityAwarenessFramework.pdf`: SusAF analysis distinguishes immediate, enabling, and structural effects and connects them through chains of effects.
- `8.2.SustainableSoftwareEngineering.pdf`: sustainable software engineering emphasizes energy efficiency, resource efficiency, carbon awareness, embodied carbon, and measurement.
- `9.1.greenCoding.pdf`: green coding emphasizes reducing complexity, minimizing resource use, optimizing database queries, reducing API latency, and improving maintainability.
- `9.2.PerfromanceTesting.pdf`: performance requirements affect responsiveness, scalability, reliability, bottleneck detection, and resource efficiency.

Likelihood scale used below: `Very likely`, `Likely`, `Possible`, `Unlikely`.

| # | Functionality | Positive effect, dimension, likelihood | Negative effect, dimension, likelihood |
|---:|---|---|---|
| 1 | Display home / welcome page | Helps users orient themselves quickly, improving usability and confidence. Dimension: Individual / Technical. Likelihood: Very likely. | Adds a small static asset and page-rendering cost for every new session. Dimension: Environmental. Likelihood: Possible. |
| 2 | Open the owner search page | Gives clinic staff a clear entry point for finding client records. Dimension: Individual / Technical. Likelihood: Very likely. | If the page becomes the main workflow bottleneck, staff may repeatedly reload or retry searches. Dimension: Technical / Environmental. Likelihood: Possible. |
| 3 | Search owners by last-name prefix | Reduces staff time spent locating owner records and improves service responsiveness. Dimension: Individual / Economic. Likelihood: Very likely. | Broad or repeated searches can increase database workload if traffic grows. Dimension: Technical / Environmental. Likelihood: Likely. |
| 4 | List all owners when search is empty | Allows staff to browse records without needing exact search input, supporting autonomy of use. Dimension: Individual / Technical. Likelihood: Likely. | Empty searches can return unnecessary data, increasing processing and page rendering work. Dimension: Environmental / Technical. Likelihood: Likely. |
| 5 | Paginate owner search results | Limits data shown per page, improving usability, response time, and resource efficiency. Dimension: Technical / Environmental. Likelihood: Very likely. | Staff may need extra clicks across pages, adding interaction time for large result sets. Dimension: Individual. Likelihood: Possible. |
| 6 | Create a new owner record | Digitizes client registration and reduces paper-based record keeping. Dimension: Environmental / Economic. Likelihood: Very likely. | Incorrect or duplicate owner creation can increase data-cleaning work and reduce record trust. Dimension: Technical / Social. Likelihood: Possible. |
| 7 | Validate owner details, including required fields and 10-digit phone number | Improves data quality, contact reliability, and trust in clinic records. Dimension: Technical / Social. Likelihood: Very likely. | Rigid validation may reject legitimate phone formats and reduce inclusiveness. Dimension: Social / Individual. Likelihood: Possible. |
| 8 | View owner details and contact information | Gives staff fast access to contact details, improving communication and service continuity. Dimension: Social / Economic. Likelihood: Very likely. | Exposes personal information on-screen, increasing privacy risk if access is not controlled. Dimension: Individual / Technical. Likelihood: Likely. |
| 9 | Edit an existing owner | Keeps records current and reduces operational mistakes caused by stale contact information. Dimension: Technical / Economic. Likelihood: Very likely. | Incorrect edits may corrupt owner records and harm trust in the system. Dimension: Technical / Social. Likelihood: Possible. |
| 10 | Add a new pet to an owner | Creates structured pet records that support continuity of care. Dimension: Individual / Social. Likelihood: Very likely. | More stored records increase database size and long-term maintenance responsibility. Dimension: Technical / Environmental. Likelihood: Likely. |
| 11 | Select pet type from a maintained pet-type catalogue | Standardizes pet classification, improving data consistency and reporting. Dimension: Technical / Economic. Likelihood: Very likely. | A limited catalogue may exclude uncommon pet types, reducing flexibility and inclusiveness. Dimension: Social / Technical. Likelihood: Possible. |
| 12 | Prevent duplicate pet names for the same owner | Reduces ambiguity in treatment records and avoids staff errors. Dimension: Technical / Individual. Likelihood: Likely. | Duplicate-name prevention may block valid cases where an owner has pets with the same name. Dimension: Individual / Social. Likelihood: Possible. |
| 13 | Prevent future birth dates for pets | Protects data integrity and prevents biologically impossible records. Dimension: Technical. Likelihood: Very likely. | Strict date validation may frustrate users when approximate or unknown dates are needed. Dimension: Individual. Likelihood: Possible. |
| 14 | Edit an existing pet's details | Allows pet records to evolve as details are corrected, supporting long-term data accuracy. Dimension: Technical / Individual. Likelihood: Very likely. | Incorrect pet edits may affect treatment history and clinical decision-making. Dimension: Individual / Technical. Likelihood: Possible. |
| 15 | View an owner's pets and their visit history | Supports continuity of care by showing pet details and previous visits in one place. Dimension: Individual / Social. Likelihood: Very likely. | Displaying full visit history can expose sensitive information and load more data than needed. Dimension: Individual / Environmental / Technical. Likelihood: Likely. |
| 16 | Add / book a visit for a pet | Improves appointment tracking and reduces paper or verbal booking errors. Dimension: Economic / Social. Likelihood: Very likely. | Incomplete or inaccurate visit descriptions can reduce care quality and create rework. Dimension: Individual / Technical. Likelihood: Possible. |
| 17 | View veterinarians and their specialties with pagination | Helps owners or staff identify appropriate vets, improving trust and service matching. Dimension: Social / Individual. Likelihood: Very likely. | If vet information is stale or incomplete, users may make poor booking decisions. Dimension: Social / Technical. Likelihood: Possible. |

## Notes For Report Use

- The likelihood ratings are reasoned estimates for a small clinic-management system, not measured results.
- For the SusAF diagram, prioritize effects that are both likely and meaningful, such as privacy exposure from owner details, reduced paper use from digital records, database workload from broad searches, continuity of care from visit history, and trust/data integrity effects from validation.
- The strongest cross-dimensional chains will probably connect technical data quality to individual care quality, social trust, and economic efficiency.
