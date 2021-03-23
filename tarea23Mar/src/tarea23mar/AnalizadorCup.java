/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea23mar;

import java.io.IOException;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import java_cup.runtime.Symbol;

/**
 *
 * @author david
 */
public class AnalizadorCup {

    private int token;
    private String entrada;
    private lexico lex;
    private boolean error = false;
    int suma = 1, mult = 2, id = 3, num = 4, pa = 5, pc = 6, fincadena = 0, ERROR = 10;

    public AnalizadorCup(String entrada) {
        this.entrada = entrada;
    }

    private void avanzar() {
        try {
            token = lex.next_token().sym;
        } catch (IOException ex) {
            Logger.getLogger(Tarea23Mar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void consumir(int entrada) {
        if (entrada == token) {
            avanzar();
        } else {
            error = true;
            //System.out.println("Error en entrada");
        }
    }

    public void analizar() {
        lex = new lexico(new StringReader(entrada));
        try {
            token = lex.next_token().sym;
            //System.out.println("Token es " + token);
            E();
            if (error) {
                System.out.println("Error Cadena no aceptada "+entrada);
            } else {
                System.out.println("Cadena " + entrada + " Aceptada");
            }
        } catch (IOException ex) {
            Logger.getLogger(Tarea23Mar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void E() {
        switch (token) {
            case 3:
                //System.out.println("Se va a E -> TEp");
                T();
                Ep();
                break;
            case 4:
                //System.out.println("Se va a E -> TEp");
                T();
                Ep();
                break;
            case 5:
                //System.out.println("Se va a E -> TEp");
                T();
                Ep();
                break;
            default:
                error(token, new int[]{3, 4, 5});
                break;
        }
    }

    private void Ep() {
        switch (token) {
            case 1:
               // System.out.println("Se va a Ep -> +TEp");
                consumir(1);
                T();
                Ep();
                break;
            case 6:
                //System.out.println("Nada");
                break;
            case 0:
                //System.out.println("Nada");
                break;
            default:
                error(token, new int[]{1, 6, 0});
                break;
        }
    }

    private void T() {
        switch (token) {
            case 3:
                //System.out.println("Se va a T -> FTp");
                F();
                Tp();
                break;
            case 4:
                //System.out.println("Se va a T -> FTp");
                F();
                Tp();
                break;
            case 5:
                //System.out.println("Se va a T -> FTp");
                F();
                Tp();
                break;
            default:
                error(token, new int[]{3, 4, 5});
                break;

        }
    }

    private void Tp() {
        switch (token) {
            case 1:
                //System.out.println("Nada");
                break;
            case 2:
                //System.out.println("Se va a Tp -> *FTp");
                consumir(2);
                F();
                Tp();
                break;
            case 6:
                //System.out.println("Nada");
                break;
            case 0:
                //System.out.println("Nada");
                break;
            default:
                error(token, new int[]{1, 2, 6, 0});
                break;

        }
    }

    private void F() {
        switch (token) {
            case 3:
                //System.out.println("Se va a F->id");
                consumir(3);
                break;
            case 4:
                //System.out.println("Se va a F->num");
                consumir(4);
                break;
            case 5:
                //System.out.println("Se va a F->(E)");
                consumir(5);
                E();
                consumir(6);
                break;
            default:
                error(token, new int[]{3, 4, 5});
                break;

        }
    }

    private void error(int token, int esperado[]) {
        System.out.println("Error en la entrada con simbolo " + token);
        String esperadoString = tipo(esperado);
        System.out.println("Se esperaba " + esperadoString);
        error = true;
    }

    private String tipo(int Esperado[]) {
        String salida = "";
        for (int i : Esperado) {
            switch (i) {
                case 1:
                    salida += " Suma(+) ";
                    break;
                case 2:
                    salida += " Multiplicacion(*) ";
                    break;
                case 3:
                    salida += " Id([a-zA-Z]([0-9]|[a-zA-Z])*) ";
                    break;
                case 4:
                    salida += " numero [0-9]+ ";
                    break;
                case 5:
                    salida += " Parentesis abierto'(' ";
                    break;
                case 6:
                    salida += " Parentesis cerrado ')' ";
                    break;
                case 7:
                    salida += " Fin cadena ";
                    break;
            }
            salida+="\n";
        }
        return salida;
    }    
}
