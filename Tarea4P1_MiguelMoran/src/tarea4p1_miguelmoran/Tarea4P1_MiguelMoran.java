/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tarea4p1_miguelmoran;

import java.util.Scanner;
import java.util.Random;

/**
 *
 * @author flash
 */
public class Tarea4P1_MiguelMoran {

    static Scanner sc = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        boolean seguir = true;
        while (seguir) {
            System.out.println("1. Battleship");
            System.out.println("2. Minesweeper");
            System.out.println("3. Salir");
            System.out.print("Elija una opcion: ");
            int opcion = sc.nextInt();
            switch (opcion) {
                case 1 -> {
                    String[][] tablero1 = new String[6][5];
                    String[][] tablero2 = new String[6][5];
                    tablero1 = Llenado(tablero1);
                    tablero2 = Llenado(tablero2);
                    for (int i = 0; i < 3; i++) {
                        tablero1[i][0] = " * ";
                    }
                    tablero1[1][2] = " * ";
                    tablero1[1][3] = " * ";
                    for (int i = 3; i < 6; i++) {
                        tablero1[i][4] = " * ";
                    }
                    for (int i = 1; i < 4; i++) {
                        tablero2[i][3] = " * ";
                    }
                    for (int i = 3; i < 6; i++) {
                        tablero2[i][1] = " * ";
                    }
                    tablero2[5][3] = " * ";
                    tablero2[5][4] = " * ";
                    String[][] void1 = new String[6][5];
                    String[][] void2 = new String[6][5];
                    void1 = Llenado(void1);
                    void2 = Llenado(void2);
                    int damageP1 = 0;
                    int damageP2 = 0;
                    int x = 0;
                    int y = 0;
                    while (damageP1 < 3 && damageP2 < 3) {
                        boolean seguir1 = true;
                        while (seguir1) {
                            System.out.print("Ingrese las coordenadas del ataque (Jugador 1): ");
                            String coordenadas = sc.next();
                            int x1 = Integer.parseInt(String.valueOf(coordenadas.charAt(0)));
                            int y1 = Integer.parseInt(String.valueOf(coordenadas.charAt(1)));
                            x = x1 - 1;
                            y = y1 - 1;
                            if (x >= 0 && x < 5 && y >= 0 && y < 6) {
                                seguir1 = false;
                            } else {
                                System.out.println("Las coodenadas ingresadas no son validas");
                            }
                        }
                        damageP2 = Ataque(tablero2, void2, x, y, damageP2);
                        Impresion(void2);
                        if (damageP2 < 3) {
                            boolean seguir2 = true;
                            while (seguir2) {
                                System.out.print("Ingrese las coordenadas del ataque (Jugador 2): ");
                                String coordenadas = sc.next();
                                int x1 = Integer.parseInt(String.valueOf(coordenadas.charAt(0)));
                                int y1 = Integer.parseInt(String.valueOf(coordenadas.charAt(1)));
                                x = x1 - 1;
                                y = y1 - 1;
                                if (x >= 0 && x < 5 && y >= 0 && y < 6) {
                                    seguir2 = false;
                                } else {
                                    System.out.println("Las coodenadas ingresadas no son validas");
                                }
                            }
                            damageP1 = Ataque(tablero1, void1, x, y, damageP1);
                            Impresion(void1);
                        }
                    }
                    if (damageP2 == 3) {
                        System.out.println("El Jugador 1 ha ganado. Felicidades!");
                    }
                    if (damageP1 == 3) {
                        System.out.println("El Jugador 2 ha ganado. Felicidades!");
                    }
                    System.out.println("Tablero del Jugador 1:");
                    Impresion(tablero1);
                    System.out.println("Tablero del Jugador 2:");
                    Impresion(tablero2);
                }
                case 2 -> {
                    String[][] tablero = new String[6][5];
                    tablero = Llenado(tablero);
                    Minas(tablero);
                    String[][] tabvoid = new String[6][5];
                    tabvoid = Llenado(tabvoid);
                    int x = 0;
                    int y = 0;
                    boolean seguir1 = true;
                    while(seguir1){
                        Impresion(tabvoid);
                        boolean seguir2 = true;
                        while (seguir2) {
                            System.out.print("Ingrese las coordenadas que desea investigar: ");
                            String coordenadas = sc.next();
                            int x1 = Integer.parseInt(String.valueOf(coordenadas.charAt(0)));
                            int y1 = Integer.parseInt(String.valueOf(coordenadas.charAt(1)));
                            x = x1 - 1;
                            y = y1 - 1;
                            if (x >= 0 && x < 5 && y >= 0 && y < 6) {
                                seguir2 = false;
                            } else {
                                System.out.println("Las coodenadas ingresadas no son validas");
                            }
                        }
                        seguir1 = Investigacion(tablero, tabvoid, x, y);
                    }
                    Impresion(tablero);
                    System.out.println("GAME OVER");
                }
                case 3 -> {
                    seguir = false;
                }
                default -> {
                    System.out.println("Elija un opcion valida");
                }
            }
        }
    }

    public static String[][] Llenado(String[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                matriz[i][j] = "   ";
            }
        }
        return matriz;
    }

    public static void Impresion(String[][] matriz) {
        System.out.println("---------------------");
        for (int i = 0; i < matriz.length; i++) {
            System.out.print("|");
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j] + "|");
            }
            System.out.println("");
            System.out.println("---------------------");
        }
    }

    public static int Ataque(String[][] tablero, String[][] tabvoid, int x, int y, int damage) {
        if (tablero[y][x].equals(" * ")) {
            tabvoid[y][x] = " O ";
            tablero[y][x] = " O ";
            System.out.println("Un barco ha sido dañado!");
            damage += 1;
        } else if(tablero[y][x].equals("   ")){
            tabvoid[y][x] = " x ";
            tablero[y][x] = " x ";
            System.out.println("Bomba al agua!");
        } else {
            System.out.println("Ese espacio ya fue sido atacado");
        }
        return damage;
    }
    
    public static String[][] Minas(String[][] tablero){
        Random rng = new Random();
        for (int i = 0; i < 5; i++) {
            int x = rng.nextInt(5);
            int y = rng.nextInt(6);
            if(tablero[y][x].equals(" * ")){
                i -= 1;
            } else {
                tablero[y][x] = " * ";
            }
        }
        return tablero;
    }
    
    public static boolean Investigacion(String[][] tablero, String[][] tabvoid, int x, int y) {
        boolean seguir = true;
        if (tablero[y][x].equals(" * ")) {
            tabvoid[y][x] = " O ";
            tablero[y][x] = " O ";
            System.out.println("BOOM!");
            seguir = false;
        } else if(tablero[y][x].equals("   ")){
            tabvoid[y][x] = " x ";
            tablero[y][x] = " x ";
            System.out.println("El espacio es seguro");
        } else {
            System.out.println("Ese espacio ya fue investigado");
        }
        return seguir;
    }
}
