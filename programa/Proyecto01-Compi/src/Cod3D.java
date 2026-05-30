
import java.io.FileWriter;
import java.io.IOException;
import java.lang.StringBuffer;

public class Cod3D {
    
    // Buffer de código como en los ejemplos del profe
    public StringBuffer codigo3D = new StringBuffer();
    
    // Contador de temporales (empieza en 1 como en los ejemplos: t1, t2, t3...)
    private int tempActual = 1;
    private int labelActual = 1; // Para etiquetas de control (if, while, etc.)

    // contadores estructuras de control

    private int contIf = 0;
    
    /**
     * Genera un nuevo temporal secuencial
     * Ejemplo: t1, t2, t3...
     */
    public String nuevoTemp() {
        String temp = "t" + tempActual;
        tempActual++;
        return temp;
    }

    public String genTemporal(String valor) {
        String temp = nuevoTemp();
        crearCodigo(temp + " = " + valor);
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
        labelActual = 1;
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
    
    public String nuevaEtiqueta(String base) {
        return base + "_" + labelActual++;
    }

    public void crearEtiqueta(String etiqueta) {
        crearCodigo(etiqueta + ":");
    }

    public String genOperacion(String izquierda, String operador, String derecha) {
        String temp = nuevoTemp();
        crearCodigo(temp + " = " + izquierda + " " + operador + " " + derecha);
        return temp;
    }

    public String genNegacion(String valor) {
        String temp = nuevoTemp();
        crearCodigo(temp + " = $" + valor);
        return temp;
    }

    public String genOpRelacionales(String operador, String izquierda, String derecha) {
        String temp = nuevoTemp();
        crearCodigo(temp + " = " + operador + "," + izquierda + "," + derecha);
        return temp;
    }

    public void genReturn(String valor) {
        crearCodigo("return " + valor);
    }

    public void genPrint(String tipo, String valor) {
        crearCodigo("print, " + tipo + ", " + valor);
    }

    public void genRead(String tipo, String variable) {
        crearCodigo("read, " + tipo + ", " + variable);
    }

    // ============ MÉTODOS PARA ESTRUCTURAS DE CONTROL ============

    public String nuevoIf() {
        return "if_" + ++contIf;
    }

    public String nuevoElse() {
        return "else_" + contIf;
    }

    public void genIf(String condicion, String etiqueta) {
        crearCodigo("if " + condicion + " goto " + etiqueta);
    }

    public void genGoto(String etiqueta) {
        crearCodigo("goto " + etiqueta);
    }















    public void escribirArchivo() {
        try {
            FileWriter fw = new FileWriter("codigo_intermedio.txt");
            fw.write(codigo3D.toString());
            fw.close();
        } catch (IOException e) {
            System.err.println("Error escribiendo código intermedio: " + e.getMessage());
        }
    }
}