import java.math.BigInteger;
import java.security.SecureRandom; // More crypto secure ver of Random

public class cryptoUtility{
    private static BigInteger ZERO = new BigInteger("0");
    private static BigInteger ONE = new BigInteger("1");
    private static BigInteger TWO = new BigInteger("2");
    private static SecureRandom random = new SecureRandom();

    public static void getPrimeFactor(BigInteger n, BigInteger factors[]){
        if (n.compareTo(ONE) == 0)
            return;
        if (n.isProbablePrime(30)){
            factors[0] = n;
            return;
        }
        BigInteger divisor = pollardRho(n);
        getPrimeFactor(divisor, factors);
        getPrimeFactor(n.divide(divisor), factors);
    }

    public static BigInteger pollardRho(BigInteger n){
        BigInteger divisor;
        BigInteger x = new BigInteger(n.bitLength(), random);
        BigInteger c = new BigInteger(n.bitLength(), random);
        BigInteger xx = x;
        
        // Check divisiblility by 2
        if(n.mod(TWO).compareTo(ZERO) == 0)
            return TWO;
        do{
            x  =  x.multiply(x).mod(n).add(c).mod(n);
            xx = xx.multiply(xx).mod(n).add(c).mod(n);
            xx = xx.multiply(xx).mod(n).add(c).mod(n);
            divisor = x.subtract(xx).gcd(n);
        } while((divisor.compareTo(ONE)) == 0);

        return divisor;
    }
}