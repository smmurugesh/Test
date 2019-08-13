package compunell.org.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import compunell.org.layout.DelimiterSeparatedFile;
import compunell.org.layout.EmployeeFile;
import compunell.org.layout.FixedLengthFile;
import compunell.org.pojo.Employee;
import compunell.org.util.Util;

public class CompunellMain {

	public static void processInput(String userInput) throws IOException {
		/*
		 * 1. Check whether User Input is a valid file path
		 */
		boolean validPath = Util.isPathValid(userInput);
		if (!validPath) {
			System.out.println("File path is not valid");
			return;
		}

		/*
		 * 2. Check the file to read is ASCII file type .txt
		 */
		Optional<String> optional = Util.getFileExtension(userInput);
		if (optional.isPresent()) {
			if (!(optional.get().equalsIgnoreCase(EmployeeFile.TEXT_FILE_EXRENSION))) {
				System.out.println(
						"File Extension is not valid. Only " + EmployeeFile.TEXT_FILE_EXRENSION + " file is expected");
				return;
			}
		} else {
			System.out.println(
					"File Extension is not valid. Only " + EmployeeFile.TEXT_FILE_EXRENSION + " file is expected");
			return;
		}

		/*
		 * 3. Check the file code and parse the lines to Employee Objects based on
		 * layouts
		 */
		File file = new File(userInput);
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
		EmployeeFile employeeFile = null;
		List<Employee> employeeList = null;
		while ((line = br.readLine()) != null) {
			if (line.trim().equals(EmployeeFile.FIXED_LENGTH_FORMAT_CODE)) {
				employeeFile = new FixedLengthFile(file);
			} else if (line.trim().equals(EmployeeFile.COMMA_SEPARATED_FORMAT_CODE)) {
				employeeFile = new DelimiterSeparatedFile(file);
			} else {
				System.out.println("Invalid file format code. It should be one of these --> "
						+ EmployeeFile.FIXED_LENGTH_FORMAT_CODE + " or " + EmployeeFile.COMMA_SEPARATED_FORMAT_CODE);
				break;
			}
			br.close();
			employeeFile.setFormatCode(line.trim());
			employeeList = employeeFile.parseFile();
			break;
		}

		/*
		 * 4. Sort the Employee Objects, Print in the format to output file
		 */
		if (null != employeeList && employeeList.size() > 0) {
			Collections.sort(employeeList);
			StringBuffer fileContent = new StringBuffer();
			int index = 1;

			String outFilePath = file.getAbsoluteFile().getParent() + File.separator + employeeFile.OUTPUT_FILE_PREFIX
					+ employeeFile.getFormatCode() + "." + employeeFile.TEXT_FILE_EXRENSION;
			PrintWriter writer = new PrintWriter(new FileWriter(outFilePath));
			for (Employee employee : employeeList) {
				writer.println(index);
				writer.println(" " + employee.getFirstName() + " " + employee.getLastName() + ", " + "("
						+ new SimpleDateFormat("MM/dd/YYYY").format(employee.getStartDate()) + ")");
				writer.println(" " + employee.getAddress1() + ", " + employee.getAddress2());
				writer.println(" " + employee.getCity() + ", " + employee.getState());
				writer.println(" " + employee.getCountry() + ", " + employee.getZip());
				++index;
			}
			writer.close();
			System.out.println("Employee Output file is created in this path --> " + outFilePath);
		}
	}

	public static void main(String[] args) {

		String userInput = "";
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			System.out.println("** Enter the Full Path (Including the File Name) of Employee record Input file **");
			System.out.println("** Press 0 anytime to exit **");
			try {
				userInput = reader.readLine();
			} catch (IOException e) {
				System.out.println("IO Exception in reading the input");
			}
			if (null != userInput && userInput.equals("0"))
				System.exit(0);
			if (null != userInput && userInput.length() > 0) {
				try {
					processInput(userInput);
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}
			} else
				System.out.println("Invalid Input");
			System.out.println("** End of Program Execution **");
		}
	}

}
