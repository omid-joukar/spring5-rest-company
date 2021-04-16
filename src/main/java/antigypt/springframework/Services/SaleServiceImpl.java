package antigypt.springframework.Services;

import antigypt.springframework.api.v1.mapper.SaleMapper;

import antigypt.springframework.api.v1.model.SaleDTO;
import antigypt.springframework.repositories.SaleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaleServiceImpl implements SaleService{
    private final SaleRepository saleRepository;
    private final SaleMapper saleMapper;

    public SaleServiceImpl(SaleRepository saleRepository, SaleMapper saleMapper) {
        this.saleRepository = saleRepository;
        this.saleMapper = saleMapper;
    }

    @Override
    public List<SaleDTO> getAllSales() {
        return saleRepository.findAll()
                .stream().map(sale -> {
                    SaleDTO saleDTO = saleMapper.saleToSaleDTO(sale);
                    saleDTO.setSaleUrl("/api/v1/sales/"+ sale.getSaleId());
                    return saleDTO;
                }).collect(Collectors.toList());
    }
}
