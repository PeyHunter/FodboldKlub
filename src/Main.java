import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Main
{
    public static void main(String[] args)
    {


        Scanner scanner = new Scanner(System.in);

        LinkedListCreator memberList = new LinkedListCreator();

        memberList.addRandomMembers();
        System.out.println(memberList);
        memberList.loadContactFromFile("members.txt");

        boolean running = true;

        while(running)
        {

            System.out.println("Fodbold Klubbens Spiller Oversigt");
            System.out.println("Menu");
            System.out.println("1. Opret ny spiller");
            System.out.println("2. Søg på spillere");
            System.out.println("3. Se spillere i alphabetisk");
            System.out.println("4. Slet kontakt");
            System.out.println("5. Se liste med division 1 og 2");
            System.out.println("6. Gem kontakter i fil");
            System.out.println("Exit");

            String input = scanner.nextLine();

            int choice;

            try
            {
                choice = Integer.parseInt(input);
            } catch(NumberFormatException e)
            {
                System.out.println("Invalid input - try again");
                continue;
            }

            switch (choice)
            {
                case 1:
                    System.out.println("Enter first name of person");
                    String fname = scanner.nextLine();

                    System.out.println("Enter last name of person");
                    String lname = scanner.nextLine();

                    System.out.println("Enter age");
                    int age = scanner.nextInt();

                    System.out.println("Enter team division (only 1 or 2)");
                    int team = scanner.nextInt();
                    scanner.nextLine();

                    memberPlayer newMember= new memberPlayer(fname.trim(), lname.trim(), age, team);
                    memberList.add(newMember);

                    System.out.println("New member added:");
                    System.out.println(newMember);


                    System.out.println("Saving to file...");
                    memberList.saveToFile("members.txt");
                    break;

                case 2:
                    System.out.println("Søg på spillers fornavn: ");
                    String firstName = scanner.nextLine();
                    System.out.println("Søg på spillers efternavn: ");
                    String lastName = scanner.nextLine();

                    memberList.findPlayer(firstName, lastName);
                    break;

                case 3:

                    memberList.sorterSpillere();
                    System.out.println(memberList);
                    break;

                case 4:

                    System.out.println("First name of person to be deleted (incl. middle name)");
                    String fName = scanner.nextLine();

                    System.out.println("Last name of person to be deleted");
                    String lName = scanner.nextLine();

                    memberList.findPlayer(fName, lName);

                    memberList.delete(fName, lName);
                    System.out.println(memberList);

                    System.out.println(fName + " " + lName + ", has been deleted" );

                    memberList.saveToFile("members.txt");

                    break;

                case 5:
                    System.out.println("Which division do you want to see?");
                    int division = scanner.nextInt();

                    System.out.println("Division list");
                    memberList.findDivision(division);
                    break;


                default:
                    System.out.println("Invalid input - try again");
                    break;


            }
        }










//        memberList.add(new memberPlayer("Hans", "Hansen", 13, 1));
//        memberList.add(new memberPlayer("Tobias", "Tobiasen", 12, 1));
//        memberList.add(new memberPlayer("Peter", "Petersen", 13, 1));

    }


}




//Exercise List, The Football Club
//
//Make a class that represents a memberPlayer of a football club with first name, last name, age, team (first or second).
//Make a main that produces a menu with options to create new players/members as objects,
// list all members/players in a sorted list,
// search for a player,
// delete a player
// search for all the players on the first or second team /
// or within an age interval.


//Create all the methods.
//Make the appropriate files and the code to handle the reading and writing to and from files.
//        Create 30 members/players in random order, you have to variate age (between 10 - 35), first-/second team.



//Test that all the functionality works!
//Make it possible for the user to choose which field the list should be sorted by.

