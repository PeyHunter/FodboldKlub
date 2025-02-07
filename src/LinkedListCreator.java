import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;


public class LinkedListCreator
{


    Node head;
    Random random = new Random();

    LinkedList<memberPlayer> playerListe = new LinkedList<>();

    public LinkedListCreator()
    {
    }

    public LinkedListCreator(LinkedList<memberPlayer> playerListe)
    {
        this.playerListe = playerListe;
    }


    public void add(memberPlayer player)
    {
        Node newNode = new Node(player);
        if (head == null)
        {
            head = newNode;
        } else
        {
            Node current = head;
            while (current.next != null)
            {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    public void addRandomMembers()
    {
        String[] firstNames = {"Hans", "Mark", "Markus", "Brian", "Tom", "Peter", "John", "Liam", "Lucas", "Anton"};
        String[] lastNames = {"Hansen", "Jensen", "Brown", "Smith", "Johnson", "Williams", "Taylor", "Miller", "Davis", "Moore"};

        for (int i = 0; i < 30; i++)
        {
            String firstName = firstNames[random.nextInt(firstNames.length)];
            String lastName = lastNames[random.nextInt(firstNames.length)];
            int age = random.nextInt(26) + 10;
            int team = random.nextInt(2) + 1;

            memberPlayer player = new memberPlayer(firstName, lastName, age, team);
            add(player);
        }
    }


    public void findPlayer(String firstName)
    {
        String formattedNames = capitalizeFirstAndLastName(firstName.trim());
        Node current = head;
        boolean found = false;

        while (current != null)
        {
            if (current.player.getFirstName().equals(firstName))
            {
                found = true;
                System.out.println("Player found: " + current.player.getFirstName() + " " + current.player.getLastName());
            }
            current = current.next;
        }
        if (!found)
        {
        System.out.println("No player found");
        }
    }


    public void searchForPlayer(String firstName)
    {
        Node current = head;
        while (current != null)
        {
            if (current.player.getFirstName().equals(firstName))
            {
                System.out.println("Player found: " + current.player.getFirstName() + " " + current.player.getLastName());
                return;
            }
            current = current.next;
        }
        System.out.println("Player not found");
    }


    public String capitalizeFirstAndLastName(String name)
    {
        if (name == null || name.isEmpty())
        {
            return name;
        }
        String[] parts = name.split(" ");

        for (int i = 0; i < parts.length; i++)
        {
            parts[i] = capitalizeFirstLetter(parts[i]);
        }
        return String.join(" ", parts);
    }

    public String capitalizeFirstLetter(String name)
    {
        if (name == null || name.isEmpty())
        {
            return name;
        }
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }


    @Override
    public String toString()
    {
        StringBuilder result = new StringBuilder();
        Node current = head;
        while (current != null)
        {
            result.append(current.player);
            current = current.next;
        }
        result.append("null");
        return result.toString();
    }


}
