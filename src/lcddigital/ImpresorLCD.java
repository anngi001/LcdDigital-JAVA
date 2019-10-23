/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lcddigital;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Asus
 */
public class ImpresorLCD {
     private final int[] pivote1;
    private final int[] pivote2;
    private final int[] pivote3;
    private final int[] pivote4;
    private final int[] pivote5;
    private String[][] matrizImprimir;

    static final String CaracterVERTICAL = "|";
    static final String CaracterHORIZONTAL = "-";
    static final String POSICION_X = "X";
    static final String POSICION_Y = "Y";

    
    //String entrada = JOptionPane.showInputDialog("Digite el numero");
    private int size;

   
    private int digFilas;
    private int digColumnas;
    private int filasTotales;
    private int columnasTotales;

       public ImpresorLCD() {
       
        this.pivote1 = new int[2];
        this.pivote2 = new int[2];
        this.pivote3 = new int[2];
        this.pivote4 = new int[2];
        this.pivote5 = new int[2];
    }
        private void adicionarLinea(String[][] matriz, int[] punto, String posFija,
            int size, String caracter) {

        if (posFija.equalsIgnoreCase(POSICION_X)) 
        {
            for (int y = 1; y <= size; y++) 
            {
                int valor = punto[1] + y;
                matriz[punto[0]][valor] = caracter;
            }
        } 
        else 
        {
            for (int i = 1; i <= size; i++) 
            {
                int valor = punto[0] + i;
                matriz[valor][punto[1]] = caracter;
            }
        }
    }

    /**
     *
     * Metodo encargado de un segmento a la matriz de Impresion
     *
     * @param segmento Segmento a adicionar
     */  
    private void adicionarSegmento(int segmento) {

        switch (segmento) {
            case 1:
                adicionarLinea(this.matrizImprimir, this.pivote1, POSICION_Y,
                        this.size, CaracterVERTICAL);
                break;
            case 2:
                adicionarLinea(this.matrizImprimir, this.pivote2, POSICION_Y,
                        this.size, CaracterVERTICAL);
                break;
            case 3:
                adicionarLinea(this.matrizImprimir, this.pivote5, POSICION_Y,
                        this.size, CaracterVERTICAL);
                break;
            case 4:
                adicionarLinea(this.matrizImprimir, this.pivote4, POSICION_Y,
                        this.size, CaracterVERTICAL);
                break;
            case 5:
                adicionarLinea(this.matrizImprimir, this.pivote1, POSICION_X,
                        this.size, CaracterHORIZONTAL);
                break;
            case 6:
                adicionarLinea(this.matrizImprimir, this.pivote2, POSICION_X,
                        this.size, CaracterHORIZONTAL);
                break;
            case 7:
                adicionarLinea(this.matrizImprimir, this.pivote3, POSICION_X,
                        this.size, CaracterHORIZONTAL);
                break;
            default:
                break;
        }
    }

    /**
     *
     * Metodo encargado de definir los segmentos que componen un digito y
     * a partir de los segmentos adicionar la representacion del digito a la matriz
     *
     * @param numero Digito
     */
    private void adicionarDigito(int numero) {

        // Establece los segmentos de cada numero
        List<Integer> segList = new ArrayList<>();

        switch (numero) {
            case 1:
                segList.add(3);
                segList.add(4);
                break;
            case 2:
                segList.add(5);
                segList.add(3);
                segList.add(6);
                segList.add(2);
                segList.add(7);
                break;
            case 3:
                segList.add(5);
                segList.add(3);
                segList.add(6);
                segList.add(4);
                segList.add(7);
                break;
            case 4:
                segList.add(1);
                segList.add(6);
                segList.add(3);
                segList.add(4);
                break;
            case 5:
                segList.add(5);
                segList.add(1);
                segList.add(6);
                segList.add(4);
                segList.add(7);
                break;
            case 6:
                segList.add(5);
                segList.add(1);
                segList.add(6);
                segList.add(2);
                segList.add(7);
                segList.add(4);
                break;
            case 7:
                segList.add(5);
                segList.add(3);
                segList.add(4);
                break;
            case 8:
                segList.add(1);
                segList.add(2);
                segList.add(3);
                segList.add(4);
                segList.add(5);
                segList.add(6);
                segList.add(7);
                break;
            case 9:
                segList.add(1);
                segList.add(3);
                segList.add(4);
                segList.add(5);
                segList.add(6);
                segList.add(7);
                break;
            case 0:
                segList.add(1);
                segList.add(2);
                segList.add(3);
                segList.add(4);
                segList.add(5);
                segList.add(7);
                break;
            default:
                break;
        }

        Iterator<Integer> iterator = segList.iterator();

        while (iterator.hasNext()) {
            adicionarSegmento(iterator.next());
        }
    }

    /**
     *
     * Metodo encargado de imprimir un numero
     *
     * @param size Tamaño Segmento Digitos
     * @param numeroImp Numero a Imprimir
     * @param espacio Espacio Entre digitos
     */    
    private void imprimirNumero(int size, String numeroImp, int espacio) 
    {
        int pivotX = 0;
        char[] digitos;

        this.size = size;

        // Calcula el numero de filas cada digito
        this.digFilas = (2 * this.size) + 3;

        // Calcula el numero de columna de cada digito
        this.digColumnas = this.size + 2;

        // Calcula el total de filas de la matriz en la que se almacenaran los digitos
        this.filasTotales = this.digFilas;

        // Calcula el total de columnas de la matriz en la que se almacenaran los digitos
        this.columnasTotales = (this.digColumnas * numeroImp.length())
                + (espacio * numeroImp.length());

        // crea matriz para almacenar los numero a imprimir
        this.matrizImprimir = new String[this.filasTotales][this.columnasTotales];

        // crea el arreglo de digitos
        digitos = numeroImp.toCharArray();

        // Inicializa matriz
        for (int i = 0; i < this.filasTotales; i++) {
            for (int j = 0; j < this.columnasTotales; j++) {
                this.matrizImprimir[i][j] = " ";
            }
        }

        for (char digito : digitos) {
            
            //Valida que el caracter sea un digito
            if( ! Character.isDigit(digito))
            {
                throw new IllegalArgumentException("Caracter " + digito
                    + " no es un digito");
            }

            int numero = Integer.parseInt(String.valueOf(digito));

            //Calcula puntos fijos
            this.pivote1[0] = 0;
            this.pivote1[1] = 0 + pivotX;

            this.pivote2[0] = (this.digFilas / 2);
            this.pivote2[1] = 0 + pivotX;

            this.pivote3[0] = (this.digFilas - 1);
            this.pivote3[1] = 0 + pivotX;

            this.pivote4[0] = (this.digColumnas - 1);
            this.pivote4[1] = (this.digFilas / 2) + pivotX;

            this.pivote5[0] = 0;
            this.pivote5[1] = (this.digColumnas - 1) + pivotX;

            pivotX = pivotX + this.digColumnas + espacio;

            adicionarDigito(numero);
        }

        // Imprime matriz
        for (int i = 0; i < this.filasTotales; i++) {
            for (int j = 0; j < this.columnasTotales; j++) {
                System.out.print(this.matrizImprimir[i][j]);
            }
            System.out.println();
        }
    }

     /**
     *
     * Metodo encargado de procesar la entrada que contiene el size del segmento
     * de los digitos y los digitos a imprimir
     *
     * @param comando Entrada que contiene el size del segmento de los digito
     * y el numero a imprimir
     * @param espacioDig Espacio Entre digitos
     */  
    public void procesar(String comando, int espacioDig) {
        
        String[] parametros;
        
        int tam;

        if (!comando.contains(",")) {
            throw new IllegalArgumentException("Cadena " + comando
                    + " no contiene caracter ,");
        }
        
        
        parametros = comando.split(",");
        
        
        if(parametros.length>2)
        {
           throw new IllegalArgumentException("Cadena " + comando
                    + " contiene mas caracter ,"); 
        }
        
       
        if(parametros.length<2)
        {
           throw new IllegalArgumentException("Cadena " + comando
                    + " no contiene los parametros requeridos"); 
        }
        
        
        if(isNumeric(parametros[0]))
        {
            tam = Integer.parseInt(parametros[0]);
            
            
            if(tam <1 || tam >10)
            {
                throw new IllegalArgumentException("El parametro size ["+tam
                        + "] debe estar entre 1 y 10");
            }
        }
        else
        {
            throw new IllegalArgumentException("Parametro Size [" + parametros[0]
                    + "] no es un numero");
        }

        // Realiza la impresion del numero
        imprimirNumero(tam, parametros[1],espacioDig);

    }

    
    static boolean isNumeric(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}
