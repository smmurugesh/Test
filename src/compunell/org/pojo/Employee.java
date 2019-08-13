package compunell.org.pojo;

import java.util.Date;

public class Employee implements Comparable<Employee> {
	private String firstName;
	private String lastName;
	private Date startDate;
	private String address1;
	private String address2;
	private String city;
	private String state="CA";
	private String country="USA";
	private Integer zip;
	
	public static final  String FIRST_NAME = "First Name";
	public static final  String LAST_NAME  = "Last Name";
	public static final  String START_DATE = "Start Date";
	public static final  String ADDRESS_1  = "Address 1";
	public static final  String ADDRESS_2  = "Address 2";
	public static final  String CITY       = "City";
	public static final  String STATE      = "State";
	public static final  String COUNTRY    ="Country";
	public static final  String ZIP        = "Zip";
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Integer getZip() {
		return zip;
	}
	public void setZip(Integer zip) {
		this.zip = zip;
	}
	@Override
	public int compareTo(Employee o) {
		int comparisonResult = 0;
		if(this.firstName != null && o.firstName != null) 
			comparisonResult = String.CASE_INSENSITIVE_ORDER.compare(this.firstName, o.firstName);
			if(comparisonResult != 0)
				return comparisonResult;
		if(this.lastName != null && o.lastName != null) 
			comparisonResult = String.CASE_INSENSITIVE_ORDER.compare(this.lastName, o.lastName);
			if(comparisonResult != 0)
				return comparisonResult;
		if(this.startDate != null && o.startDate != null)
			comparisonResult = this.startDate.compareTo(o.startDate);
		
		return comparisonResult;
	}

}
