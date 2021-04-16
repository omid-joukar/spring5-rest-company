package antigypt.springframework.controllers.api.v1;


import antigypt.springframework.Services.SaleService;
import antigypt.springframework.api.v1.model.SaleListDTO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/sales")
@CrossOrigin(origins = "*")
public class SaleController {
    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }
    @GetMapping
    public SaleListDTO getAllSales(){
        return new SaleListDTO(saleService.getAllSales());
    }


}