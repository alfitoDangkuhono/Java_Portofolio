package Application.test.simpleCRUD.Controller;

import Application.test.simpleCRUD.Model.WarnaKendaraan;
import Application.test.simpleCRUD.Repository.WarnaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.concurrent.ExecutionException;

@Controller
@RequestMapping("/api")
public class WarnaController {

    @Autowired
    private WarnaRepository repository;

    @RequestMapping(value = "/create-warna",method = RequestMethod.POST)
    public RedirectView create(@RequestParam(name = "warna")String warna){
        try{
            WarnaKendaraan data=new WarnaKendaraan();
            data.setWarna_kendaraan(warna);
            WarnaKendaraan result=repository.save(data);
            return new RedirectView("/");
        }
        catch (Exception e){
            e.getStackTrace();
            return null;
        }
    }
}
