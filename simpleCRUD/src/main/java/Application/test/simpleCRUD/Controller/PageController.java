package Application.test.simpleCRUD.Controller;

import Application.test.simpleCRUD.Model.DataKendaraan;
import Application.test.simpleCRUD.Model.WarnaKendaraan;
import Application.test.simpleCRUD.Repository.KendaraanRepository;
import Application.test.simpleCRUD.Repository.WarnaRepository;
import Application.test.simpleCRUD.Service.KendaraanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping
public class PageController {
    @Autowired
    private WarnaRepository repo;
    @Autowired
    private KendaraanRepository kendaraanRepository;
    @Autowired
    private KendaraanService service;
    @GetMapping
    public String index(DataKendaraan kendaraan,Model model){
        List<DataKendaraan> data=kendaraanRepository.findAll();
        model.addAttribute("data",data);
        return "home";
    }
    @GetMapping("/create-warna")
    public String createWarna(){
        return "createWarna";
    }
    @GetMapping("/create-data")
    public String createData(Model model){
        List<WarnaKendaraan>data=repo.findAll();
        model.addAttribute("data",data);
        return "create";
    }
    @GetMapping("/edit-data/{id}")
    public String editData(Model model, @PathVariable String id){
        DataKendaraan data=kendaraanRepository.findById(id).orElse(null);
        List<WarnaKendaraan>warna=repo.findAll();
        model.addAttribute("data",data);
        model.addAttribute("warna",warna);
        return "edit";
    }
    @GetMapping("/detail-data/{id}")
    public String detailData(Model model, @PathVariable String id){
        DataKendaraan data=kendaraanRepository.findById(id).orElse(null);
        model.addAttribute("data",data);
        return "detail";
    }

}
