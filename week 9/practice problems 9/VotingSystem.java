public class VotingSystem {
    public void processVote(String voterId, String candidate) {
        class VoteValidator {
            boolean validate() {
                return voterId != null && voterId.startsWith("V");
            }
        }

        VoteValidator validator = new VoteValidator();
        if (validator.validate())
            System.out.println("Vote accepted for " + candidate);
        else
            System.out.println("Invalid voter ID!");
    }

    public static void main(String[] args) {
        VotingSystem vs = new VotingSystem();
        vs.processVote("V123", "Alice");
        vs.processVote("123", "Bob");
    }
}
