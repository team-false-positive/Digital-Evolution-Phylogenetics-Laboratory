# EvoLab — Build Plan
### Digital Evolution & Phylogenetics Laboratory | Java + JavaFX | Team of 4 | 5 Weeks

**How to use this document:** each week has exactly 4 modules (A, B, C, D). Assign one
module per person and keep the same ownership lane every week — it maps directly to the
spec's suggested split (Genetics Core / Evolution+Environment / Phylogenetics+Analysis /
Visualization+Report). At the end of every week, each person explains their module to the
other three (30–45 min, screen-share, no slides). Don't start next week's modules until that
session has happened.

**Reality check on scope:** the full spec (6 patterns, 3 experiments, crossover/linkage,
sexual selection, a third tree-building method, dual live visualization) is an 8–11 week
project. In 5 weeks, the goal is a **working core loop** — a population that evolves, a
ground-truth ancestry, a blind reconstruction, and a real Robinson-Foulds number — plus
**one** well-tuned experiment. Everything past that is explicitly stretch. If you only have
4 weeks, drop Week 4's second experiment and Module D's fitness-landscape heatmap.

**Cut for time (do not attempt before the core loop is solid and demoed):**
- Gene linkage / crossover between loci
- Sexual selection
- Maximum Parsimony as a third tree method
- Interactive god-mode interventions beyond pause/speed
- The Environmental Shift experiment (keep Mutation Rate Sweep + Geographic Isolation only)

---

## Day 0 (a few hours, not a week)

1. Create the GitHub repo, add all 4 as collaborators.
2. Install the shared toolchain: JDK 21 (Eclipse Temurin), Maven, JavaFX SDK, an IDE with
   Java support (IntelliJ or VS Code + Java extensions), Git.
3. Agree: `main` always works; everyone branches off it
   (`git checkout -b module/your-part-name`); merge via Pull Request; one other person
   approves before merging.
4. **As a team, before anyone writes simulation logic:** lock the `Organism` /
   `Genotype` / `Phenotype` data contract (fields, types, how `ExpressionRule` is invoked).
   This is the dependency backbone the spec calls out — every module blocks on it, so it
   has to be settled today, not sometime in Week 1.
5. Everyone skims JavaFX basics — Scene graph vs. `Canvas`, `Pane` layouts, and the
   `Observable`/listener pattern JavaFX already uses internally (it maps directly onto the
   Observer pattern you'll build for the simulation engine).

---

## WEEK 1 — Domain Model & Interface Contracts

**Goal by end of week:** every core interface exists and compiles; each person can build in
parallel from Week 2 onward without waiting on anyone else.

**Module A — Genetics Core** *(Gene, Allele, Genotype, ExpressionRule)*
- `Gene` (locus + attached `ExpressionRule`), `Allele` (variant + effect value), `Genotype`
  (map of Gene → allele pair).
- `ExpressionRule` as a **Strategy** interface, with two concrete implementations:
  dominant/recessive and additive/polygenic. Codominant can wait.
- Deliverable: given a hard-coded `Genotype`, `Phenotype` values compute correctly for both
  expression rules. Unit tests cover both.

**Module B — Evolution + Environment** *(Organism, LifeStage, Region)*
- `Organism` wrapping `Genotype` + computed `Phenotype`, `LifeStage` as a **State** pattern
  (Juvenile → Adult → Senescent, with juvenile unable to reproduce), `Behavior` as a
  **Strategy** stub (Foraging/Fleeing — logic can be trivial this week).
- `Region`/`Environment` as a **Composite** — terrain color + one resource axis. Skip the
  second environmental factor from the spec; one axis (terrain/camouflage) is enough.
- Deliverable: an `Organism` transitions through life stages correctly; a `Region` composite
  prints its structure.

**Module C — Phylogenetics + Analysis** *(DistanceMatrix, TreeBuildingAlgorithm)*
- `DistanceMatrix` computed from genome similarity between organisms.
- `TreeBuildingAlgorithm` as a **Strategy** interface with a UPGMA stub (implementation
  lands Week 2).
- Deliverable: `DistanceMatrix` correctly computed from 4–5 hard-coded toy genomes,
  verified by hand and unit-tested.

**Module D — Visualization + Report** *(JavaFX shell, Observer contract)*
- JavaFX application shell: split-screen layout — left pane for the ecosystem, right pane
  for the tree, a bottom strip reserved for stats panels.
- Define the `SimulationListener` interface (the **Observer** contract the engine will
  push ticks to) — this is what Modules A/B build against starting Week 2.
- Deliverable: app launches; split-screen skeleton renders with placeholder shapes.

---

## WEEK 2 — Reproduction, Fitness, Ancestry & UPGMA

**Goal by end of week:** one full generation cycle works — reproduction via real meiosis
logic, fitness derived from environment, ancestry recorded as it happens, and UPGMA
produces a correct tree on toy data.

**Module A — Genetics Core**
- `ReproductionStrategy` (**Strategy**): meiosis via independent segregation + per-allele
  mutation chance. No crossover/linkage — cut for scope.
- `AncestryRecorder`: the true parent→child lineage graph, appended on every birth.
- Deliverable: two parent `Organism`s reproduce; offspring genotype is correctly derived;
  the birth is recorded in `AncestryRecorder`.

**Module B — Evolution + Environment**
- `FitnessFunction` (**Composite**): sums per-trait contributions (start with just
  coat-color-vs-terrain match).
- `EvolutionEngine` tick loop: evaluate fitness → select → reproduce → advance generation,
  firing `SimulationListener` events each tick (wire to Module D's interface).
- Deliverable: a 10-organism population runs for several generations in a console test;
  fitness values are sane (fitter phenotypes reproduce more).

**Module C — Phylogenetics + Analysis**
- Full UPGMA implementation. Neighbor-Joining is a stretch — only attempt once UPGMA is
  solid and tested.
- `PopulationGeneticsTracker`: allele frequency tracking per generation + Hardy-Weinberg
  expected genotype ratio calculation.
- Deliverable: UPGMA builds a correct tree from a 4–5-taxon test matrix (hand-verified);
  allele frequencies tracked correctly across a manually-stepped test run.

**Module D — Visualization + Report**
- `EcosystemRenderer`: organisms drawn on `Canvas` as shapes colored/sized from phenotype,
  positioned on a terrain-colored grid.
- Wire it as a listener to Module B's engine (use stub tick data if the engine isn't ready
  by mid-week — don't block on it).
- Deliverable: organisms visibly render and update as simulated ticks arrive.

---

## WEEK 3 — End-to-End Integration (the "make it real" week)

**Goal by end of week:** the whole pipeline runs start to finish — population evolves for
many generations, true ancestry is recorded, a tree is blindly reconstructed from final
genomes, and Robinson-Foulds distance is computed. This is the MVP; everything after this
week is polish and experiments, not new plumbing.

**Module A — Genetics Core**
- Integrate fully into `EvolutionEngine`; make mutation rate a configurable parameter.
- Unit tests for edge cases: zero mutation rate, very high mutation rate, homogeneous
  starting population.

**Module B — Evolution + Environment**
- `ExperimentController` (**Command**): pause / speed / run-N-generations as executable
  commands. Interventions beyond that are cut.
- `Factory` for population initialization from a config (population size, starting allele
  distribution, mutation rate).
- Run the engine end-to-end: 50+ generations, population of 30–50.

**Module C — Phylogenetics + Analysis**
- `TreeComparator`: Robinson-Foulds distance between `AncestryRecorder`'s ground truth and
  the UPGMA-reconstructed tree. Attempt Neighbor-Joining only if ahead of schedule.

**Module D — Visualization + Report**
- `PhylogeneticTreeRenderer`, radial layout — reconstructed tree at minimum; dual mode
  (true vs. inferred) if time allows.
- Stats panel skeleton: live allele-frequency line plot wired to Module C's tracker.

**Team deliverable, end of week:** one complete run, console-logged or on-screen —
population evolves → ancestry recorded → tree reconstructed → RF distance printed. Get a
teammate outside the phylogenetics module to independently verify one RF number by hand
on a tiny run.

---

## WEEK 4 — Experiments & Analytics Polish

**Goal by end of week:** real, reportable results from actual experiments — this is what
turns the project from a demo into a study, per the spec's own framing.

**Module A — Genetics Core**
- Run and tune the **Mutation Rate Sweep** experiment (low/medium/high). Collect genetic
  diversity and reconstruction-accuracy data across all three settings.

**Module B — Evolution + Environment**
- Run and tune the **Geographic Isolation** experiment: split the population into two
  regions with zero gene flow, run for many generations, check whether reconstruction
  correctly identifies the divergence. *(If you're on 4 weeks total, this is the module to
  cut — ship with Mutation Rate Sweep alone.)*

**Module C — Phylogenetics + Analysis**
- Finalize Hardy-Weinberg deviation plotting and the genotype-distribution histogram.
- Package `PopulationGeneticsTracker` output into report-ready figures for both
  experiments.

**Module D — Visualization + Report**
- Finish the live allele-frequency panel and the true-vs-reconstructed tree side-by-side
  final screen (this is the money shot — prioritize it over the fitness-landscape heatmap,
  which is optional if time is short).

---

## WEEK 5 — Integration Testing, Polish, Report & Demo

**Goal by end of week:** stable, rehearsed, submission-ready.

**Module A — Genetics Core**
- Bug bash on genetics/reproduction logic; finalize unit test suite.

**Module B — Evolution + Environment**
- Bug bash on engine + experiments; performance pass if larger populations (50+) lag.

**Module C — Phylogenetics + Analysis**
- Write up the RF-distance results from both experiments — the actual quantitative
  answer to the research question. This is the section that makes the project a study.

**Module D — Visualization + Report**
- Assemble final report/slides: architecture diagram, justification for each of the
  patterns actually used (Strategy, Composite, Observer, State, Command, Factory), and a
  timed demo script. Record a short clip of the split-screen ecosystem + tree building live
  — it's the strongest visual asset you have.

---

## The One Rule That Makes This Work

If a module's owner is stuck and about to lose the week to it, say so immediately in your
daily check-in — don't wait for the teach-back session to reveal a problem. The
`Organism`/`Genotype`/`Phenotype` contract locked on Day 0 and the `SimulationListener`
Observer contract from Week 1 are the two things every later module quietly depends on;
a silent blocker on either one doesn't just cost one person a week, it stalls the whole team
behind it. With only 5 weeks, there's no slack to absorb a late-discovered blocker.
