package tarea23mar;
import java_cup.runtime.*;
%%
%class lexico
%line
%column
%standalone
%cup
%{


private Symbol symbol (int tipo){
    return new Symbol (tipo,yyline,yycolumn);
}   
private Symbol symbol (int tipo, Object value){
    return new Symbol (tipo,yyline,yycolumn,value);
}

%}
terminaLinea = \r|\n|\r\n
espacioBlanco = {terminaLinea} | [ \t\f]


digito = [0-9]
id = [a-zA-Z]({digito}|[a-zA-Z])*
%eofval{
  return symbol(0);
%eofval}
%%
{espacioBlanco} {
}

\+ { 
//System.out.println("Detecto +");
return symbol(1);
}

\* { 
//System.out.println("Detecto *");
return symbol(2);
}

\( { 
//System.out.println("Detecto parentesis abierto");
return symbol(5);
}

\) { 
//System.out.println("Detecto parentesis cerrado");
return symbol(6);
}

{id} { 
//System.out.println("Detecto id"+yytext());
return symbol(3,yytext());
}

{digito}+ { 
//System.out.println("Detecto digito"+yytext());
return symbol(4,yytext());
}
. { 
System.out.println("Error lexico"+yytext());
return symbol(10,"Error lexico "+yytext());
}
