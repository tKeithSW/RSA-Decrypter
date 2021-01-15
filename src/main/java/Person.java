import java.math.BigInteger;

public class Person {
    BigInteger p;
    BigInteger q;
    BigInteger r;
    BigInteger modulus; // n
    BigInteger privateKey;
    BigInteger publicKey;

    public Person(){
        
    }

	public void start() {
        System.out.println("Beginning factorising for modulus:" + modulus.toString());
        long startTime = System.nanoTime();

        BigInteger factors[] = {BigInteger.ZERO};
        cryptoUtility.getPrimeFactor(modulus, factors);

        if(factors[0] != BigInteger.ZERO){
            p = factors[0];
            q = modulus.divide(p);
            System.out.println("p: " + p.toString());
            System.out.println("q: " + q.toString());
            System.out.println("Time taken: " + (System.nanoTime() - startTime)/1000000000 + "s" );
        }
        else
            System.out.println("No factors found");

        System.out.println("Done!");
        System.out.println();

        //r = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
        r = p.subtract(BigInteger.ONE);
		r = r.multiply(q.subtract(BigInteger.ONE));

        privateKey = publicKey.modInverse(r);
    }
    
    public static BigInteger encode(final String text) { // Encode text as number
        return new BigInteger(text, Character.MAX_RADIX);
    }

    public BigInteger encrypt(String text){
        BigInteger encodedText = encode(text);
        BigInteger cipherText = encodedText.modPow(publicKey, modulus); // c = m^e mod n
        return cipherText;
    }

    public static String decode(final BigInteger number) { // Decode number as text
        return number.toString(Character.MAX_RADIX);
    }

    public BigInteger decrypt(String cipherText) {
        BigInteger encodedCipText = encode(cipherText);
        BigInteger encodedText = encodedCipText.modPow(privateKey, modulus); // p = c^d mod n;
        return encodedText;
    }

}
