package Application.test.simpleCRUD.Service;

import Application.test.simpleCRUD.Model.DataKendaraan;
import Application.test.simpleCRUD.Repository.KendaraanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class KendaraanService implements  ServiceKendaraan{
    @Autowired
    private KendaraanRepository repository;

    @Override
    public void delete(String no_registrasi) {
        repository.deleteById(no_registrasi);
    }

    @Override
    public Optional<DataKendaraan> cariId(String no_registrasi) {
       return repository.findById(no_registrasi);
    }

    @Override
    public DataKendaraan get(String id) {
        return repository.findById(id).get();
    }


}
