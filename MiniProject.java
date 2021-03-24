import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;
public class AnalyzeBabyNames {
    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        int numOfBirths = 0;
        for (CSVRecord record : getFileParser(year)) {
            if (record.get(0).equals(name) && record.get(1).equals(gender)) {
                numOfBirths = Integer.parseInt(record.get(2));
            }
        }
        
        int totalBirths = 0;
        for (CSVRecord record : getFileParser(year)) {
            String currentGender = record.get(1);
            if (!record.get(0).equals(name) && currentGender.equals(gender) && 
                Integer.parseInt(record.get(2)) >= numOfBirths) {
                totalBirths += Integer.parseInt(record.get(2));
            }
        }
        return totalBirths;
    }
    
    public void testGetTotalBirthsRankedHigher() {
        int year = 1990;
        String name = "Drew";
        String gender = "M";
        int totalBirths = getTotalBirthsRankedHigher(year, name, gender);
        System.out.println("Total number of births of those with the same gender who " +
                            "are ranked higher than " + name + ", " + gender + " in " + year
                            + ": " + totalBirths);
    }
    
    public double getAverageRank(String name, String gender) {
        // Allow user to select a range of files
        DirectoryResource dir = new DirectoryResource();
        double totalRank = 0.0;
        int count = 0;
        for (File f : dir.selectedFiles()) {
            // Extract current year from file name
            int currentYear = Integer.parseInt(f.getName().substring(3,7));
            // Determine rank of name in current year
            int currentRank = getRank(currentYear, name, gender);
            // Add rank to total and increment count
            totalRank += currentRank;
            count++;
        }
        // Return calculated average rank
        if (totalRank == 0) {
            return -1;
        }
        double average = totalRank/count;
        return average;
    }
    
    public void testGetAverageRank() {
        String name = "Susan";
        String gender = "F";
        double avg = getAverageRank(name, gender);
        System.out.println("Average rank of " + name + ", " + gender + ": " + avg);
    }
    
    public int yearOfHighestRank(String name, String gender) {
        // Allow user to select a range of files
        DirectoryResource dir = new DirectoryResource();
        int year = 0;
        int rank = 0;
        // For every file the user selected
        for (File f : dir.selectedFiles()) {
            // Extract current year from file name
            int currentYear = Integer.parseInt(f.getName().substring(3,7));
            // Determine rank of name in current year
            int currentRank = getRank(currentYear, name, gender);
            System.out.println("Rank in year " + currentYear + ": " + currentRank);
            // If current rank isn't invalid
            if (currentRank != -1) {
                // If on first file or if current rank is higher than saved rank
                if (rank == 0 || currentRank < rank) {
                    // Update tracker variables
                    rank = currentRank;
                    year = currentYear;
                } 
            }
        }
        
        if (year == 0) {
            return -1; 
        }
        return year;
    }
    
    public void testYearOfHighestRank() {
        String name = "Genevieve";
        String gender = "F";
        int year = yearOfHighestRank(name, gender);
        System.out.println("The year with the highest rank for " + name + " (gender " + gender
                            + ") is " + year);
    }
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender) {
        int rank = getRank(year, name, gender); 
        System.out.println("The rank of Owen is " + rank);
        String newName = getName(newYear, rank, gender);
        System.out.println(name + " born in " + year + " would be " 
                            + newName + " if born in " + newYear);
    }
    
    public void testWhatIsNameInYear() {
        whatIsNameInYear("Susan", 1972, 2014, "F");
    }
    
    public String getName(int year, int rank, String gender) {
        int currentRank = 0;
        String name = "";
        for (CSVRecord record : getFileParser(year)) {
            if (record.get(1).equals(gender)) {
                if (currentRank == rank) {
                    return name;
                }
                name = record.get(0);
                currentRank++;
            }
        }
        
        return "NO NAME";
    }
    
    public void testGetName() {
        int year = 1982;
        int rank = 450;
        String gender = "M";
        String name = getName(year, rank, gender);
        System.out.println("In " + year + ", the " + gender + " at rank " + rank + " was " + name);
    }
    
    public int getRank(int year, String name, String gender) {
        int rank = 1;
        for (CSVRecord record : getFileParser(year)) {
            if (record.get(1).equals(gender)) {
                if (record.get(0).equals(name)) {
                    return rank;
                }
                rank++;
            }
        }
        return -1;
    }
    
    public void testGetRank() {
        int year = 1960;
        String name = "Emily";
        String gender = "F";
        int rank = getRank(year, name, gender);
        System.out.println("Rank of " + name + ", " + gender + ", in " + year + ": " + rank);  
    }

    public void totalBirths () {
        FileResource fr = new FileResource();
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        int totalNames = 0;
        StorageResource uniqueGirlNames = new StorageResource();
        StorageResource uniqueBoyNames = new StorageResource();
        for (CSVRecord record : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(record.get(2));
            totalBirths += numBorn;
            totalNames++;
            String name = record.get(0);
            if (record.get(1).equals("M")) {
                totalBoys += numBorn;
                if (!uniqueBoyNames.contains(name)) {
                    uniqueBoyNames.add(name);
                }
            }
            else {
                totalGirls += numBorn;
                if (!uniqueGirlNames.contains(name)) {
                    uniqueGirlNames.add(name);
                }
            }
        }
        System.out.println(uniqueBoyNames.size());
        System.out.println(uniqueGirlNames.size());
        System.out.println(totalNames);
        System.out.println(totalBirths);
        System.out.println(totalGirls);
        System.out.println(totalBoys);
    }
    
    public CSVParser getFileParser(int year) {
        FileResource fr = new FileResource(String.format("data/us_babynames_by_year/yob%s.csv", year));
        return fr.getCSVParser(false);
    }
}

