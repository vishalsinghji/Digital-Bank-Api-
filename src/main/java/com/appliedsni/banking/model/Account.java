package com.appliedsni.banking.model;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CacheModeType;

import javax.*;


@Entity
@Table(name = "AccountDetail")
public class Account
{
	private long Ac_no;
	private Customer Customer;
    private Long Amount;
    
    public Account()
    {
    	
    }
  
    public Account(long Ac_no,Long Amount,Customer Customer) 
    {
    	this.Customer = Customer;
        this.Amount = Amount;
        this.Ac_no = Ac_no;
    }
    

	@Id
	@JoinColumn(name="Ac_no",nullable=false)
    public long getAc_no() {
        return Ac_no;
    }
    public void setAc_no(long Ac_no)
    {
        this.Ac_no = Ac_no;
    }
    
    @Column(name = "Amount",nullable=false)
    public Long getAmount() {
        return Amount;
    }
    public void setAmount(Long Amount) {
        this.Amount = Amount;
    }
    
    @OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public Customer getCustomer() {
		return Customer;
	}

	public void setCustomer(Customer customer) {
		Customer = customer;
	}

	@Override
    public String toString() {
    	 return "AccountDetail[Ac_no=" + Ac_no + ", Amount=" + Amount+ "]";
    		    }

}
