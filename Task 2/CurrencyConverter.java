

import java.util.Scanner;

    public class CurrencyConverter {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            // Step 1: Prompt the user to enter the amount to be converted
            System.out.print("Enter the amount to be converted: ");
            double amount = scanner.nextDouble();

            // Step 2: Prompt the user to enter the source currency
            System.out.print("Enter the source currency (e.g., USD, EUR, INR): ");
            String sourceCurrency = scanner.next().toUpperCase();

            // Step 3: Prompt the user to enter the target currency
            System.out.print("Enter the target currency (e.g., USD, EUR, INR): ");
            String targetCurrency = scanner.next().toUpperCase();

            // Step 4: Define conversion rates (replace these with actual rates from an API in real-world applications)
            double usdToInrRate = 74.5;
            double usdToEurRate = 0.85;
            double eurToInrRate = 87.6;

            // Step 5: Fetch the appropriate conversion rate based on source and target currencies
            double conversionRate;
            if (sourceCurrency.equals("USD") && targetCurrency.equals("INR")) {
                conversionRate = usdToInrRate;
            } else if (sourceCurrency.equals("USD") && targetCurrency.equals("EUR")) {
                conversionRate = usdToEurRate;
            } else if (sourceCurrency.equals("EUR") && targetCurrency.equals("INR")) {
                conversionRate = eurToInrRate;
            } else if (sourceCurrency.equals(targetCurrency)) {
                conversionRate = 1.0; // If the source and target currencies are the same, no conversion needed.
            } else {
                System.out.println("Unsupported currency conversion.");
                scanner.close();
                return;
            }

            // Step 6: Perform the currency conversion
            double convertedAmount = amount * conversionRate;

            // Step 7: Display the converted amount to the user
            System.out.println("Converted amount: " + convertedAmount + " " + targetCurrency);

            scanner.close();
        }
    }

