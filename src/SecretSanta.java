import java.util.*;

public class SecretSanta {

    /**
     * The list of people participating in this Secret Santa.
     */
    private final List<String> participants = new ArrayList<>();

    /**
     * A mapping from each participant to the person they should gift.
     */
    private static final Map<String, String> santas = new HashMap<>();

    public static void main(String[] args) {
        SecretSanta secretSanta = new SecretSanta(args);
        System.out.println(secretSanta.getSantas().toString());
    }

    /**
     * Constructor for an instance of SecretSanta.
     *
     * @param participants the list of participants
     */
    public SecretSanta(String[] participants) {
        this.participants.addAll(Arrays.asList(participants));
        assignSantas();
    }

    /**
     * @return a mapping from each participant to the person they should gift
     */
    public Map<String, String> getSantas() {
        return santas;
    }

    /**
     * Assigns every participant a person to gift.
     */
    private void assignSantas() {
        Random random = new Random();
        List<String> toReceive = new ArrayList<>(this.participants);

        // Give every participant a receiver
        for (String participant : this.participants) {
            // Randomly choose a receiver, a participant cannot receive from themself
            boolean removed = toReceive.remove(participant);
            int choice = random.nextInt(toReceive.size());

            // Add mapping of santa to receive to santas
            santas.put(participant, toReceive.get(choice));
            toReceive.remove(choice);

            // If participant was originally in toReceive, add them back
            if (removed) {
                toReceive.add(participant);
            }
        }
    }
}
