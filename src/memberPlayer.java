public class memberPlayer
{

    private String firstName;
    private String lastName;
    private int age;
    private int team;
 //   private String gender;

    public memberPlayer()
    {}

    public memberPlayer(String firstName, String lastName, int age, int team)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.team = team;
    //    this.gender = gender;

    }


    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public int getAge()
    {
        return age;
    }

    public int getTeam()
    {
        return team;
    }

    @Override
        public String toString() {
        return firstName + " " +
                lastName + ", age: " +
                age + ", division: " +
                team + "\n";
        //", gender: " +
           //     gender + "\n";
    }

}
