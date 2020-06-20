package com.appliedsni.banking.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.appliedsni.banking.exception.ResourceNotFoundException;
import com.appliedsni.banking.model.Customer;
import com.appliedsni.banking.repository.CustomerRepository;


@RestController
@RequestMapping("/api/v1")
public class CustomerController 
{
	@Autowired
    private CustomerRepository CustomerRepository;

    @GetMapping("/CustomerDetail")
    public List<Customer> getAllbank() {
        return CustomerRepository.findAll();
    }
	
    
    @GetMapping("/CustomerDetail/{Cus_ID}")
    public ResponseEntity<Customer> getEmployeeById(@PathVariable(value = "Cus_ID") Long employeeId)
        throws ResourceNotFoundException {
    	Customer CustomerDetail = CustomerRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id ::"));
        return ResponseEntity.ok().body(CustomerDetail);
    }
    
    @PostMapping("/CustomerDetail")
    public Customer createCumsomerDetail(@Valid @RequestBody Customer cumsomerDetail) 
    {
        return CustomerRepository.save(cumsomerDetail);
    }

    @PutMapping("/CustomerDetail/{Cus_ID}")
    public ResponseEntity<Customer> updateCustomerDetail(@PathVariable(value = "Cus_ID") Long customerDetaiCus_ID,
         @Valid @RequestBody Customer customerDetail) throws ResourceNotFoundException {
    	Customer customerDetail1 = CustomerRepository.findById(customerDetaiCus_ID)
        .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + customerDetaiCus_ID));

    	customerDetail1.setCus_ID(customerDetail1.getCus_ID());
    	customerDetail1.setFirstName(customerDetail1.getFirstName());
    	customerDetail1.setLastName(customerDetail1.getLastName());
        final Customer updatedCustomerDetail = CustomerRepository.save(customerDetail);
        return ResponseEntity.ok(updatedCustomerDetail);
    }
    
    @DeleteMapping("/CustomerDetail/{Cus_ID}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "Cus_ID") Long CumsomerDetailCus_ID)
         throws ResourceNotFoundException {
    	Customer cumsomerDetail = CustomerRepository.findById(CumsomerDetailCus_ID)
       .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + CumsomerDetailCus_ID));

    	CustomerRepository.delete(cumsomerDetail);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
