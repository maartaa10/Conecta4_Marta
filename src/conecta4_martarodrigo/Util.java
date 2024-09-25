/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conecta4_martarodrigo;
import java.util.Scanner;
/**
 *
 * @author martarodrigo
 */
public class Util {
private static final Scanner teclat = new Scanner(System.in);
public static int llegirInt(String text) {
        int val = 0;
        boolean llegit = false;
        do {
            System.out.println(text);
            if (teclat.hasNextInt()) {
                val = teclat.nextInt();
                llegit = true;
            } else {
                System.out.println("Error");
            }
            teclat.nextLine();
        } while (!llegit);
        return val;
    }

    public static double llegirDouble(String text) {
        double val = 0.00;
        boolean llegit = false;
        do {
            System.out.println(text);
            if (teclat.hasNextDouble()) {
                val = teclat.nextDouble();
                llegit = true;
            } else {
                System.out.println("Error");
            }
            teclat.nextLine();
        } while (!llegit);
        return val;
    }

  public static char demanaSexe(String text) {
    char sexe;
    do {
        System.out.print(text + " (H/D): ");
        sexe = teclat.nextLine().toUpperCase().charAt(0);
    } while (sexe != 'H' && sexe != 'D');
    return sexe;
}



    public static int demanaEdat(String text) {
        System.out.print("Introdueix l'edat: ");
        return teclat.nextInt();
    }

   public static int demanaTempsAnterior(String text) {
    System.out.print("Quant temps has trigat en fer la marato anterior? ");
      return teclat.nextInt();
    }

 public static void main(String[] args) {
        String cadena = llegirString("Introdueix una cadena: ");
        System.out.println("Has introduït: " + cadena);
    }

    public static String llegirString(String text) {
        String input = "";
        boolean llegit = false;

        do {
            System.out.println(text);
            if (teclat.hasNextLine()) {
                input = teclat.nextLine();
                llegit = true;
            } else {
                System.out.println("Error: No és una cadena vàlida.");
                teclat.nextLine(); 
            }
        } while (!llegit);

        return input;
    }
 public static String demanarString(String frase) {
        Scanner teclat = new Scanner(System.in);
        String paraula;

        do {
            System.out.print(frase);
            paraula = teclat.nextLine();
            if ( paraula.length() == 0) {
                System.out.println("Error. Introduzca un string no vacio");
            }
        } while (paraula.length() == 0);

        return paraula;
    }
    
        public static int demanarIntPositiu(String frase) {
        int quant = 0;
        do {
            quant = Util.llegirInt(frase);
            if (quant < 0) {
                System.out.println("Error!!!, Vull un nombre positiu");
            }
        } while (quant < 0);
        return quant;
    }

    public static double demanarDoubleInterval(String frase, double min, double max) {
        double val = 0;
        do {
            val = Util.llegirDouble(frase);
            if (val < min || val > max) {
                System.out.printf("Error!!, Vull un nombre entre %.2f i %.2f\n", min, max);
            }
        } while (val < min || val > max);
        return val;
    }

 public static char demanaChar(String text, String charsValid) {
    Scanner teclat = new Scanner(System.in);
    char lletra = ' ';
    String lect = "";

    // Convertimos el conjunto de letras válidas a mayúsculas
    charsValid = charsValid.toUpperCase();

    while (lect.length() == 0) {
        System.out.print(text + " (" + charsValid + "): ");
        lect = teclat.nextLine().toUpperCase();  // Convertir la entrada a mayúsculas para comparar
        
        if (lect.length() != 1) {
            System.out.println("Error-- una lletra ");
            lect = "";
        } else {
            lletra = lect.charAt(0);
            if (charsValid.indexOf(lletra) == -1) {
                System.out.println("Error-- opció errònia ");
                lect = "";
            }
        }
    }

    return lletra;  // Devolvemos la letra en mayúscula
}

public static void mostraVector(int[] vect, String separador) {
        if (separador.length() == 0) {
            separador = ", ";
        }
        for (int i = 0; i < vect.length; i++) {
            if (i != 0) {
                System.out.print(separador);
            }
            System.out.print(vect[i]);
        }
    }
public static char demanaOpinio(String text) {
    char opinio;
    do {
        System.out.print(text + " ");
        opinio = teclat.nextLine().toUpperCase().charAt(0);
    } while (opinio != 'S' && opinio != 'N');
    return opinio;
}
public static String[] leerYDividirCadena(String mensaje, String separador) {
        System.out.print(mensaje);
        String cadena = teclat.nextLine();
        return cadena.split(separador);
    }
public static String completarCorreoElectronico(String mensaje) {
        System.out.print(mensaje);
        String nombreUsuario = teclat.nextLine();

        if (nombreUsuario.contains("@")) {
            return nombreUsuario;
        } else {
            return nombreUsuario + "@gmail.com";
        }
    }
}












    
