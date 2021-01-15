
public class Details{
    private String name;
    private Person alice;
    private Person bob;
    private String plainText;
    private String cipherText;

    public Details(){

    }

    public void start(){
        // Initialise Persons
        alice.start();
        bob.start();

        if (alice.modulus.compareTo(bob.modulus) == -1)
    		plainText = Person.decode(alice.encrypt(Person.decode(bob.decrypt(cipherText))));
    	else
    		plainText = Person.decode(bob.decrypt(Person.decode(alice.encrypt(cipherText))));
    }

}
