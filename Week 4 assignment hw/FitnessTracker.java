class Workout 
{
    String activityName;
    int durationInMinutes;
    int caloriesBurned;

    // 1. Default constructor  "Walking", 30 mins, 100 calories
    Workout() {
        this.activityName = "Walking";
        this.durationInMinutes = 30;
        this.caloriesBurned = 100;
    }

    // 2. Constructor with activity name  assigns default duration
    Workout(String activityName) {
        this.activityName = activityName;
        this.durationInMinutes = 30; // default
        this.caloriesBurned = durationInMinutes * 5;
    }

    // 3. Constructor with activity and duration calculate caloriesBurned = duration  5
    Workout(String activityName, int durationInMinutes) {
        this.activityName = activityName;
        this.durationInMinutes = durationInMinutes;
        this.caloriesBurned = durationInMinutes * 5; // calorie calculation
    }

    // Display workout details
    void displayWorkout() {
        System.out.println(" Workout Details:");
        System.out.println("Activity: " + activityName);
        System.out.println("Duration: " + durationInMinutes + " mins");
        System.out.println("Calories Burned: " + caloriesBurned + " kcal");
        System.out.println("---------------------------------");
    }
}

public class FitnessTracker {
    public static void main(String[] args) {
        Workout w1 = new Workout(); // default
        Workout w2 = new Workout("Cycling"); // activity only
        Workout w3 = new Workout("Running", 60); // activity + duration

        w1.displayWorkout();
        w2.displayWorkout();
        w3.displayWorkout();
    }
}
