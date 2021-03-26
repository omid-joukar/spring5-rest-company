package antigypt.springframework.Services;

import antigypt.springframework.api.v1.mapper.BuyTrolleyMapper;
import antigypt.springframework.api.v1.mapper.CustomerMapper;
import antigypt.springframework.api.v1.mapper.OrderMapper;
import antigypt.springframework.api.v1.model.BuyTrolleyDTO;
import antigypt.springframework.api.v1.model.CustomerDTO;
import antigypt.springframework.api.v1.model.OrderDTO;
import antigypt.springframework.domain.Address;
import antigypt.springframework.domain.Customer;
import antigypt.springframework.exceptions.ResourceNotFoundException;
import antigypt.springframework.repositories.CustomerRepository;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;
    private final OrderMapper orderMapper;
    private final BuyTrolleyMapper buyTrolleyMapper;


    public CustomerServiceImpl(CustomerMapper customerMapper, CustomerRepository customerRepository, OrderMapper orderMapper, BuyTrolleyMapper buyTrolleyMapper) {
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
        this.orderMapper = orderMapper;
        this.buyTrolleyMapper = buyTrolleyMapper;
    }

    @Override
    public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {
        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
        Customer savedCustomer = customerRepository.save(customer);
        CustomerDTO returnedDTO = customerMapper.customerToCustomerDTO(savedCustomer);
        returnedDTO.setCustomerUrl("/api/v1/customers/"+savedCustomer.getCustomerId());
        return returnedDTO;
    }

    @SneakyThrows
    @Override
    public CustomerDTO findCustomerById(Long id) {

        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (!customerOptional.isPresent()){
            throw new ResourceNotFoundException("id is invalid : "+ id);
        }
        Customer foundedCustomer = customerOptional.get();
        CustomerDTO returnedDTO = customerMapper.customerToCustomerDTO(foundedCustomer);
        returnedDTO.setCustomerUrl("/api/v1/customers/"+foundedCustomer.getCustomerId());
        return returnedDTO;
    }

    @SneakyThrows
    @Override
    public CustomerDTO updateCustomerByDTO(Long id, CustomerDTO customerDTO) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (!customerOptional.isPresent()){
            throw new ResourceNotFoundException("id is invalid : "+id);
        }
        Customer customer = customerOptional.get();
        customer.setCustomerId(id);
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setEmail(customerDTO.getEmail());
        Address address = new Address();
        address.setPostalCode(customerDTO.getAddress().getPostalCode());
        address.setRegion(customerDTO.getAddress().getRegion());
        address.setCountry(customerDTO.getAddress().getCountry());
        address.setCity(customerDTO.getAddress().getCity());
        address.setAddressLine(customerDTO.getAddress().getAddressLine());
        customer.setAddress(address);
        customer.setMobilePhone(customerDTO.getMobilePhone());
        customer.setHomePhone(customerDTO.getHomePhone());
        CustomerDTO updatedDTO = customerMapper.customerToCustomerDTO(customerRepository.save(customer));
        updatedDTO.setCustomerUrl("/api/v1/customers/"+id);
        return updatedDTO;
    }

    @Override
    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public boolean isNew(CustomerDTO customerDTO) {
        boolean isObjectNew = true;
        for (CustomerDTO customerDTO1 : findAllCustomers()) {
            if (customerDTO.getEmail().equals(customerDTO1.getEmail())) {
                isObjectNew = false;
                break;
            }
        }
        return isObjectNew;
    }

    @Override
    public List<CustomerDTO> findAllByLastName(String customerLastName) {
        return customerRepository.findAllByLastName(customerLastName)
                .stream()
                .map(customer -> {
                    CustomerDTO returnedDTO = customerMapper.customerToCustomerDTO(customer);
                    returnedDTO.setCustomerUrl("/api/v1/customers/"+customer.getCustomerId());
                    return returnedDTO;
                }).collect(Collectors.toList());
    }

    @Override
    public List<CustomerDTO> findAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customer ->{
                    CustomerDTO returnedDTO = customerMapper.customerToCustomerDTO(customer);
                    returnedDTO.setCustomerUrl("/api/v1/customers/"+customer.getCustomerId());
                    return returnedDTO;
                } ).collect(Collectors.toList());
    }

    @SneakyThrows
    @Override
    public List<OrderDTO> findAllCustomerOrders(Long customerId) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if (!customerOptional.isPresent()){
            throw new ResourceNotFoundException("id is invalid : " + customerId);
        }
        Customer foundedCustomer = customerOptional.get();
        return foundedCustomer.getOrderList()
                .stream()
                .map(order -> {
                    OrderDTO returnedDTO = orderMapper.orderToOrderDTO(order);
                    returnedDTO.setOrderUrl("/api/v1/orders/"+order.getOrderId());
                    return returnedDTO;
                }).collect(Collectors.toList());
    }

    @SneakyThrows
    @Override
    public OrderDTO findCustomersOrderByOrderId(Long customerId, Long orderId) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if (!customerOptional.isPresent()){
            throw new ResourceNotFoundException("id is invalid : " + customerId);
        }
        Customer foundedCustomer = customerOptional.get();
        return foundedCustomer.getOrderList()
                .stream()
                .filter(order -> order.getOrderId().equals(orderId))
                .findFirst()
                .map(order -> {
                    OrderDTO returnedDTO = orderMapper.orderToOrderDTO(order);
                    returnedDTO.setOrderUrl("/api/v1/orders/"+order.getOrderId());
                    return returnedDTO;
                }).orElseThrow(ResourceNotFoundException::new);
    }

    @SneakyThrows
    @Override
    public BuyTrolleyDTO findCustomersBuyTrolleyById(Long customerId) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if (!customerOptional.isPresent()){
            throw new ResourceNotFoundException("id is invalid : " + customerId);
        }
        Customer foundedCustomer = customerOptional.get();
        return buyTrolleyMapper.buyTrolleyToBuyTrolleyDTO(foundedCustomer.getBuyTrolley());
    }

}
