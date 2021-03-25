import edu.duke.*;
import org.apache.commons.csv.*;

public class ParsingExportData {

	public static void numberOfExporters(CSVParser parser, String exportItem) {
		int count = 0; 
		for (CSVRecord record : parser) {
			String export = record.get("Exports");
			if (export.contains(exportItem)) {
				String country = record.get("Country");
				System.out.println(country);
				count++;
			}
		}
		System.out.println("How many countries export " + exportItem + " : " + count);
	}

	public static void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
		for (CSVRecord record : parser) {
			String export = record.get("Exports");
			if (export.contains(exportItem1) && export.contains(exportItem2)) {
				String country = record.get("Country");
				System.out.println(country);
			}
		}
	}

	public static void countryInfo(CSVParser parser, String country) {		
		boolean found = false;
		for (CSVRecord record : parser) {
			String getCountry = record.get("Country");
			if (getCountry.contains(country)) {
				String export = record.get("Exports");
				System.out.println(getCountry + " : " + export);
				found = true;
			}
		}
		if (!found) System.out.println("NOT FOUND");
	}

	public static void bigExporters(CSVParser parser, String amount) {
		for (CSVRecord record : parser) {
			String value = record.get("Value (dollars)");
			if (value.length() >("$"+amount).length() ) {
				String country = record.get("Country");
				System.out.println(country + " : " + value);

			}
		}
	}
	public static void tester() {
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser();
		countryInfo(parser, "Germany");
		parser = fr.getCSVParser();
		listExportersTwoProducts(parser, "gold", "diamonds");
		parser = fr.getCSVParser();
		numberOfExporters(parser, "gold");
		parser = fr.getCSVParser();
		bigExporters(parser, "$999,999,999");
	}

	public static void main(String[] args) {
		ParsingExportData parse=new ParsingExportData();
		parse.tester();
	}
}
