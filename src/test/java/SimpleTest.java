import java.util.StringTokenizer;

public class SimpleTest {

    public static void main(String[] args) {

        String name = "kamar baraka";
        String[] lnames = name.split(" ");
        System.out.println(lnames[0] + "... " + lnames[1]);

        StringTokenizer nameToken = new StringTokenizer(name);
        String firstname = nameToken.nextToken();
        String lastname = nameToken.nextToken();
        System.out.println(firstname + "... " + lastname);
    }
}
