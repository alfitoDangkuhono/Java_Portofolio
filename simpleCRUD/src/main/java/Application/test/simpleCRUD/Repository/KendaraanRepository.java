package Application.test.simpleCRUD.Repository;

import Application.test.simpleCRUD.Model.DataKendaraan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface KendaraanRepository extends JpaRepository<DataKendaraan, String> {
}
