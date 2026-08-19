# EvoLab — Fast-Track Addendum
### What to build if you're ahead of pace (companion to EvoLab_Roadmap.md)

**How this works:** this is not a separate plan — it layers on top of the 5-week core
roadmap. Every stretch item below has a **gate**: a condition that must already be true
before anyone touches it. If the gate isn't met, skip it and keep working the core plan.
Never let a fast-track item delay that week's core deliverable — the MVP (Week 3's
end-to-end run) is non-negotiable; everything here is upside on top of it.

**Order of priority if you have to pick:** dual live visualization first (it's core to the
pitch, not a stretch goal), then Geographic Isolation experiment, then Neighbor-Joining,
then sexual selection, then crossover/linkage, then Environmental Shift, then Maximum
Parsimony, then interactive interventions. Earlier items give more payoff per hour spent.

---

## Week 1 Fast Track
**Gate:** all four Week 1 core deliverables done and teach-back completed by Thursday.

- **Module A:** add the third `ExpressionRule` — codominant — so all three expression
  modes from the spec exist, not just two.
- **Module C:** start reading up on Neighbor-Joining (don't implement yet — UPGMA has to
  land first in Week 2) so it's a known quantity rather than a cold start in Week 3.
- **Module D:** stub out the *second* visualization mode now — a placeholder toggle
  between "true ancestry tree" and "reconstructed tree" panels, even with fake data. This
  is what makes dual live visualization achievable later without a scramble.

---

## Week 2 Fast Track
**Gate:** UPGMA verified correct on toy data, and the 10-organism multi-generation console
test is stable, both by Thursday.

- **Module B:** add the second environmental factor from the spec (a resource axis on top
  of terrain/camouflage) — the spec's "explicitly not doing" list only cuts this for time,
  it's real depth if you have it.
- **Module C:** begin Neighbor-Joining implementation as a second `TreeBuildingAlgorithm`.
  Keep UPGMA as the default; NJ is additive, not a replacement.
- **Module D:** get the true-ancestry side of the split-screen actually rendering live
  from `AncestryRecorder` (not just the reconstructed side) — this is the real "dual live
  visualization, split-screen, always in sync" from the original pitch.

---

## Week 3 Fast Track
**Gate:** the full end-to-end MVP run (evolve → record → reconstruct → RF distance) is
working and independently verified by Wednesday, two days ahead of the core deadline.

- **Module A:** implement crossover/linkage between genes on the same "chromosome" —
  the spec's own nice-to-have. Add a config flag so you can A/B whether it changes
  reconstruction accuracy — that's a bonus data point for the report, not just a feature.
- **Module B:** begin sexual selection as an additional fitness pressure (mate choice
  biased toward a trait, layered on top of the existing environmental fitness).
- **Module C:** finish Neighbor-Joining fully and wire `TreeComparator` to report RF
  distance for **both** UPGMA and NJ side by side — this directly strengthens the research
  question (which method degrades faster as mutation rate rises?).
- **Module D:** finish the dual live visualization properly — both trees live, radial
  layout, in sync with the ecosystem panel.

---

## Week 4 Fast Track
**Gate:** Mutation Rate Sweep experiment has clean results and Geographic Isolation is at
least running (even if not fully tuned), by Wednesday.

- **Module A / B (joint):** run the third experiment — **Environmental Shift** — a sudden
  mid-run terrain or resource change, observing the selection-pressure spike. This
  completes all three experiments from the original spec, not two.
- **Module C:** if UPGMA vs. NJ is in from Week 3, extend the Mutation Rate Sweep and
  Geographic Isolation experiments to report both methods' RF distance — turns "we ran an
  experiment" into "we compared two reconstruction methods across three experimental
  conditions," which is a materially stronger result section.
- **Module D:** build the fitness-landscape heatmap (population's trait cloud climbing
  toward local optima) — the one visualization explicitly marked optional in the core plan.

---

## Week 5 Fast Track
**Gate:** all three experiments have final figures and the core report/demo materials are
drafted, by Wednesday — this gate is stricter than the others since Week 5 is your only
polish buffer.

- **Module A:** if crossover/linkage landed in Week 3, add a fourth "linkage strength"
  sweep as a bonus experiment — cheap to run once the plumbing exists, and it's genuinely
  novel analysis nobody asked for.
- **Module B:** implement basic interactive god-mode interventions beyond pause/speed —
  a UI trigger for "force an isolation event now" or "force a climate shift now" during a
  live demo. This is a strong live-demo moment if it's stable, but skip it entirely if
  Module A/B's bug bash isn't clean yet — a crash mid-demo is worse than not having it.
- **Module C:** attempt Maximum Parsimony as a third tree-building method **only** if NJ
  is fully solid and RF comparisons across UPGMA/NJ are already report-ready. This is the
  single most optional item in this whole document — do not start it if anything upstream
  is shaky.
- **Module D:** if the interactive interventions from Module B land, wire a live
  before/after visual (tree structure visibly changing shape after a triggered isolation
  event) — this is the kind of moment worth recording for the portfolio clip.

---

## If You Finish Everything Early: Bonus Week 6

Only relevant if all three core experiments, dual visualization, and at least one Week 3–5
stretch item are done with a full week still on the clock.

- Run a combined sweep: mutation rate × population size × time-since-divergence as a
  proper factorial experiment (the spec's research question names all three as variables —
  the core plan only isolates mutation rate).
- Produce a short written "where reconstruction breaks down" analysis — the actual
  research-paper-style conclusion the spec's research question is asking for, distinct from
  just reporting RF numbers per experiment.
- Polish pass on the visual portfolio clip — this is genuinely worth extra time if you have
  it; the spec calls it out as the best visual payoff of the project.

---

## The Rule for This Document

If a gate isn't met, don't start the stretch item — full stop, even if one person
personally has spare time. A half-finished stretch feature left mid-merge is worse for the
team than not attempting it, because it becomes something someone else has to clean up
during Week 5's bug bash. Finish and merge the core deliverable first, every time.
