package ma.emsi.dashboardfitness.services;

import ma.emsi.dashboardfitness.entities.Nutrition;
import ma.emsi.dashboardfitness.repositories.INutritionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.hibernate.internal.util.collections.ArrayHelper.forEach;

@Service
public class NutritionService {
    private INutritionRepository nutritionRepository;
    // couplage faible
    public NutritionService(INutritionRepository nutritionRepository) {
        this.nutritionRepository = nutritionRepository;
    }

    //ajouter une nutrition par l'admin a la base de donnee
    public Nutrition ajouterNutrition(Nutrition nutrition) {
        return nutritionRepository.save(nutrition);
    }
    //supprimer une nutrition par l'admin de la base de donnee
    public void supprimerNutrition(Nutrition nutrition) {
        nutritionRepository.delete(nutrition);
    }
    //supprimer une Nutrition en se basant sur son ID
    public void supprimerNutritionById(Long id) {
        boolean existingNutrition = nutritionRepository.findById(id).isPresent();
        if(existingNutrition) {
            System.out.println("suppression de la nutrition qui a l'id: " + id);
            nutritionRepository.deleteById(id);
        }
    }
    // update d'une nutrition
    public void modifierNutrition(Long id , Nutrition updatedNutrition) {
        Nutrition existingNutrition = nutritionRepository.findById(id).orElse(null);
        if(existingNutrition != null) {
            existingNutrition.setNomNutrition(updatedNutrition.getNomNutrition());
            existingNutrition.setType(updatedNutrition.getType());
            existingNutrition.setCalorie(updatedNutrition.getCalorie());
            existingNutrition.setProteine(updatedNutrition.getProteine());
            existingNutrition.setGraisse(updatedNutrition.getGraisse());
            nutritionRepository.save(existingNutrition);
        }
    }
    //afficher toutes les nutritions
    public List<Nutrition > afficherNutritions() {

        return nutritionRepository.findAll();
    }
    public Nutrition getNutritionById(Long id) {
        return nutritionRepository.findById(id).orElse(null);
    }
    //chercher une nutrition en se basant de son nom
    public List<Nutrition> chercherNutritionParNom(String name) {
        return nutritionRepository.findByNomNutritionContainingIgnoreCase(name);
    }

}
