package compunell.org.layout;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import compunell.org.pojo.Employee;

public class DelimiterSeparatedFile extends EmployeeFile {
	private String delimiter = ",";
	private File file;
	private List<Employee> employeeList;
	
	public DelimiterSeparatedFile(File file) {
		this.file = file;
	}
	
	public String getDelimiter() {
		return delimiter;
	}

	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}

	public List<Employee> parseFile() throws IOException {
		employeeList = new ArrayList<Employee>();
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
		Employee employee = null;
		while ((line = br.readLine()) != null) {
			if (line.trim().equals(EmployeeFile.COMMA_SEPARATED_FORMAT_CODE))
				continue;
			try {
				String[] record = line.split(delimiter);
				if (record.length != getElementList().size()) {
					System.out.println("This File Record does not have the expected number of elements  "
							+ getElementList().size() + " --> " + line);
					continue;
				}

				String firstName = record[FIRST_NAME_ORDER];
				String lastName = record[LAST_NAME_ORDER];
				String startDate = record[START_DATE_ORDER];
				String address1 = record[ADDRESS_1_ORDER];
				String address2 = record[ADDRESS_2_ORDER];
				String city = record[CITY_ORDER];
				String state = record[STATE_ORDER];
				String country = record[COUNTRY_ORDER];
				String zip = record[ZIP_ORDER];

				employee = createEmployeeObject(line, firstName, lastName, startDate, address1, address2, city, state,
						country, zip);
				if (null == employee)
					continue;
			} catch (Exception e) {
				System.out.println("This File Record is not in the expected layout --> " + line);
			}
			employeeList.add(employee);
		}
		return employeeList;
	}

}
