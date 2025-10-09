class hwProblem2_Tool {
    private String name = "Generic Tool";  // only inside Tool
    protected String type = "Hand Tool";   // accessible in child
    public int weight = 5;                 // accessible everywhere

    // Getter for private field
    public String getName() {
        return name;
    }
}

class hwProblem2_Hammer extends hwProblem2_Tool {
    void showAccess() {
        // System.out.println(name); Not allowed (private)
        System.out.println("Using getter: " + getName());
        System.out.println("Protected field: " + type);   
        System.out.println("Public field: " + weight);    
    }
}

public class hwProblem2_Main {
    public static void main(String[] args) {
        hwProblem2_Hammer h = new hwProblem2_Hammer();
        h.showAccess();

        // Outside class
        // System.out.println(h.name);
        // System.out.println(h.type); 
        System.out.println("Public field from outside: " + h.weight); 
    }
}
