package com.appliedsni.banking.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CustomerDetail")
public class Customer {
	
	private long Cus_ID;
    private String firstName;
    private String lastName;
    private String Address;
    public Customer()
    {
  	  
    }
    
    public Customer(Long Cus_ID,String firstName, String lastName, String Address) 
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.Address =Address;
        this.Cus_ID=Cus_ID;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getCus_ID() {
        return Cus_ID;
    }
    public void setCus_ID(Long Cus_ID )
    {
        this.Cus_ID = Cus_ID;
    }
    
    @Column(name = "first_name",nullable=false)
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    @Column(name = "last_name",nullable=false)
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    @Column(name = "Address",nullable=false)
    public String getAddress() {
        return Address;
    }
    public void setAddress(String Address) {
        this.Address = Address;
    }
    
    @Override
    public String toString() {
    	 return "CustomerDetail [Cus_ID=" + Cus_ID + ", firstName=" + firstName + ", lastName=" + lastName + ",Address=" + Address+ "]";
    		    }
}
