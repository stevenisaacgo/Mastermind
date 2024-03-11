import java.util.*;

public class App {
    static Scanner sc = new Scanner(System.in);
    private static int min = 2;
    private static int max = 8;
    private static int longCombinacion = 4;
    private static String combinacionfinal = "";
    static Integer numJugadas = 0;
    static Integer numJugada = 0;
    static String jugada;
    private static List<Integer> numbers = new ArrayList<>();

    public static boolean checkInput(String input) {
        List<Character> seen = new ArrayList<>();

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

    public static String checkCombinacion(String combinacioString) {

        StringBuilder stringCombinacion = new StringBuilder();

        List<Character> arrayJugada = new ArrayList<>();
        List<Character> arrayCombinacion = new ArrayList<>();

        for (int i = 0; i < combinacionfinal.length(); i++) {
            char chComb = combinacionfinal.charAt(i);
            arrayCombinacion.add(chComb);
        }

        for (int i = 0; i < combinacioString.length(); i++) {
            char chJugada = combinacioString.charAt(i);
            arrayJugada.add(chJugada);
        }

        for (int i = 0; i < arrayJugada.size(); i++) {
            if (arrayJugada.get(i).equals(arrayCombinacion.get(i))) {
                stringCombinacion.append("X");
            } else {
                if (arrayCombinacion.contains(arrayJugada.get(i))) {
                    stringCombinacion.append("O");
                }
            }
        }

        return stringCombinacion.toString();
    }

    public static void verificarJugada(String jugada) {
        while (!jugada.equals(combinacionfinal) && numJugada < numJugadas) {
            String mensajeFinal = checkCombinacion(jugada);
            System.out.println(" Jugada " + numJugada + ": " + jugada + " " + mensajeFinal);
            System.out.printf("introduce nueva combinación: ");
            jugada = sc.next();
            while (checkInput(jugada) == false) {
                System.out.printf("Porfavor introduce correctamente la combinación de 4 digitos: ");
                jugada = sc.nextLine();
            }
            numJugada++;
        }
        String mensajeFinal = checkCombinacion(jugada);
        System.out.println(" Jugada " + numJugada + ": " + jugada + " " + mensajeFinal);
        if (numJugada < numJugadas) {
            System.out.println("Has ganado!!");
        } else {
            System.out.println("Has perdido la combinación correcta era: " + combinacionfinal);
        }

    }

    public static String generaCSecreta() {
        String combinacion = "";
        numbers.clear();
        for (int i = min; i <= max; i++) {
            numbers.add(i);
        }

        for (int i = 0; i < longCombinacion; i++) {

            Random rndGen = new Random();

            int rndIndex = rndGen.nextInt(numbers.size());

            int rndNumber = numbers.get(rndIndex);

            numbers.remove(rndIndex);

            combinacion = combinacion + Integer.toString(rndNumber);

        }

        return combinacion;
    }

    public static void pedirJugada() {
        System.out.printf("Bienvenido a Mastermind! Porfavor introduzca una combinación: ");
        jugada = sc.next();

        while (checkInput(jugada) == false) {
            System.out.printf("Porfavor introduce correctamente la combinación de 4 digitos: ");
            jugada = sc.nextLine();
        }

        verificarJugada(jugada);

    }

    public static void IniciarJuego() {
        combinacionfinal = generaCSecreta();
        System.out.println("Combinación final: " + combinacionfinal);

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