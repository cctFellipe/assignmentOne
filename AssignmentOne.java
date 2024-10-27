/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package assignmentone;

// Importing classes for reading and writing text files
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class AssignmentOne {

    public static void main(String[] args) {
        // Tries to run this code and catches any errors if they occur
        try {
           //opens file to read and write
            BufferedReader reader = new BufferedReader(new FileReader("customer.txt"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("customerdiscount.txt"));

            // declares name total price and classes
            String firstName, secondName;
            double total;
            int classNumber, lastPurchaseYear;

            String line; // holds each line

            
            
            
            
            
            // read file until there is no more
            while ((line = reader.readLine()) != null) {
                // splits line to get first and second name
                String[] nameParts = line.split(" ");
                if (nameParts.length != 2 || !nameParts[0].matches("[a-zA-Z]+")) {
                    System.out.println("Error: Invalid name format");
                    continue;
                }
                firstName = nameParts[0];
                secondName = nameParts[1];

                
                
                
                
                
                //this line reads the total price without discount
                line = reader.readLine();
                if (!line.matches("\\d+(\\.\\d+)?")) {
                    System.out.println("Error: Invalid purchase amount");
                    continue;
                }
                total = Double.parseDouble(line);

                
                
                
                
                //read the customers classes
                line = reader.readLine();
                if (!line.matches("[1-3]")) {
                    System.out.println("Error: Invalid class number. Must be 1, 2, or 3.");
                    continue;
                }
                classNumber = Integer.parseInt(line);

                // reads the next line for the last purchase year
                line = reader.readLine();
                if (!line.matches("\\d{4}") || Integer.parseInt(line) > 2024) {
                    System.out.println("Error: Invalid last purchase year.");
                    continue;
                }
                lastPurchaseYear = Integer.parseInt(line);

                
                
                
                // this give the discount using th classes
                double discount = 0;
                if (classNumber == 1) {
                    if (lastPurchaseYear == 2024) {
                        discount = 0.30;
                    } else if (lastPurchaseYear >= 2019) {
                        discount = 0.20;
                    } else {
                        discount = 0.10;
                    }
                } else if (classNumber == 2) {
                    if (lastPurchaseYear == 2024) {
                        discount = 0.15;
                    } else if (lastPurchaseYear >= 2019) {
                        discount = 0.13;
                    } else {
                        discount = 0.05;
                    }
                } else if (classNumber == 3) {
                    if (lastPurchaseYear == 2024) {
                        discount = 0.03;
                    } else if (lastPurchaseYear >= 2019) {
                        discount = 0.00;
                    }
                }

                
                
                // calculates the final amount after applying the discount
                double finalValue = total - (total * discount);

                // writes the results to "customerdiscount.txt"
                writer.write(firstName + " " + secondName + "\n");
                writer.write("Final Price: $" + String.format("%.2f", finalValue) + "\n\n");
            }

            
            
            // Closes both the reader and writer
            reader.close();
            writer.close();
            System.out.println("Discounts calculated and saved in customerdiscount.txt.");

        } catch (IOException e) {
            System.out.println("Error reading or writing file: " + e.getMessage());
        }
    }
}
