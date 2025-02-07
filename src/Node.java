public class Node
{

    memberPlayer player;
    Node next;

    public Node()
    {}

    public Node(memberPlayer player)
    {
        this.player = player;
        this.next = next;
    }

    @Override
    public String toString()
    {
        StringBuilder result = new StringBuilder();
        Node current = this;
        while (current != null )
        {
            result.append(current.player).append(", ");
            current = current.next;
        }
        result.append("null");
        return result.toString();
    }
}
