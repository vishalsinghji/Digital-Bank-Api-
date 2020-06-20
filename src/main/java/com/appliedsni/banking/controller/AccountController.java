package com.appliedsni.banking.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.appliedsni.banking.exception.ResourceNotFoundException;
import com.appliedsni.banking.model.Account;
import com.appliedsni.banking.model.Customer;
//import com.appliedsni.banking.model.Customer;
import com.appliedsni.banking.repository.AccountRepository;
import com.appliedsni.banking.repository.CustomerRepository;



@RestController
@RequestMapping("/api/v1")
public class AccountController 
{
	@Autowired
    private AccountRepository AccountRepository;

    @GetMapping("/account_detail")
    public List<Account> getAllbank() {
        return AccountRepository.findAll();
    }

    @GetMapping("/account_detail/{Ac_no}")
    public ResponseEntity<Account> getAccountDetailByAc_no(@PathVariable(value = "Ac_no") Long accountDetailAc_no)
        throws ResourceNotFoundException {
    	Account accountDetail = AccountRepository.findById(accountDetailAc_no)
          .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id ::"));
        return ResponseEntity.ok().body(accountDetail);
    }
    
    @PostMapping("/account_detail")
    public Account createAccountDetail(@Valid @RequestBody Account accountDetail) 
    {
        return AccountRepository.save(accountDetail);
    }
    

   @PutMapping("/account_detail/{Ac_no}/{Amount}")
    public ResponseEntity<Account> DepositAccountDetail(@PathVariable(value = "Ac_no") Long AccountDetailAc_no,
    		@PathVariable(value = "Amount") Integer amount) throws ResourceNotFoundException {
	   Account accountDetail = AccountRepository.findById(AccountDetailAc_no)
        .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + AccountDetailAc_no));
	   
	   accountDetail.setAmount(amount+accountDetail.getAmount());
        final Account updateAccountDetail = AccountRepository.save(accountDetail);
        return ResponseEntity.ok(updateAccountDetail);
    }
   
   
   @PutMapping("/account_detail/withdraw/{Ac_no}/{Amount}")
   public ResponseEntity<Account> WithdrawAccountDetail(@PathVariable(value = "Ac_no") Long AccountDetailAc_no,
   		@PathVariable(value = "Amount") Integer amount) throws ResourceNotFoundException {
	   Account accountDetail = AccountRepository.findById(AccountDetailAc_no)
       .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + AccountDetailAc_no));
	   
	   accountDetail.setAmount(accountDetail.getAmount()-amount);
       final Account updateAccountDetail = AccountRepository.save(accountDetail);
       return ResponseEntity.ok(updateAccountDetail);
   }
   
   
    
    @DeleteMapping("/account_detail/{Ac_no}")
    public Map<String, Boolean> deleteAccountDetail(@PathVariable(value = "Ac_no") Long AccountDetailAc_no)
         throws ResourceNotFoundException {
    	Account accountDetail = AccountRepository.findById(AccountDetailAc_no)
       .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + AccountDetailAc_no));

    	AccountRepository.delete(accountDetail);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
