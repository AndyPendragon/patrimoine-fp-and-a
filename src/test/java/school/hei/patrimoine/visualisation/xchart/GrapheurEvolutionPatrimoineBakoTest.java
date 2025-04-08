package school.hei.patrimoine.visualisation.xchart;

import static java.time.Month.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import school.hei.patrimoine.ResourceFileGetter;
import school.hei.patrimoine.cas.example.BakoSalariee;
import school.hei.patrimoine.modele.Patrimoine;
import school.hei.patrimoine.modele.evolution.EvolutionPatrimoine;
import school.hei.patrimoine.visualisation.AreImagesEqual;

class GrapheurEvolutionPatrimoineBakoTest {
  private final GrapheurEvolutionPatrimoine grapheurEvolutionPatrimoine =
          new GrapheurEvolutionPatrimoine();
  private final AreImagesEqual areImagesEqual = new AreImagesEqual();
  private final ResourceFileGetter resourceFileGetter = new ResourceFileGetter();

  private Patrimoine patrimoine() {
    return new BakoSalariee().patrimoine();
  }

  @Test
  void visualise_sur_quelques_annees() {
    var patrimoine =
            new EvolutionPatrimoine(
                    "Bako", patrimoine(), LocalDate.of(2025, APRIL, 8), LocalDate.of(2025, DECEMBER, 31));

    var imageGeneree = grapheurEvolutionPatrimoine.apply(patrimoine);

    assertTrue(
            areImagesEqual.apply(
                    resourceFileGetter.apply("patrimoine-bako-sur-quelques-annees.png"), imageGeneree));
  }
}
