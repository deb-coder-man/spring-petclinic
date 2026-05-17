# SOFTENG 754 Sustainability Reference Notes

These notes summarize the course PDFs provided in `~/Downloads` so future assignment work can use the same terminology and framing.

Source PDFs:

- `1.1.sustainabilityDimentions.pdf`
- `8.1.sustainabilityAwarenessFramework.pdf`
- `8.2.SustainableSoftwareEngineering.pdf`
- `8.2.SonarQube.pdf`
- `9.1.greenCoding.pdf`
- `9.2.PerfromanceTesting.pdf`

## Sustainability Dimensions

Sustainability means meeting present needs without compromising future generations' ability to meet their own needs. In software engineering, sustainability should be considered both in how software is built and in what the software enables.

The five sustainability dimensions are:

| Dimension | Course framing | PetClinic analysis angle |
|---|---|---|
| Individual | Physical, mental, emotional wellbeing; education; privacy; safety; self-awareness; free will; skill development. | Effects on vets, clinic staff, pet owners, developers, and users' ability to act safely and confidently. |
| Social | Trust, equity, justice, collaboration, transparency, inclusiveness, communication, and community effects. | Effects on client-clinic relationships, fair access, communication quality, and trust in records. |
| Environmental | Long-term effects on ecosystems and resources; energy use; carbon emissions; waste; pollution; logistics. | Server use, database queries, unnecessary computation, paper reduction, travel reduction, and hardware longevity. |
| Economic | Financial sustainability, value creation, cost efficiency, profitability, stakeholder investment protection, market relevance. | Clinic productivity, reduced administrative cost, fewer errors, maintenance cost, and value of reliable records. |
| Technical | Longevity, adaptability, resilience, maintainability, security, data integrity, scalability, and resource management. | Code quality, testability, maintainability, extensibility, data correctness, and performance under workload. |

Useful sustainable software requirement themes:

- Resource efficiency: maximize useful work while minimizing energy and hardware use.
- Hardware longevity and compatibility: avoid forcing unnecessary upgrades.
- Autonomy of use: let users choose how they use software.
- Transparency: clear documentation, licensing, and terms.
- Continuity: software should remain maintainable and usable over time.
- Offline capability and uninstallability can be sustainability requirements where relevant.

Important warning: greenwashing is claiming sustainability benefits without evidence. Course framing prefers measurable sustainability claims over vague eco-friendly wording.

## Sustainability Awareness Framework

The Sustainability Awareness Framework (SusAF) raises awareness of potential sustainability effects of a software system in its intended context of use. It is designed to support stakeholder discussion during requirements engineering.

SusAF process:

1. Warm-up: introduce participants, the product or service, and known sustainability effects.
2. Capture: brainstorm possible effects across the five dimensions, including positive and negative effects.
3. Analysis: order effects into chains and identify causal relationships.
4. Synthesis: turn effects into opportunities, threats, and actions.

Capture guidance:

- Brainstorm broadly first; quantity matters before filtering.
- Classify effects by sustainability dimension.
- Separate positive and negative effects.
- Place effects in a likelihood and impact matrix.
- Prioritize high-impact and high-likelihood effects, but keep enough lower-impact effects for context.

Effect orders:

| Order | Meaning |
|---|---|
| First order / immediate | Direct effects from production, use, or disposal of the software system. |
| Second order / enabling | Effects arising from operation and use over time. |
| Third order / structural | Long-term societal, organizational, policy, or norm changes caused by widespread adoption. |

SusAD diagram guidance:

- Map effects by sustainability dimension and order of effect.
- Connect cause and effect relationships.
- Ask "How do we get to this effect?" and "What does this effect lead to?"
- Chains can cross dimensions and orders.
- The synthesis step should identify opportunities to maximize and threats to mitigate.

Dimension prompt examples from the course:

- Social: trust, sense of community, inclusion, equity, participation, and communication.
- Individual: health, learning, privacy, safety, self-awareness, and free will.
- Environmental: materials, waste, pollution, biodiversity, energy, and logistics.
- Economic: value, customer relationships, supply chain, governance, and innovation.
- Technical: maintainability, usability, adaptability, security, and scalability.

## Sustainable Software Engineering

Sustainable software engineering, also called green software, considers and minimizes environmental effects across the software lifecycle. The focus is reduction, not just offsetting.

Core green software principles:

| Principle | Meaning |
|---|---|
| Carbon efficiency | Maximize value per unit of carbon emitted. |
| Electricity efficiency | Maximize value per kWh consumed. |
| Carbon awareness | Run workloads when and where electricity has lower carbon intensity. |
| Embodied carbon | Account for carbon emitted during hardware creation and disposal. |
| Energy proportionality | Use hardware efficiently; higher utilization can be more efficient than idle capacity. |
| Climate commitments | Prefer actual reduction mechanisms over vague compensation claims. |
| Measurement | Use measurement to guide sustainability improvements. |

Measurement concepts:

- Scope 1: direct emissions from company-owned or controlled activities.
- Scope 2: indirect emissions from purchased electricity or heat.
- Scope 3: indirect supply-chain and related emissions.
- Software Carbon Intensity (SCI) is a rate, not just a total footprint.
- SCI considers operational emissions plus embodied carbon per functional unit.
- Functional units can be per user, per device, per API request, or another scaling unit.

For PetClinic, useful sustainability measurement proxies include request count, database calls, response time, resource use, code complexity, test execution time, and maintainability findings.

## SonarQube And Static Analysis

Static analysis tools support sustainable development by improving maintainability, quality, and security. They detect code smells, bugs, vulnerabilities, duplicated or unused code, unnecessary imports, and other coding issues.

Sonar suite:

- SonarQube Cloud: cloud-based analysis for CI/CD workflows.
- SonarQube Server: self-managed continuous codebase inspection.
- SonarQube IDE, formerly SonarLint: IDE extension for on-the-fly analysis.

For Assignment 5 Task 2, SonarQube evidence should support the coding criteria:

- Reduce code complexity, including cyclomatic or cognitive complexity.
- Minimize resource usage, including try-with-resources where relevant.
- Avoid redundant or unused code.
- Remove unnecessary imports and avoid wildcard imports.
- Improve documentation, especially for selected service/module classes.

Report angle:

- Explain the detected issue.
- Explain its negative sustainability impact.
- Explain the implemented change.
- Explain how the change improves sustainability.

## Green Coding

Green coding means writing software that uses fewer resources, less energy, and causes lower environmental impact during execution and maintenance. Green programming is broader and includes lifecycle practices.

Key green coding practices:

1. Choose appropriate programming languages and paradigms for the requirement. Faster execution often correlates with lower energy, but the choice should match the system context.
2. Use energy-efficient code design: optimize algorithms, reduce computational complexity, choose suitable data structures, and avoid unnecessary loops or redundant computation.
3. Minimize resource consumption: prevent memory leaks, optimize allocation, use lazy loading, cache frequently accessed data, and close resources.
4. Use try-with-resources in Java when handling closable resources.
5. Optimize database queries: use indexes, avoid `SELECT *` when not needed, prefer efficient joins, use batch processing where appropriate.
6. Reduce API latency: optimize database access, cache data, compress transferred data, minify JavaScript/CSS, and use asynchronous processing where appropriate.
7. Improve documentation and maintainability: clean reusable code, version control discipline, and remove unused dependencies.
8. Support cultural change: teams and management should align on sustainability goals, encourage efficient solutions, and focus on long-term outcomes.

Sustainable AI course points:

- AI has ethical, technical, and environmental sustainability concerns.
- AI workloads can consume significant energy, depend on resource-intensive hardware, and create e-waste.
- Green AI emphasizes energy-efficient computation and lifecycle impact, while Red AI emphasizes accuracy, speed, and performance without necessarily considering environmental cost.
- Adaptation strategies include checking whether AI is needed, using greener data centers, scheduling training for low-carbon periods, reusing pre-trained models, reducing datasets, and avoiding redundant hyperparameter tuning.

## Performance Testing

Performance testing measures whether a system meets speed, responsiveness, and scalability requirements under a defined workload. Performance is a critical non-functional requirement.

Why performance matters:

- Speed and responsiveness improve user satisfaction.
- Reliability testing under workload reveals bottlenecks and scalability limits.
- Performance optimization improves resource allocation and efficiency.
- Early testing mitigates production failure risk and is cheaper than late fixes.

Good performance requirements should be:

- Unambiguous: define context and workload.
- Measurable: use quantities that tools can collect.
- Verifiable: a cost-effective test process exists.
- Complete: parameters and context are fully specified.
- Traceable: linked to business, engineering, stakeholder, or regulatory needs.

Performance testing types:

| Type | Purpose |
|---|---|
| Performance testing | Overall system behavior, speed, and responsiveness. |
| Load testing | Behavior under expected user or transaction levels. |
| Stress testing | Behavior beyond expected limits, including breaking points. |

Information needed for performance analysis:

- Performance objectives such as response time, throughput, and resource constraints.
- Workload specification, including functions used, request order, request rate, and concurrent users.
- Software execution paths, including components, order, repetition, conditions, and parallel execution.
- Execution environment, including hardware, service times, and platform details.
- Resource requirements, including people, equipment, and budget.
- Processing overhead, mapping software resources onto device services.

Six-step testing process:

1. Define performance criteria and requirements.
2. Design and plan tests.
3. Establish test environments.
4. Conduct tests.
5. Study results.
6. Optimize, test, and repeat.

For PetClinic, performance-relevant areas include owner search, pagination, vet listing, database queries, request latency, and resource usage under multiple users.
