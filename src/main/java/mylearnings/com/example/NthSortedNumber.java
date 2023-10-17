package mylearnings.com.example;

public class NthSortedNumber {
    public static int findNthNumber(int n) {
        int current = 1;  // Initialize with the first natural number
        int count = 0;
        
        while (count < n) {
            current++; // Move to the next natural number
            int product = current * (current + 1); // Initialize the product with the current and next number
            
            while (count < n && isProductOfConsecutiveNumbers(product)) {
                count++;
                if (count == n) {
                    return product;
                }
                current++; // Move to the next consecutive number
                product *= (current + 1);
            }
        }
        
        return -1; // Error or out of bounds
    }
    
    public static boolean isProductOfConsecutiveNumbers(int num) {
        for (int i = 1; i <= num; i++) {
            if (i * (i + 1) == num) {
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        int n = 4; // Replace with the desired Nth number
        int nthNumber = findNthNumber(n);
        System.out.println("The " + n + "th number is: " + nthNumber);
    }
}
