import java.math.BigInteger;

public class Base44Converter {
    private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefgh";
    private static final BigInteger BASE = BigInteger.valueOf(44);

    public static String encode(byte[] input) {
        BigInteger value = new BigInteger(1, input);
        StringBuilder sb = new StringBuilder();
        while (value.compareTo(BigInteger.ZERO) > 0) {
            BigInteger[] quotientAndRemainder = value.divideAndRemainder(BASE);
            sb.append(ALPHABET.charAt(quotientAndRemainder[1].intValue()));
            value = quotientAndRemainder[0];
        }
        return sb.reverse().toString();
    }

    public static byte[] decode(String input) {
        BigInteger value = BigInteger.ZERO;
        for (char c : input.toCharArray()) {
            value = value.multiply(BASE).add(BigInteger.valueOf(ALPHABET.indexOf(c)));
        }
        return value.toByteArray();
    }

    public static void main(String[] args) {
        String data = "Hello World";
        String encoded = encode(data.getBytes());
        System.out.println("Encoded: " + encoded);
        System.out.println("Decoded: " + new String(decode(encoded)));
    }
}

