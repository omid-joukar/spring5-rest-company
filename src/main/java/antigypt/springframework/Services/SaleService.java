package antigypt.springframework.Services;

import antigypt.springframework.api.v1.model.SaleDTO;
import java.util.List;

public interface SaleService {
    List<SaleDTO> getAllSales();
}
