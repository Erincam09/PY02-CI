
import java.lang.StringBuffer;

public class Cod3D {
    
    // Buffer de código como en los ejemplos del profe
    public StringBuffer codigo3D = new StringBuffer();
    
    // Contador de temporales (empieza en 1 como en los ejemplos: t1, t2, t3...)
    private int tempActual = 1;
    
    /**
     * Genera un nuevo temporal secuencial
     * Ejemplo: t1, t2, t3...
     */
    public String nuevoTemp() {
        String temp = "t" + tempActual;
        tempActual++;
        return temp;
    }
    
    /**
     * Emite una línea de código 3D con salto de línea
     * Como en: codigo3D.append("\\n"+temp+" = "+intL.toString());
     */
    public void crearCodigo(String linea) {
        codigo3D.append(linea).append("\n");
    }
    
    /**
     * Emite una línea con salto de línea explícito al inicio
     * Como en los ejemplos que usan "\\n" al principio
     */
    public void emitirLinea(String linea) {
        codigo3D.append("\n").append(linea);
    }
    
    /**
     * Obtiene todo el código 3D generado
     */
    public String getCodigo() {
        return codigo3D.toString();
    }
    
    /**
     * Limpia el buffer y reinicia contadores
     */
    public void limpiar() {
        codigo3D = new StringBuffer();
        tempActual = 1;
    }
    
    /**
     * Genera código para suma
     * Ejemplo: t1 = a + b
     */
    public String genSuma(String izquierda, String derecha) {
        String temp = nuevoTemp();
        crearCodigo(temp + " = " + izquierda + " + " + derecha);
        return temp;
    }
    
    /**
     * Genera código para resta
     * Ejemplo: t2 = a - b
     */
    public String genResta(String izquierda, String derecha) {
        String temp = nuevoTemp();
        crearCodigo(temp + " = " + izquierda + " - " + derecha);
        return temp;
    }
    
    /**
     * Genera código para multiplicación
     * Ejemplo: t3 = a * b
     */
    public String genMultiplicacion(String izquierda, String derecha) {
        String temp = nuevoTemp();
        crearCodigo(temp + " = " + izquierda + " * " + derecha);
        return temp;
    }
    
    /**
     * Genera código para división
     * Ejemplo: t4 = a / b
     */
    public String genDivision(String izquierda, String derecha) {
        String temp = nuevoTemp();
        crearCodigo(temp + " = " + izquierda + " / " + derecha);
        return temp;
    }
    
    /**
     * Para literales enteros - devuelve el valor directamente
     * Como en: RESULT = intL.toString();
     */
    public String genLiteralEntero(String valor) {
        return valor;
    }
    
    /**
     * Para identificadores/variables - devuelve el nombre
     * Como en: RESULT = idVar.toString();
     */
    public String generarVariable(String nombre) {
        return nombre;
    }
    
    
    /**
     * Genera código para asignación
     * Ejemplo: a = t1
     * Como en: codigo3D.append(" \\n"+crea.toString()+" = "+partesExpr[1]);
     */
    public void genAsignacion(String variable, String expresion) {
        crearCodigo(variable + " = " + expresion);
    }
    
    // ============ MÉTODOS PARA MÚLTIPLES OPERACIONES ============
    
    /**
     * Para expresiones con múltiples operaciones
     * Retorna el temporal final que contiene el resultado
     * 
     * Ejemplo: para 2 + 3 * 4
     * t1 = 3 * 4
     * t2 = 2 + t1
     * retorna "t2"
     */
    public String generarExpbin(String operador, String izquierda, String derecha) {
        switch(operador) {
            case "+": return genSuma(izquierda, derecha);
            case "-": return genResta(izquierda, derecha);
            case "*": return genMultiplicacion(izquierda, derecha);
            case "/": return genDivision(izquierda, derecha);
            default: 
                System.err.println("Operador no soportado: " + operador);
                return izquierda;
        }
    }
}