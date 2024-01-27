package Application.test.simpleCRUD.Service;

import Application.test.simpleCRUD.Model.DataKendaraan;

import java.util.Optional;


public interface ServiceKendaraan {
    void delete(String no_registrasi);
    Optional<DataKendaraan> cariId(String no_registrasi);
    DataKendaraan get(String no_registrasi);
}
