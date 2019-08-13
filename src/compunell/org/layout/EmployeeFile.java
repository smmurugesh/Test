package compunell.org.layout;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;
import compunell.org.pojo.Employee;
import compunell.org.util.Util;

public abstract class EmployeeFile {
	public static final String FIXED_LENGTH_FORMAT_CODE = "1";
	public static final String COMMA_SEPARATED_FORMAT_CODE = "2";
	public static final String TEXT_FILE_EXRENSION = "txt";
	public static final String OUTPUT_FILE_PREFIX = "EmployeeOutput";
	public List<Employee> employeeList;
	public String formatCode;
	
	public static final Integer FIRST_NAME_ORDER = 0;
	public static final Integer LAST_NAME_ORDER  = 1;
	public static final Integer START_DATE_ORDER = 2;
	public static final Integer ADDRESS_1_ORDER  = 3;
	public static final Integer ADDRESS_2_ORDER  = 4;
	public static final Integer CITY_ORDER       = 5;
	public static final Integer STATE_ORDER      = 6;
	public static final Integer COUNTRY_ORDER    = 7;
	public static final Integer ZIP_ORDER        = 8;
	
	
	public List<String> getElementList(){
		List<String> list = new ArrayList<String>();
		list.add(FIRST_NAME_ORDER,Employee.FIRST_NAME);
		list.add(LAST_NAME_ORDER,Employee.LAST_NAME);
		list.add(START_DATE_ORDER,Employee.START_DATE);
		list.add(ADDRESS_1_ORDER,Employee.ADDRESS_1);
		list.add(ADDRESS_2_ORDER,Employee.ADDRESS_2);
		list.add(CITY_ORDER,Employee.CITY);
		list.add(STATE_ORDER,Employee.STATE);
		list.add(COUNTRY_ORDER, Employee.COUNTRY);
		list.add(ZIP_ORDER,Employee.ZIP);
		return list;
	}
	 public abstract List<Employee> parseFile() throws IOException;
	 
	 public String getFormatCode() {
			return formatCode;
		}
	 
	 public void setFormatCode(String formatCode) {
			this.formatCode= formatCode;
		}
	 
	 public Employee createEmployeeObject(String line, String firstName, String lastName, String startDate, 
			String address1, String address2, String city, String state, String country, String zip) {
		Employee employee = new Employee();

		if (firstName.trim().length() > 0 && Util.isAlpha(firstName.trim()))
			employee.setFirstName(firstName.trim());
		else {
			System.out.println(
					"This File Record does not have valid  First Name element. This element is mandatory and must contain only alphabets --> "
							+ line);
			return null;
		}
		if (lastName.trim().length() > 0 && Util.isAlpha(lastName.trim()))
			employee.setLastName(lastName.trim());
		else {
			System.out.println(
					"This File Record does not have valid Last Name element. This element is mandatory and must contain only alphabets --> "
							+ line);
			return null;
		}
		if (startDate.trim().length() > 0) {
			try {
				/*
				 * start Date format is YYYYMMDD. Convert this string to MM/DD/YYYY
				 */
				startDate = startDate.trim();
				Calendar cal = Calendar.getInstance();
				cal.set(Integer.valueOf(startDate.substring(0, 4)), Integer.valueOf(startDate.substring(4, 6)) - 1,
						Integer.valueOf(startDate.substring(6, 8)), 0, 0, 0);
				employee.setStartDate(cal.getTime());
			} catch (Exception e) {
				System.out.println("This File Record has incorrect Date format --> " + line);
				return null;
			}
		} else {
			System.out.println("This File Record does not have Start Date --> " + line);
			return null;
		}

		if (Util.isAlphaNumericWithSpace(address1.trim()))
			employee.setAddress1(address1.trim());
		else {
			System.out.println(
					"This File Record does not have valid Address 1. It must contain only alpha Numeric --> " + line);
			return null;
		}
		if (Util.isAlphaNumericWithSpace(address2.trim()))
			employee.setAddress2(address2.trim());
		else {
			System.out.println(
					"This File Record does not have valid Address 2. It must contain only alpha Numeric --> " + line);
			return null;
		}
		if (Util.isAlphaNumericWithSpace(city.trim()))
			employee.setCity(city.trim());
		else {
			System.out.println(
					"This File Record does not have valid City. It must contain only alpha Numeric --> " + line);
			return null;
		}

		if (state.trim().length() > 0) {
			if (Util.isAlpha(state.trim()))
				employee.setState(state.trim());
			else {
				System.out.println(
						"This File Record does not have valid State. It must contain only alphabets --> " + line);
				return null;
			}
		}

		if (country.trim().length() > 0) {
			if (Util.isAlpha(country.trim()))
				employee.setCountry(country.trim());
			else {
				System.out.println(
						"This File Record does not have valid Country. It must contain only alphabets --> " + line);
				return null;
			}
		}
		if (zip.trim().length() > 0 && Util.isNumeric(zip.trim()))
			employee.setZip(Integer.valueOf(zip.trim()));
		else {
			System.out.println("This File Record has incorrect Zip format. Only Numbers are allowed --> " + line);
			return null;
		}

		return employee;
	}
}
