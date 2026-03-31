import java.util.Scanner;

public class SkinSense {
    
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        boolean repeat = true;
        
        displayWelcome();
        
        while (repeat) {
            int[] scores = conductAssessment();
            String skinType = determineSkinType(scores);
            displayResults(scores, skinType);
            provideRecommendations(skinType);
            
            System.out.print("\nWould you like to take the assessment again? (yes/no): ");
            String response = scanner.next().toLowerCase();
            repeat = response.equals("yes") || response.equals("y");
        }
        
        displayThankYou();
        scanner.close();
    }
    
    public static void displayWelcome() {
        System.out.println("========================================");
        System.out.println("         SKINSENSE");
        System.out.println("    Your Personal Skincare Assistant");
        System.out.println("========================================");
        System.out.println("\nThis assessment will help you understand");
        System.out.println("your skin type and provide personalized");
        System.out.println("skincare recommendations.\n");
    }
    
    public static int[] conductAssessment() {
        String[] questions = {
            "How often does your skin feel oily or shiny?",
            "How often does your skin feel tight or flaky?",
            "How often does your skin react to new products?",
            "How often do you experience breakouts or pimples?",
            "How often does your skin feel rough or uneven?"
        };
        
        int[] responses = new int[5];
        
        System.out.println("\nPlease answer the following questions about your skin.");
        System.out.println("Choose from:");
        System.out.println("1 = Not at all");
        System.out.println("2 = Slightly");
        System.out.println("3 = Moderately");
        System.out.println("4 = Very much\n");
        
        for (int i = 0; i < questions.length; i++) {
            while (true) {
                System.out.print((i + 1) + ". " + questions[i] + " : ");
                if (scanner.hasNextInt()) {
                    int input = scanner.nextInt();
                    if (input >= 1 && input <= 4) {
                        responses[i] = input;
                        break;
                    }
                }
                System.out.println("Invalid input. Please enter 1, 2, 3, or 4.");
                scanner.next();
            }
        }
        
        // Calculate scores for each skin characteristic
        int oilyScore = responses[0];           // Oily from question 1
        int dryScore = responses[1];              // Dry from question 2
        int sensitiveScore = responses[2];        // Sensitive from question 3
        int acneScore = responses[3];              // Acne from question 4
        
        return new int[]{oilyScore, dryScore, sensitiveScore, acneScore};
    }
    
    public static String determineSkinType(int[] scores) {
        int oily = scores[0];
        int dry = scores[1];
        int sensitive = scores[2];
        int acne = scores[3];
        
        // Check for combination skin
        if (oily >= 3 && dry >= 3) {
            return "Combination Skin";
        }
        
        // Check for sensitive acne-prone
        if (sensitive >= 3 && acne >= 3) {
            return "Sensitive Acne-Prone Skin";
        }
        
        // Find dominant type
        if (oily >= dry && oily >= sensitive && oily >= acne) {
            return "Oily Skin";
        } else if (dry >= oily && dry >= sensitive && dry >= acne) {
            return "Dry Skin";
        } else if (sensitive >= oily && sensitive >= dry && sensitive >= acne) {
            return "Sensitive Skin";
        } else if (acne >= oily && acne >= dry && acne >= sensitive) {
            return "Acne-Prone Skin";
        }
        
        return "Normal Skin";
    }
    
    public static void displayResults(int[] scores, String skinType) {
        String[] characteristics = {"Oily", "Dry", "Sensitive", "Acne"};
        
        System.out.println("\n========================================");
        System.out.println("         YOUR SKIN PROFILE");
        System.out.println("========================================\n");
        
        System.out.println("Analysis Results:");
        System.out.println("-----------------");
        
        for (int i = 0; i < scores.length; i++) {
            int percentage = scores[i] * 25;
            System.out.print(characteristics[i] + " Score:    ");
            
            int bars = scores[i];
            for (int j = 0; j < bars; j++) {
                System.out.print("████");
            }
            System.out.println(" " + percentage + "%");
        }
        
        System.out.println("\nDominant Skin Type: " + skinType.toUpperCase());
    }
    
    public static void provideRecommendations(String skinType) {
        System.out.println("\n========================================");
        System.out.println("      SKINCARE RECOMMENDATIONS");
        System.out.println("========================================\n");
        
        switch (skinType) {
            case "Oily Skin":
                System.out.println("✓ Use gel-based or foaming cleansers");
                System.out.println("✓ Apply oil-free, non-comedogenic moisturizers");
                System.out.println("✓ Use niacinamide to control sebum");
                System.out.println("✓ Exfoliate 2-3 times weekly with salicylic acid");
                System.out.println("✓ Avoid heavy creams and oils");
                break;
                
            case "Dry Skin":
                System.out.println("✓ Use cream-based or milky cleansers");
                System.out.println("✓ Apply rich, hydrating moisturizers with ceramides");
                System.out.println("✓ Look for ingredients like hyaluronic acid and glycerin");
                System.out.println("✓ Avoid harsh soaps and hot water");
                System.out.println("✓ Use a humidifier in dry environments");
                break;
                
            case "Sensitive Skin":
                System.out.println("✓ Use fragrance-free, hypoallergenic products");
                System.out.println("✓ Patch test new products before full application");
                System.out.println("✓ Look for soothing ingredients like aloe and centella");
                System.out.println("✓ Avoid physical exfoliants and strong acids");
                System.out.println("✓ Protect skin with mineral sunscreen");
                break;
                
            case "Acne-Prone Skin":
                System.out.println("✓ Use salicylic acid or benzoyl peroxide cleansers");
                System.out.println("✓ Apply non-comedogenic, lightweight moisturizers");
                System.out.println("✓ Avoid touching your face throughout the day");
                System.out.println("✓ Change pillowcases frequently");
                System.out.println("✓ Consider retinoids for long-term management");
                break;
                
            case "Combination Skin":
                System.out.println("✓ Use gentle, pH-balanced cleansers");
                System.out.println("✓ Apply lightweight moisturizer everywhere");
                System.out.println("✓ Use oil-control products on T-zone only");
                System.out.println("✓ Exfoliate 2 times per week");
                System.out.println("✓ Consider multi-masking for different areas");
                break;
                
            case "Sensitive Acne-Prone Skin":
                System.out.println("✓ Use gentle, fragrance-free acne treatments");
                System.out.println("✓ Look for azelaic acid as a gentle alternative");
                System.out.println("✓ Moisturize thoroughly to maintain barrier");
                System.out.println("✓ Avoid over-exfoliating and harsh ingredients");
                System.out.println("✓ Consult a dermatologist for persistent acne");
                break;
                
            default:
                System.out.println("✓ Maintain a consistent basic routine");
                System.out.println("✓ Use gentle cleanser and moisturizer daily");
                System.out.println("✓ Apply sunscreen every morning");
                System.out.println("✓ Stay hydrated and eat a balanced diet");
                System.out.println("✓ Get adequate sleep for skin repair");
        }
    }
    
    public static void displayThankYou() {
        System.out.println("\n========================================");
        System.out.println("    Thank you for using SkinSense!");
        System.out.println("    Take care of your skin, it's the");
        System.out.println("    only one you'll ever have.");
        System.out.println("========================================");
    }
}
