import java.io.*;
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

    //iterator
    public void add(memberPlayer player)
    {
        String formattedFirstName = capitalizeFirstAndLastName(player.getFirstName());
        String formattedLastName = capitalizeFirstAndLastName(player.getLastName());

        // Update the player's name with the formatted version
        player.setFirstName(formattedFirstName);
        player.setLastName(formattedLastName);

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

    public void delete(String fName, String lName) {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }

        // Check if the first node matches the player
        if (head.player.getFirstName().equals(fName) && head.player.getLastName().equals(lName)) {
            head = head.next; // Set head to the next node
            System.out.println("Player deleted: " + fName + " " + lName);
            return;
        }

        Node current = head;
        Node previous = null;

        // Traverse the list to find the player to delete
        while (current != null) {
            if (current.player.getFirstName().equals(fName) && current.player.getLastName().equals(lName)) {
                previous.next = current.next; // Skip the node to delete it
                System.out.println("Player deleted: " + fName + " " + lName);
                return;
            }
            previous = current;  // Move previous to current
            current = current.next;  // Move current to the next node
        }

        // If player was not found
        System.out.println("Player not found");
    }


//    public boolean validatePlayer(String name)
//    {
//        String fName = memberPlayer.getfirstName();
//        if (name == fName)
//        {
//            return true;
//        }
//        return false;
//
//    }

    public void addRandomMembers()
    {
        String[] firstNames = {"Hans", "Mark", "Markus", "Brian", "Tom", "Peter", "John", "Liam", "Lucas", "Anton"};
        String[] lastNames = {"Hansen", "Jensen", "Brown", "Smith", "Johnson", "Williams", "Taylor", "Miller", "Davis", "Moore"};
        String[] middleNames = {"A", "J", "W", "R", "D", "T", "Z", "U", "Q", "L"};

        for (int i = 0; i < 30; i++)
        {
            String firstName = firstNames[random.nextInt(firstNames.length)];
            String lastName = lastNames[random.nextInt(lastNames.length)];
            String fullName = firstName + " " + lastName;
            int age = random.nextInt(26) + 10;
            int team = random.nextInt(2) + 1;

            // Check if the full name already exists
            Node current = head;
            boolean isDuplicate = false;

            // Traverse the linked list to check for duplicates
            while (current != null)
            {
                if (current.player.getFirstName().equals(firstName) && current.player.getLastName().equals(lastName))
                {
                    isDuplicate = true;
                    break;
                }
                current = current.next;
            }

            // If duplicate, add a middle name
            if (isDuplicate)
            {
                String middleName = middleNames[random.nextInt(middleNames.length)];
                firstName = firstName + " " + middleName;  // Create a new first name with the middle name
            }

            // Create the player with firstName and lastName (now potentially including the middle name)
            memberPlayer player = new memberPlayer(firstName, lastName, age, team);

            // Add the player to the linked list
            add(player);
        }
    }

    public void findPlayer(String firstName, String lastName)
    {
        String formattedFirstName = capitalizeFirstAndLastName(firstName.trim());
        String formattedLastName = capitalizeFirstAndLastName(lastName.trim());

        Node current = head;
        boolean found = false;

        while (current != null)
        {
            if (current.player.getFirstName().equals(firstName) &&
                    current.player.getLastName().equals(lastName))
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

    public void findDivision(int division)
    {
        int division1 = 1;
        int division2 = 2;

        Node current = head;
        boolean found = false;


        while (current != null)
        {
            if (current.player.getTeam() == division)
            {
                found = true;
                System.out.println(
                        "Player found: " + current.player.getFirstName() + " " +
                        current.player.getLastName() +
                        ", Age: " + current.player.getAge() +
                        ", Division: " + current.player.getTeam());
            }
            current = current.next;
        }
        if (!found)
        {
            System.out.println("No player found");
        }
    }

    public void loadContactFromFile(String filename) {
        if (!filename.endsWith(".txt")) {
            filename += ".txt";  // Append .txt if not present
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Assuming data is in the format: Name: <firstName> <lastName>, Age: <age>, Division: <team>
                String[] parts = line.split(", ");
                if (parts.length >= 3) {  // Checking if data has enough parts
                    // Extracting name, age, and division
                    String name = parts[0].replace("Name: ", "").trim();
                    String[] nameParts = name.split(" ");  // Split into first and last name
                    String firstName = nameParts[0];
                    String lastName = nameParts.length > 1 ? nameParts[1] : "";  // Handle case with no last name
                    int age = Integer.parseInt(parts[1].replace("Age: ", "").trim());
                    int division = Integer.parseInt(parts[2].replace("Division: ", "").trim());

                    // Create a new memberPlayer object with extracted data
                    memberPlayer player = new memberPlayer(firstName, lastName, age, division);
                    // Add the new player to the playerListe
                    playerListe.add(player);
                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void saveToFile(String filename) {
        if (!filename.endsWith(".txt")) {
            filename += ".txt";
        }

        // Write to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            Node current = head; // Start from the head of the linked list
            while (current != null) {
                memberPlayer player = current.player;

                String line = "Name: " + player.getFirstName() + " " + player.getLastName() +
                        ", Age: " + player.getAge() + ", Division: " + player.getTeam();
                writer.write(line);
                writer.newLine();
                current = current.next; // Move to the next player
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }



    //Merge Sort for linked LIst
    public void sorterSpillere()
    {
        head = mergeSort(head);
    }

    //merge Sort for Linked List
    private Node mergeSort(Node head)
    {
        if (head == null || head.next == null)
        {
            return head;
        }

        Node middle = getMiddle(head);
        Node nextOfMiddle = middle.next;
        middle.next = null;

        Node left = mergeSort(head);
        Node right = mergeSort(nextOfMiddle);

        return sortedMerge(left, right);
    }

    private Node getMiddle(Node head)
    {
        if (head == null)
            return head;
        Node slow = head, fast = head.next;
        while (fast != null && fast.next != null)
        {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private Node sortedMerge(Node a, Node b)
    {
        if (a == null) return b;
        if (b == null) return a;

        Node result;
        if (a.player.getLastName().compareToIgnoreCase(b.player.getLastName()) <= 0)
        {
            result = a;
            result.next = sortedMerge(a.next, b);
        } else
        {
            result = b;
            result.next = sortedMerge(a, b.next);
        }
        return result;
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

        return result.toString();
    }


}
