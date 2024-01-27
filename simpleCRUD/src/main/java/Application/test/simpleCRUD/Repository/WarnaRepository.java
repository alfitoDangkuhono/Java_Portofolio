package Application.test.simpleCRUD.Repository;

import Application.test.simpleCRUD.Model.WarnaKendaraan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarnaRepository extends JpaRepository<WarnaKendaraan,Integer> {
}
