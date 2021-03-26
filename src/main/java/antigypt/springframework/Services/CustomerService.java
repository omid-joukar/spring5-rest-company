package antigypt.springframework.Services;

import antigypt.springframework.api.v1.model.BuyTrolleyDTO;
import antigypt.springframework.api.v1.model.CustomerDTO;
import antigypt.springframework.api.v1.model.OrderDTO;


import java.util.List;

public interface CustomerService {
    CustomerDTO createNewCustomer(CustomerDTO customerDTO);
    CustomerDTO findCustomerById(Long id);
    CustomerDTO updateCustomerByDTO(Long id,CustomerDTO customerDTO);
    void deleteCustomerById(Long id);
    boolean isNew(CustomerDTO customerDTO);
    List<CustomerDTO> findAllByLastName(String customerLastName);
    List<CustomerDTO> findAllCustomers();
    List<OrderDTO> findAllCustomerOrders(Long customerId);
    OrderDTO findCustomersOrderByOrderId(Long customerId, Long OrderId);
    BuyTrolleyDTO findCustomersBuyTrolleyById(Long customerId);


}
