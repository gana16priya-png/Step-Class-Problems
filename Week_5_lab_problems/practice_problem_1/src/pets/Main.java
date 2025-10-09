package pets;

public class Main {
    public static void main(String[] args) {
        VirtualPet pet = new VirtualPet("Fluffy");
        pet.feedPet("meat");
        pet.playWithPet("fetch");
        System.out.println(pet);

        DragonPet draco = new DragonPet("Smaug", "FireDragon", "Fire Breath");
        System.out.println(draco);

        RobotPet bot = new RobotPet("Robo");
        bot.setBatteryLevel(85);
        System.out.println(bot);
    }
}
