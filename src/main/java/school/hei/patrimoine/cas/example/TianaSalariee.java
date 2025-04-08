package school.hei.patrimoine.cas.example;

import static java.time.Month.APRIL;
import static java.time.Month.AUGUST;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;
import static java.time.Month.JUNE;
import static java.time.Month.MAY;
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

public class TianaSalariee extends Cas {

  private final Compte compteBancaire;
  private final Materiel terrain;

  public TianaSalariee() {
    super(LocalDate.of(2025, APRIL, 8), LocalDate.of(2026, APRIL, 8), new Personne("Tiana"));
    
    LocalDate dateReference = LocalDate.of(2025, APRIL, 8);
    
    compteBancaire = new Compte("Compte bancaire", dateReference, ariary(60_000_000));

    terrain = new Materiel("Terrain bâti", dateReference, dateReference, ariary(100_000_000), 0.10);
    
    new FluxArgent(
        "Dépenses familiales",
        compteBancaire,
        dateReference,
        finSimulation,
        1, // Tous les 1 du mois
        ariary(-4_000_000));
    
    // Projet entrepreneurial (juin 2025 - décembre 2025)
    LocalDate debutProjet = LocalDate.of(2025, JUNE, 1);
    LocalDate finProjet = LocalDate.of(2025, DECEMBER, 31);
    
    new FluxArgent(
        "Acompte projet",
        compteBancaire,
        LocalDate.of(2025, MAY, 1),
        LocalDate.of(2025, MAY, 1),
        1,
        ariary(7_000_000)); // 10% de 70_000_000
    
    // Dépenses mensuelles du projet
    new FluxArgent(
        "Dépenses projet",
        compteBancaire,
        debutProjet,
        finProjet,
        5, // Tous les 5 du mois
        ariary(-5_000_000));
    
    // Paiement final du projet (90% après la fin)
    new FluxArgent(
        "Paiement final projet",
        compteBancaire,
        LocalDate.of(2026, JANUARY, 31),
        LocalDate.of(2026, JANUARY, 31),
        1,
        ariary(63_000_000)); // 90% de 70_000_000
    
    // Prêt bancaire
    LocalDate datePret = LocalDate.of(2025, JULY, 27);
    LocalDate debutRemboursement = LocalDate.of(2025, AUGUST, 27);
    
    // Réception du prêt
    new FluxArgent(
        "Prêt bancaire",
        compteBancaire,
        datePret,
        datePret,
        1,
        ariary(20_000_000));
    
    // Remboursement mensuel du prêt
    new FluxArgent(
        "Remboursement prêt",
        compteBancaire,
        debutRemboursement,
        LocalDate.of(2026, JULY, 27),
        27, // Tous les 27 du mois
        ariary(-2_000_000));
  }

  @Override
  protected String nom() {
    return "Tiana";
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
    return Set.of(compteBancaire, terrain);
  }

  @Override
  protected void suivi() {
    // Pas besoin de suivi particulier
  }
} 