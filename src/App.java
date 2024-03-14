import java.util.*;

public class App {
    static Scanner sc = new Scanner(System.in);
    private static int min = 2;
    private static int max = 8;
    private static int longCombinacion = 4;
    static Integer numJugadas = 0;
    static Integer numJugada = 0;
    static String jugada;
    private static byte[] numbersCSecreta = new byte[longCombinacion];
    private static boolean gameDone;

    public static boolean checkInput(String input) {
        ArrayList<Character> seen = new ArrayList<>();

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (input.length() != 4) {
                return false;
            } else {

                if (Character.isDigit(ch)) {
                    if (seen.contains(ch)) {
                        return false;
                    }
                    seen.add(ch);
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean contains(byte[] array, byte element) {
        for (byte b : array) {
            if (b == element) {
                return true;
            }
        }
        return false;
    }

    public static String checkCombinacion(byte[] combinacioString) {

        StringBuilder stringCombinacion = new StringBuilder();

        for (int i = 0; i < combinacioString.length; i++) {
            // comparo las array de bytes
            if (combinacioString[i] == numbersCSecreta[i]) {
                stringCombinacion.append("X");
            } else if (contains(numbersCSecreta, combinacioString[i])) {
                stringCombinacion.append("O");
            }

        }

        if (stringCombinacion.equals("XXXX")) {
            gameDone = true;
        }

        // ArrayList<Character> arrayJugada = new ArrayList<>();
        // ArrayList<Character> arrayCombinacion = new ArrayList<>();

        // for (int i = 0; i < combinacionfinal.length(); i++) {
        // char chComb = combinacionfinal.charAt(i);
        // arrayCombinacion.add(chComb);
        // }

        // for (int i = 0; i < combinacioString.length(); i++) {
        // char chJugada = combinacioString.charAt(i);
        // arrayJugada.add(chJugada);
        // }

        // for (int i = 0; i < arrayJugada.size(); i++) {
        // if (arrayJugada.get(i).equals(arrayCombinacion.get(i))) {
        // stringCombinacion.append("X");
        // } else {
        // if (arrayCombinacion.contains(arrayJugada.get(i))) {
        // stringCombinacion.append("O");
        // }
        // }
        // }

        return stringCombinacion.toString();
    }

    public static byte[] convertStringToByteArray(String str) {
        int[] intArray = new int[str.length()];

        // convierto de string a int array

        for (int i = 0; i < str.length(); i++) {
            intArray[i] = Character.getNumericValue(str.charAt(i));
        }

        // convierto de int array a byte array
        byte[] byteArray = new byte[intArray.length];
        for (int i = 0; i < intArray.length; i++) {
            byteArray[i] = (byte) intArray[i];
        }

        return byteArray;
    }

    public static void verificarJugada(String jugada) {

        byte[] jugadaByte = convertStringToByteArray(jugada);

        while (gameDone == false && numJugada < numJugadas) {

            String mensajeFinal = checkCombinacion(jugadaByte);
            System.out.println(" Jugada " + numJugada + ": " + jugada + " " + mensajeFinal);
            System.out.printf("introduce nueva combinación: ");
            jugada = sc.next();
            jugadaByte = convertStringToByteArray(jugada);
            while (checkInput(jugada) == false) {
                System.out.printf("Porfavor introduce correctamente la combinación de 4 digitos: ");
                jugada = sc.next();
                jugadaByte = convertStringToByteArray(jugada);
            }
            numJugada++;
        }
        String mensajeFinal = checkCombinacion(jugadaByte);
        System.out.println(" Jugada " + numJugada + ": " + jugada + " " +
                mensajeFinal);
        if (numJugada < numJugadas) {
            System.out.println("Has ganado!!");
        } else {
            System.out.println("Has perdido la combinación correcta era: " +
                    numbersCSecreta);

        }

    }

    public static void generaCSecreta() {

        Set<Byte> uniqueSet = new HashSet<>();

        int index = 0;
        while (uniqueSet.size() < longCombinacion) {
            byte newByte = (byte) (Math.random() * (max - min + 1) + min);
            if (!uniqueSet.contains(newByte)) {
                numbersCSecreta[index] = newByte;
                uniqueSet.add(newByte);
                index++;
            }
        }
    }

    public static void pedirJugada() {
        System.out.printf("Bienvenido a Mastermind! Porfavor introduzca una combinación: ");
        jugada = sc.next();

        while (checkInput(jugada) == false) {
            System.out.printf("Porfavor introduce correctamente la combinación de 4 digitos: ");
            jugada = sc.next();
        }

        verificarJugada(jugada);

    }

    public static void IniciarJuego() {
        generaCSecreta();
        System.out.println("Combinación final: " + numbersCSecreta);

        System.out.printf("Introducir número maximo de intentos o jugadas: ");
        numJugadas = sc.nextInt();
        while (numJugadas < 6 || numJugadas > 20) {
            System.out.printf("Introducir número entre 6 y 20: ");
            numJugadas = sc.nextInt();
        }
        pedirJugada();
    }

    public static void main(String[] args) throws Exception {

        IniciarJuego();

    }
}