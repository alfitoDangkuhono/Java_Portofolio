package Application.test.simpleCRUD.Controller;

import Application.test.simpleCRUD.Model.DataKendaraan;
import Application.test.simpleCRUD.Model.WarnaKendaraan;
import Application.test.simpleCRUD.Repository.KendaraanRepository;
import Application.test.simpleCRUD.Service.KendaraanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import java.util.List;

@Controller
@RequestMapping("/api")
public class CRUDController {
    @Autowired
    private KendaraanRepository repository;
    @Autowired
    private KendaraanService service;

    @GetMapping("/search/{noRegistrasi}")
    public String search(@ModelAttribute DataKendaraan data, Model model){
        DataKendaraan data_kendaraan=service.get(data.getNoRegistrasi());
        if (data_kendaraan == null) {
            System.out.println("data is null");
        }
        System.out.println(data_kendaraan);
        model.addAttribute("data",data_kendaraan);
        return "home";
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public RedirectView createData(@RequestParam(name = "no_registrasi")String no_registrasi,
                                   @RequestParam(name = "nama_pemilik")String name,
                                   @RequestParam(name = "merk_kendaraan")String merk_kendaraan,
                                   @RequestParam(name = "tahun_pembuatan")Integer tahun_pembuatan,
                                   @RequestParam(name = "kapasitas_silinder")Integer kapasitas_silinder,
                                   @RequestParam(name = "bahan_bakar")String bahan_bakar,
                                   @RequestParam(name = "warna")WarnaKendaraan warna,
                                   @RequestParam(name = "alamat")String alamat ){

        try{

            DataKendaraan data=new DataKendaraan();
            data.setNoRegistrasi(no_registrasi);
            data.setAlamat(alamat);
            data.setMerk(merk_kendaraan);
            data.setName_pemilik(name);
            data.setKapasitas_silinder(kapasitas_silinder);
            data.setTahun_pembuatan(tahun_pembuatan);
            data.setBahan_bakar(bahan_bakar);
            data.setWarnaKendaraan(warna);
            DataKendaraan result=repository.save(data);
            System.out.println(result +"data created");
            return new RedirectView("/");
        }
        catch (Exception e){
            e.getStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/update-data/{id}",method = RequestMethod.POST)
    public RedirectView updateData(@PathVariable String id,
                                                    @RequestParam(name = "nama_pemilik")String name,
                                                    @RequestParam(name = "merk_kendaraan")String merk_kendaraan,
                                                    @RequestParam(name = "tahun_pembuatan")Integer tahun_pembuatan,
                                                    @RequestParam(name = "kapasitas_silinder")Integer kapasitas_silinder,
                                                    @RequestParam(name = "bahan_bakar")String bahan_bakar,
                                                    @RequestParam(name = "warna")WarnaKendaraan warna,
                                                    @RequestParam(name = "alamat")String alamat ){

        try{
            DataKendaraan data=repository.findById(id).orElse(null);
            if (data==null){
                System.out.println("data is null");
                return null;
            }
            data.setAlamat(alamat);
            data.setMerk(merk_kendaraan);
            data.setName_pemilik(name);
            data.setKapasitas_silinder(kapasitas_silinder);
            data.setTahun_pembuatan(tahun_pembuatan);
            data.setBahan_bakar(bahan_bakar);
            data.setWarnaKendaraan(warna);
            DataKendaraan result=repository.save(data);
            System.out.println(result +"data updated");
//            return new ResponseEntity<>(data, HttpStatus.OK);
            return new RedirectView("/");
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("exception scope");
        }
        return null;
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<DataKendaraan> deleteId (@PathVariable("id") String id){
        DataKendaraan data=service.cariId(id).orElse(null);
        if (data== null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
