# Prioritized Chain of Effects

This artifact follows the SusAF analysis step from the course slides: prioritize effects with high likelihood and high impact, then connect them into first-order, second-order, and third-order chains. The effect orders follow the slide definitions:

- First order / immediate: direct effects from use of the system.
- Second order / enabling: effects that arise from system operation over time.
- Third order / structural: long-term organizational or social changes caused by repeated adoption.

Likelihood scale: `Very likely`, `Likely`, `Possible`, `Unlikely`.

Impact scale: `High`, `Medium`, `Low`.

## Prioritized Effects

| ID | Related functionality | Effect | Polarity | Dimension(s) | Likelihood | Impact | Order |
|---|---|---|---|---|---|---|---|
| E1 | Search owners by last-name prefix | Staff can find owner records faster. | Positive | Individual / Economic | Very likely | High | First |
| E2 | Search owners by last-name prefix | Faster lookup improves client service responsiveness. | Positive | Social / Economic | Very likely | High | Second |
| E3 | List all owners when search is empty | Broad searches can load unnecessary records. | Negative | Technical / Environmental | Likely | High | First |
| E4 | Paginate owner search results | Pagination limits data returned and rendered per request. | Positive | Technical / Environmental | Very likely | High | First |
| E5 | Paginate owner search results | Lower per-request workload improves performance under routine use. | Positive | Technical / Environmental | Likely | High | Second |
| E6 | Create a new owner record | Digital owner records reduce paper-based administration. | Positive | Environmental / Economic | Very likely | High | First |
| E7 | Create a new owner record | Incorrect or duplicate owner records create rework and reduce record trust. | Negative | Technical / Social / Economic | Possible | High | Second |
| E8 | Validate owner details | Required fields and phone validation improve data quality. | Positive | Technical | Very likely | High | First |
| E9 | Validate owner details | Reliable contact data improves follow-up communication. | Positive | Social / Economic | Very likely | High | Second |
| E10 | Validate owner details | Rigid 10-digit phone validation may exclude valid non-standard phone formats. | Negative | Social / Individual | Possible | Medium | First |
| E11 | View owner details | Staff can quickly access owner contact information. | Positive | Individual / Economic | Very likely | High | First |
| E12 | View owner details | On-screen personal information increases privacy exposure risk. | Negative | Individual / Technical | Likely | High | First |
| E13 | Add a new pet to an owner | Structured pet records support continuity of care. | Positive | Individual / Social | Very likely | High | First |
| E14 | Select pet type from catalogue | Standardized pet types improve consistency and reporting. | Positive | Technical / Economic | Very likely | Medium | First |
| E15 | Prevent duplicate pet names | Reduced ambiguity lowers the chance of staff selecting the wrong pet record. | Positive | Technical / Individual | Likely | High | First |
| E16 | Prevent future pet birth dates | Date validation protects data integrity. | Positive | Technical | Very likely | Medium | First |
| E17 | Edit existing pet details | Correctable pet records keep clinical information accurate over time. | Positive | Technical / Individual | Very likely | High | Second |
| E18 | View pets and visit history | Staff can see previous visits, supporting continuity of care. | Positive | Individual / Social | Very likely | High | First |
| E19 | View pets and visit history | Loading full visit history may expose sensitive history and increase data retrieval. | Negative | Individual / Technical / Environmental | Likely | High | First |
| E20 | Add / book a visit | Digital visit booking reduces verbal or paper booking errors. | Positive | Social / Economic | Very likely | High | First |
| E21 | Add / book a visit | Incomplete visit descriptions reduce care quality and create rework. | Negative | Individual / Technical / Economic | Possible | High | Second |
| E22 | View veterinarians and specialties | Specialty visibility helps users identify appropriate vets. | Positive | Individual / Social | Very likely | High | First |
| E23 | View veterinarians and specialties | Stale vet specialty data can mislead users and reduce trust. | Negative | Social / Technical | Possible | High | Second |
| E24 | Multiple data-entry and search workflows | More stored records and repeated queries increase database maintenance and resource use over time. | Negative | Technical / Environmental | Likely | High | Second |
| E25 | Reliable digital records across owner, pet, and visit workflows | The clinic becomes more dependent on the system for day-to-day operations. | Mixed | Technical / Economic / Social | Likely | High | Third |

This gives 25 effects, exceeding the assignment minimum of 15.

## Chain 1: Owner Search, Responsiveness, And Resource Use

| Step | Effect ID | Chain relationship |
|---:|---|---|
| 1 | E1 | Searching by last-name prefix lets staff locate owner records faster. |
| 2 | E2 | Faster lookup improves service responsiveness for clients. |
| 3 | E3 | Broad or empty searches can still load unnecessary records. |
| 4 | E4 | Pagination limits each result page to a smaller amount of data. |
| 5 | E5 | Lower per-request workload improves performance and reduces avoidable resource use. |
| 6 | E24 | Over long-term use, repeated searches and growing records increase database maintenance and energy/resource demand. |

Summary: Owner search creates a positive chain of operational efficiency, but broad searching and long-term data growth create technical and environmental pressure. Pagination mitigates part of this risk by reducing unnecessary data transfer and rendering.

## Chain 2: Owner Records, Data Quality, And Trust

| Step | Effect ID | Chain relationship |
|---:|---|---|
| 1 | E6 | Creating digital owner records reduces paper-based administration. |
| 2 | E8 | Validation improves the completeness and consistency of owner data. |
| 3 | E9 | Better contact data improves follow-up communication with clients. |
| 4 | E11 | Fast access to contact details improves staff workflow and client service. |
| 5 | E12 | The same accessibility increases privacy exposure risk. |
| 6 | E7 | Incorrect or duplicate records create rework and reduce trust in the system. |
| 7 | E25 | As digital records become central to the clinic, operational dependence on system correctness increases. |

Summary: Digital owner records support environmental, economic, and social sustainability by reducing paper and improving communication. The main threat is that the clinic becomes dependent on accurate, privacy-protected records.

## Chain 3: Pet Records And Continuity Of Care

| Step | Effect ID | Chain relationship |
|---:|---|---|
| 1 | E13 | Adding pets creates structured pet records linked to an owner. |
| 2 | E14 | Catalogue-based pet types improve consistency. |
| 3 | E15 | Duplicate-name prevention reduces ambiguity in pet selection. |
| 4 | E16 | Birth-date validation protects data integrity. |
| 5 | E17 | Editable pet details allow records to remain accurate over time. |
| 6 | E18 | Accurate pet records and visit history support continuity of care. |

Summary: Pet-record features build a strong technical-to-individual chain. Better data integrity improves staff decision-making and therefore supports better care outcomes.

## Chain 4: Visit Booking, Clinical History, And Care Quality

| Step | Effect ID | Chain relationship |
|---:|---|---|
| 1 | E20 | Digital visit booking reduces paper or verbal booking errors. |
| 2 | E18 | Visit history gives staff visibility of previous treatment interactions. |
| 3 | E21 | Incomplete visit descriptions can weaken care quality and create rework. |
| 4 | E19 | Full visit-history display can increase privacy exposure and retrieval workload. |
| 5 | E25 | Repeated operational use makes the clinic more dependent on the system's availability and correctness. |

Summary: Visit booking and visit history support economic, social, and individual sustainability by improving coordination and continuity of care. The risks are privacy exposure, incomplete clinical notes, and operational dependence.

## Chain 5: Vet Visibility And Client Trust

| Step | Effect ID | Chain relationship |
|---:|---|---|
| 1 | E22 | Showing veterinarians and specialties helps clients or staff identify suitable expertise. |
| 2 | E23 | If specialty data becomes stale, users may make poor booking decisions. |
| 3 | E2 | Better service matching can improve client responsiveness and trust when data is correct. |
| 4 | E25 | Over time, users rely on the application as the authoritative source for clinic capability. |

Summary: Vet specialty visibility improves service matching and trust, but only if the data remains accurate and maintained.

## Highest Priority Effects For SusAD

Use these as the clearest effects for the Sustainability Awareness Diagram:

| Priority | Effect ID | Reason |
|---:|---|---|
| 1 | E18 | Directly links technical records to individual care quality and social trust. |
| 2 | E12 | High-likelihood privacy risk, clearly linked to individual and technical sustainability. |
| 3 | E8 | Data quality is a root cause for several positive chains. |
| 4 | E5 | Connects performance, resource efficiency, and environmental sustainability. |
| 5 | E6 | Clear paper-reduction and administrative-efficiency benefit. |
| 6 | E24 | Captures long-term technical and environmental cost of growing digital records. |
| 7 | E25 | Strong third-order effect: operational dependence on the system. |
| 8 | E21 | Clinical-note quality can affect individual care and economic rework. |
| 9 | E23 | Stale vet data affects social trust and technical maintainability. |
| 10 | E15 | Reduces ambiguity and protects care quality through better record selection. |

## Slide References

- The five dimensions follow `1.1.sustainabilityDimentions.pdf`: individual, social, environmental, economic, and technical.
- The effect prioritization follows `8.1.sustainabilityAwarenessFramework.pdf`, which places effects in a likelihood and impact matrix and prioritizes high-impact, likely effects.
- The chain structure follows `8.1.sustainabilityAwarenessFramework.pdf`, which defines immediate, enabling, and structural effects and asks how effects cause other effects.
- The technical and environmental reasoning uses `8.2.SustainableSoftwareEngineering.pdf` and `9.1.greenCoding.pdf`, especially resource efficiency, energy efficiency, database optimization, latency reduction, and maintainability.
- The performance/resource reasoning uses `9.2.PerfromanceTesting.pdf`, especially responsiveness, scalability, bottlenecks, and resource usage under workload.
