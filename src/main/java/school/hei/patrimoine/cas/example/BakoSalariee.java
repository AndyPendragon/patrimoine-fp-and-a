package school.hei.patrimoine.cas.example;

import static java.time.Month.APRIL;
import static school.hei.patrimoine.modele.Argent.ariary;
import static school.hei.patrimoine.modele.Devise.MGA;

import java.time.LocalDate;
import java.util.Set;
import school.hei.patrimoine.cas.Cas;
import school.hei.patrimoine.modele.Devise;
import school.hei.patrimoine.modele.Personne;
import school.hei.patrimoine.modele.possession.Compte;
import school.hei.patrimoine.modele.possession.FluxArgent;
import school.hei.patrimoine.modele.possession.Materiel;
import school.hei.patrimoine.modele.possession.Possession;
import school.hei.patrimoine.modele.possession.TransfertArgent;

public class BakoSalariee extends Cas {

  private final Compte compteBNI;
  private final Compte compteBMOI;
  private final Compte coffreFort;
  private final Materiel ordinateur;

  public BakoSalariee() {
    super(LocalDate.of(2025, APRIL, 8), LocalDate.of(2026, APRIL, 8), new Personne("Bako"));
    
    // Date de référence: 8 avril 2025
    LocalDate dateReference = LocalDate.of(2025, APRIL, 8);
    
    // Création des comptes
    compteBNI = new Compte("Compte BNI", dateReference, ariary(2_000_000));
    compteBMOI = new Compte("Compte BMOI", dateReference, ariary(625_000));
    coffreFort = new Compte("Coffre fort", dateReference, ariary(1_750_000));
    
    // Création du matériel (ordinateur portable)
    ordinateur = new Materiel("Ordinateur portable", dateReference, dateReference, ariary(3_000_000), -0.12);
    
    // Flux d'argent: Salaire mensuel
    new FluxArgent(
        "Salaire",
        compteBNI,
        dateReference,
        finSimulation,
        2, // Tous les 2 du mois
        ariary(2_125_000));
    
    // Transfert d'argent: Épargne mensuelle
    new TransfertArgent(
        "Épargne mensuelle",
        compteBNI,
        compteBMOI,
        dateReference.plusDays(1), // Le lendemain du salaire
        finSimulation,
        3, // Tous les 3 du mois
        ariary(200_000));
    
    // Flux d'argent: Loyer
    new FluxArgent(
        "Loyer",
        compteBNI,
        dateReference,
        finSimulation,
        26, // Tous les 26 du mois
        ariary(-600_000));
    
    // Flux d'argent: Dépenses de vie
    new FluxArgent(
        "Dépenses de vie",
        compteBNI,
        dateReference,
        finSimulation,
        1, // Tous les 1 du mois
        ariary(-700_000));
  }

  @Override
  protected String nom() {
    return "Bako";
  }

  @Override
  protected Devise devise() {
    return MGA;
  }

  @Override
  protected void init() {
    // Pas besoin d'initialisation supplémentaire car les comptes sont déjà initialisés avec leurs valeurs
  }

  @Override
  public Set<Possession> possessions() {
    return Set.of(compteBNI, compteBMOI, coffreFort, ordinateur);
  }

  @Override
  protected void suivi() {
    // Pas besoin de suivi particulier
  }
} 