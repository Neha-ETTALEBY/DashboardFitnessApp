package ma.emsi.dashboardfitness.repositories;
import ma.emsi.dashboardfitness.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface INutritionRepository extends JpaRepository<Nutrition, Long> {
}
