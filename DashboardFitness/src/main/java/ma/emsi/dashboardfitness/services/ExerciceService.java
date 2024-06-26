package ma.emsi.dashboardfitness.services;

import ma.emsi.dashboardfitness.entities.Entrainement;
import ma.emsi.dashboardfitness.entities.Exercice;
import ma.emsi.dashboardfitness.entities.Nutrition;
import ma.emsi.dashboardfitness.repositories.IExerciceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciceService {
    private IExerciceRepository exerciceRepository;

    //injection de dependance
    public ExerciceService(IExerciceRepository exerciceRepository) {

        this.exerciceRepository = exerciceRepository;
    }
    //l'ajout d'un exercice par l'admin a la base de donnee
    public Exercice ajouterExercice(Exercice exercice) {
       return exerciceRepository.save(exercice);
    }
    //supprimer un exercice par l'admin de la base de donnee
    public void supprimerexercice(Exercice exercice) {
        exerciceRepository.delete(exercice);
    }
    //supprimer un exercice en se basant sur son ID
    public void supprimerExerciceById(Long id) {
        boolean existingExercice = exerciceRepository.findById(id).isPresent();
        if(existingExercice) {
            System.out.println("suppression de l'exercice qui a l'id: " + id);
            exerciceRepository.deleteById(id);
        }
    }
    // update d'un exercice
    public void modifierExercice(Long id , Exercice updatedExercice) {
        Exercice existingExercice = exerciceRepository.findById(id).orElse(null);
        if(existingExercice != null) {
            existingExercice.setDescription(updatedExercice.getDescription());
            existingExercice.setDuree(updatedExercice.getDuree());
            existingExercice.setEntrainement(updatedExercice.getEntrainement());
            existingExercice.setImage(updatedExercice.getImage());
            existingExercice.setNom(updatedExercice.getNom());
            existingExercice.setNombreDeRep(updatedExercice.getNombreDeRep());
            exerciceRepository.save(existingExercice);
        }
    }
    //afficher tous les exercices
    public List<Exercice> afficherExercices() {
        return exerciceRepository.findAll();
    }
    public Exercice getExerciceById(Long id) {
        return exerciceRepository.findById(id).orElse(null);
    }
    public List<Exercice> chercherExercicesParNom(String nom) {
        return exerciceRepository.findByNomContainingIgnoreCase(nom);
    }
    //public List<Entrainement> getEntrainementsByNom(String nom) {

    //}
}
