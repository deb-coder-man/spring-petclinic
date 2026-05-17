# Sustainability Awareness Diagram

This diagram is based on the prioritized effects in `MD/prioritized-chain-of-effects.md`. It follows the Sustainability Awareness Framework structure from the course slides: effects are grouped by sustainability dimension and by order of effect.

Legend:

- `+` positive effect
- `-` negative effect
- `~` mixed effect
- First order: immediate effect from system use
- Second order: enabling effect over continued use
- Third order: structural long-term effect

## SusAD Matrix

| Dimension | First-order effects | Second-order effects | Third-order effects |
|---|---|---|---|
| Individual | `+ E1` Staff find owner records faster. <br>`- E10` Rigid phone validation may exclude valid phone formats. <br>`+ E11` Staff quickly access owner contact information. <br>`- E12` Personal information is exposed on-screen. <br>`+ E13` Structured pet records support continuity of care. <br>`+ E15` Duplicate-name prevention reduces wrong-pet selection. <br>`+ E18` Visit history supports continuity of care. <br>`+ E22` Vet specialties help users identify suitable vets. | `+ E17` Correctable pet records keep clinical information accurate. <br>`- E21` Incomplete visit descriptions reduce care quality and create rework. | `~ E25` Clinic becomes dependent on the system for day-to-day operations. |
| Social | `+ E13` Structured pet records support continuity of care. <br>`+ E18` Visit history supports continuity of care. <br>`+ E20` Digital visit booking reduces verbal or paper booking errors. <br>`+ E22` Vet specialties help users identify suitable vets. | `+ E2` Faster lookup improves client service responsiveness. <br>`- E7` Incorrect or duplicate records reduce record trust. <br>`+ E9` Reliable contact data improves follow-up communication. <br>`- E23` Stale vet specialty data misleads users and reduces trust. | `~ E25` Users rely on the system as an authoritative source for clinic operations. |
| Environmental | `- E3` Broad searches can load unnecessary records. <br>`+ E4` Pagination limits data returned and rendered per request. <br>`+ E6` Digital records reduce paper-based administration. <br>`- E19` Full visit history can increase data retrieval workload. | `+ E5` Lower per-request workload improves performance and resource efficiency. <br>`- E24` Growing records and repeated queries increase maintenance and resource use. | |
| Economic | `+ E1` Faster record lookup saves staff time. <br>`+ E6` Digital records reduce administrative cost. <br>`+ E11` Fast contact access improves staff workflow. <br>`+ E14` Standardized pet types improve reporting. <br>`+ E20` Digital visit booking reduces booking errors. | `+ E2` Better responsiveness improves client service. <br>`- E7` Duplicate records create rework. <br>`+ E9` Reliable contact data improves follow-up. <br>`- E21` Incomplete visit notes create rework. | `~ E25` Operational dependence creates value but also business continuity risk. |
| Technical | `- E3` Broad searches can load unnecessary records. <br>`+ E4` Pagination limits per-request data. <br>`+ E8` Validation improves data quality. <br>`- E12` Privacy exposure risk must be managed technically. <br>`+ E14` Catalogue-based types improve consistency. <br>`+ E15` Duplicate-name prevention reduces ambiguity. <br>`+ E16` Birth-date validation protects data integrity. <br>`- E19` Visit history display increases retrieval workload. | `+ E5` Lower request workload improves performance. <br>`- E7` Duplicate records reduce data trust. <br>`+ E17` Editable pet details preserve accuracy. <br>`- E21` Incomplete descriptions reduce record usefulness. <br>`- E23` Stale vet data reduces trust and maintainability. <br>`- E24` Growing records increase maintenance and resource use. | `~ E25` System correctness, availability, and maintainability become structurally important. |

## Mermaid Diagram

Paste this Mermaid block into a Mermaid renderer, GitHub Markdown preview, or a diagramming tool that supports Mermaid.

```mermaid
flowchart LR
  classDef positive fill:#d9f7df,stroke:#1b7f3a,color:#0b3d1a,stroke-width:1px;
  classDef negative fill:#ffe0df,stroke:#b42318,color:#5f160f,stroke-width:1px;
  classDef mixed fill:#fff2cc,stroke:#9a6700,color:#4d3300,stroke-width:1px;

  subgraph F["First-order effects: immediate"]
    direction TB
    E1["+ E1 Staff find owner records faster<br/>Individual / Economic"]
    E3["- E3 Broad searches load unnecessary records<br/>Technical / Environmental"]
    E4["+ E4 Pagination limits data per request<br/>Technical / Environmental"]
    E6["+ E6 Digital records reduce paper administration<br/>Environmental / Economic"]
    E8["+ E8 Validation improves data quality<br/>Technical"]
    E10["- E10 Rigid phone validation may exclude users<br/>Social / Individual"]
    E11["+ E11 Staff quickly access contact details<br/>Individual / Economic"]
    E12["- E12 Personal information privacy exposure<br/>Individual / Technical"]
    E13["+ E13 Structured pet records support care<br/>Individual / Social"]
    E14["+ E14 Pet type catalogue improves consistency<br/>Technical / Economic"]
    E15["+ E15 Duplicate-name prevention reduces ambiguity<br/>Technical / Individual"]
    E16["+ E16 Birth-date validation protects integrity<br/>Technical"]
    E18["+ E18 Visit history supports continuity of care<br/>Individual / Social"]
    E19["- E19 Full visit history increases privacy and retrieval load<br/>Individual / Technical / Environmental"]
    E20["+ E20 Digital visit booking reduces booking errors<br/>Social / Economic"]
    E22["+ E22 Vet specialties support service matching<br/>Individual / Social"]
  end

  subgraph S["Second-order effects: enabling"]
    direction TB
    E2["+ E2 Faster lookup improves client responsiveness<br/>Social / Economic"]
    E5["+ E5 Lower workload improves performance and resource efficiency<br/>Technical / Environmental"]
    E7["- E7 Duplicate records create rework and reduce trust<br/>Technical / Social / Economic"]
    E9["+ E9 Reliable contact data improves follow-up<br/>Social / Economic"]
    E17["+ E17 Correctable pet records stay accurate over time<br/>Technical / Individual"]
    E21["- E21 Incomplete visit descriptions reduce care quality<br/>Individual / Technical / Economic"]
    E23["- E23 Stale vet data misleads users<br/>Social / Technical"]
    E24["- E24 Growing records and repeated queries increase resource use<br/>Technical / Environmental"]
  end

  subgraph T["Third-order effects: structural"]
    direction TB
    E25["~ E25 Clinic becomes dependent on system correctness and availability<br/>Technical / Economic / Social"]
  end

  E1 --> E2
  E3 --> E24
  E4 --> E5
  E5 --> E24

  E6 --> E8
  E8 --> E9
  E9 --> E11
  E11 --> E12
  E7 --> E25
  E12 --> E25

  E13 --> E14
  E14 --> E15
  E15 --> E16
  E16 --> E17
  E17 --> E18

  E20 --> E18
  E18 --> E21
  E18 --> E19
  E19 --> E25
  E21 --> E25

  E22 --> E23
  E23 --> E25
  E2 --> E25
  E24 --> E25

  class E1,E2,E4,E5,E6,E8,E9,E11,E13,E14,E15,E16,E17,E18,E20,E22 positive;
  class E3,E7,E10,E12,E19,E21,E23,E24 negative;
  class E25 mixed;
```

## Report-Ready Summary

The diagram shows that Spring PetClinic's strongest positive sustainability chain starts with structured digital owner, pet, and visit records. These immediate technical improvements improve data quality, staff responsiveness, communication, and continuity of care. Over time, these effects support social trust and economic efficiency.

The strongest negative chain comes from the same digital dependence. Personal information and visit histories become easier to access, creating privacy risk. Growing records and repeated database queries also increase technical maintenance and resource use. The main third-order effect is that the clinic becomes structurally dependent on system correctness, availability, privacy protection, and maintainability.

The highest-priority mitigation actions are to preserve pagination, protect personal data, maintain vet and pet records accurately, improve validation without excluding legitimate users, and monitor database/query performance as records grow.
