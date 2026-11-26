import java.math.BigInteger;
import java.util.Arrays;

public class Main {
    private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmno";
    private static final BigInteger BASE = BigInteger.valueOf(55);

    public static String encode(byte[] data) {
        if (data == null || data.length == 0) return "";
        BigInteger value = new BigInteger(1, data);
        StringBuilder sb = new StringBuilder();
        while (value.compareTo(BigInteger.ZERO) > 0) {
            BigInteger[] dr = value.divideAndRemainder(BASE);
            sb.append(ALPHABET.charAt(dr[1].intValue()));
            value = dr[0];
        }
        for (byte b : data) {
            if (b == 0) sb.append(ALPHABET.charAt(0));
            else break;
        }
        return sb.reverse().toString();
    }

    public static byte[] decode(String encoded) {
        if (encoded == null || encoded.isEmpty()) return new byte[0];
        BigInteger value = BigInteger.ZERO;
        for (char c : encoded.toCharArray()) {
            int index = ALPHABET.indexOf(c);
            if (index == -1) throw new IllegalArgumentException("Invalid char: " + c);
            value = value.multiply(BASE).add(BigInteger.valueOf(index));
        }
        byte[] bytes = value.toByteArray();
        if (bytes.length > 1 && bytes[0] == 0) {
            return Arrays.copyOfRange(bytes, 1, bytes.length);
        }
        return bytes;
    }

    public static void main(String[] args) {
        String original = "UniqueEncodedString";
        String encoded = encode(original.getBytes());
        System.out.println("Encoded: " + encoded);
        System.out.println("Decoded: " + new String(decode(encoded)));
    }
}
