package compunell.org.layout;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import compunell.org.pojo.Employee;
public class FixedLengthFile extends EmployeeFile {
     
	private int recordLength = 80;
	private File file;
	
	private static final Integer FIRST_NAME_LENGTH = 10;
	private static final Integer LAST_NAME_LENGTH  = 17;
	private static final Integer START_DATE_LENGTH = 8;
	private static final Integer ADDRESS_1_LENGTH  = 10;
	private static final Integer ADDRESS_2_LENGTH  = 10;
	private static final Integer CITY_LENGTH       = 10;
	private static final Integer STATE_LENGTH      = 2;
	private static final Integer COUNTRY_LENGTH    = 3;
	private static final Integer ZIP_LENGTH        = 10;
	
	
	
	
	public Integer calculateElementBeginIndex(String element) {
		Map<String, Integer> elementLengthMap = getElementLengthMap();
		List<String> elementOrderList = getElementList();
		Integer beginIndex = 0;
		Integer index = elementOrderList.indexOf(element);
		for(int i = 0; i< index; i++) {
			beginIndex = beginIndex + elementLengthMap.get(elementOrderList.get(i));
		}
		
		return beginIndex;
	}
	
	public FixedLengthFile(File file) {
	 this.file = file;	
	}

	public int getRecordLength() {
		return recordLength;
	}

	public void setRecordLength(int recordLength) {
		this.recordLength = recordLength;
	}

	
	
	public Map<String, Integer> getElementLengthMap(){
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put(Employee.FIRST_NAME, FIRST_NAME_LENGTH);
		map.put(Employee.LAST_NAME, LAST_NAME_LENGTH);
		map.put(Employee.START_DATE, START_DATE_LENGTH);
		map.put(Employee.ADDRESS_1, ADDRESS_1_LENGTH);
		map.put(Employee.ADDRESS_2, ADDRESS_2_LENGTH);
		map.put(Employee.CITY, CITY_LENGTH);
		map.put(Employee.STATE, STATE_LENGTH);
		map.put(Employee.COUNTRY, COUNTRY_LENGTH);
		map.put(Employee.ZIP, ZIP_LENGTH);
		return map;
	}
	
	
	@Override
	public List<Employee> parseFile() throws IOException {
		employeeList = new ArrayList<Employee>();
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
		Employee employee = null;
		Map<String, Integer> elementLengthMap = getElementLengthMap();
		List<String> elementOrderList = getElementList();
		while ((line = br.readLine()) != null) {
			if (line.trim().equals(EmployeeFile.FIXED_LENGTH_FORMAT_CODE))
				continue;
			if (line.length() != recordLength) {
				System.out.println(
						"This File Record is not in the expected record length  " + recordLength + " --> " + line);
				continue;
			}
			try {
				String firstName = line.substring(calculateElementBeginIndex(Employee.FIRST_NAME),
						calculateElementBeginIndex(Employee.FIRST_NAME) + elementLengthMap.get(Employee.FIRST_NAME));
				String lastName = line.substring(calculateElementBeginIndex(Employee.LAST_NAME),
						calculateElementBeginIndex(Employee.LAST_NAME) + elementLengthMap.get(Employee.LAST_NAME));
				String startDate = line.substring(calculateElementBeginIndex(Employee.START_DATE),
						calculateElementBeginIndex(Employee.START_DATE) + elementLengthMap.get(Employee.START_DATE));
				String address1 = line.substring(calculateElementBeginIndex(Employee.ADDRESS_1),
						calculateElementBeginIndex(Employee.ADDRESS_1) + elementLengthMap.get(Employee.ADDRESS_1));
				String address2 = line.substring(calculateElementBeginIndex(Employee.ADDRESS_2),
						calculateElementBeginIndex(Employee.ADDRESS_2) + elementLengthMap.get(Employee.ADDRESS_2));
				String city = line.substring(calculateElementBeginIndex(Employee.CITY),
						calculateElementBeginIndex(Employee.CITY) + elementLengthMap.get(Employee.CITY));
				String state = line.substring(calculateElementBeginIndex(Employee.STATE),
						calculateElementBeginIndex(Employee.STATE) + elementLengthMap.get(Employee.STATE));
				String country = line.substring(calculateElementBeginIndex(Employee.COUNTRY),
						calculateElementBeginIndex(Employee.COUNTRY) + elementLengthMap.get(Employee.COUNTRY));
				String zip = line.substring(calculateElementBeginIndex(Employee.ZIP),
						calculateElementBeginIndex(Employee.ZIP) + elementLengthMap.get(Employee.ZIP));

				employee = createEmployeeObject(line, firstName, lastName, startDate, address1, address2, city, state,
						country, zip);
				if (null == employee)
					continue;
			} catch (Exception e) {
				System.out.println("This File Record is not in the expected layout --> " + line);
			}
			if (null != employee)
				employeeList.add(employee);
		}
		return employeeList;
	}
	
	

}
