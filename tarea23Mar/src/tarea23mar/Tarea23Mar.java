/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea23mar;

import java.util.Scanner;



/**
 *
 * @author david
 */
public class Tarea23Mar {

    /**
     * @param args the command line arguments
     */  
    private int token;
    lexico lex;
    public static void main(String[] args) {
        String entrada = "";
        if(args.length>0){
            entrada="";
            for (String arg : args) {
                entrada+=arg;
            }
        }
        Scanner in = new Scanner(System.in);
        int opcion=1;
        String textoAnalizar;
        while(!entrada.equals("s")){
            if(entrada.isEmpty()){
                System.out.println("Digite una nueva entrada");
                entrada = in.nextLine();
            }
            if(!entrada.isEmpty()){
                AnalizadorCup p = new AnalizadorCup(entrada);
                p.analizar();
            }
            System.out.println("Salida :s  Nuevo analisis: (Ingrese otra entrada distinta de s)");
            entrada = in.nextLine();
        }
        
    }
    
        
    
}
