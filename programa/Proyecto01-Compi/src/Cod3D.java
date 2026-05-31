
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
    private int contDo = 1;
    private int contSwitch = 1;
    private int contCaso = 1;

    private int contParam = 1;
    
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
        contIf = 0;
        contDo = 1;
        contSwitch = 1;
        contCaso = 1;
        contParam = 1;
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

    public String nuevoDo() {
        return "do_" + contDo++;
    }

    public int nuevoSwitch() {
        return contSwitch++;
    }

    public void reiniciarCases() {
        contCaso = 1;
    }

    public String nuevoCase(String etiquetaSwitch) {
        String etiqueta = etiquetaSwitch + "_case_" + contCaso;
        contCaso++;
        return etiqueta;
    }


    // ================ MÉTODOS PARA FUNCIONES Y PARÁMETROS ================
    public void reiniciarParametros() {
        contParam = 1;
    }

    public void genParametro(String tipo, String valor) {
        crearCodigo("param_" + contParam + "_" + tipo + "_" + valor);
        contParam++;
    }

    public void genParametroInv(int numero, String tipo, String valor) {
        crearCodigo("param_inv_" + numero + "_" + tipo + "_" + valor);
    }

    public String genLlamada(String funcion, int numParams) {
        String temp = nuevoTemp();
        crearCodigo(temp + " = call " + funcion + "," + numParams);
        return temp;
    }

    // ================ MÉTODOS PARA CIN (READ) Y COUT (PRINT) ================

    public void genPrint(String tipo, String valor) {
        crearCodigo("cout, " + tipo + ", " + valor);
    }

    public void genRead(String tipo, String variable) {
        crearCodigo("cin, " + tipo + ", " + variable);
    }

    // ================ MÉTODO PARA ARRAYS ================
    public void genDeclaracionArray(String nombre, String tipo, String filas, String columnas) {
        crearCodigo("data_" + tipo + "_array " + nombre + " " + filas + "," + columnas);
    }

    /* public void genTempsArray(String nombre, String fila, String columna, String temp) {
        crearCodigo(nombre + " " + fila + "," + columna + " = " + temp);
    }
    */

    public void genAsignacionArray(String nombre, String fila, String columna, String valor) {
        crearCodigo(nombre + " " + fila + "," + columna + " = " + valor);
    }

    public String genAccesoArray(String nombre, String fila, String columna) {
        String temp = nuevoTemp();
        crearCodigo(temp + " = " + nombre + " " + fila + "," + columna);
        return temp;
    }

    // ================ MÉTODO PARA ESCRIBIR EL CÓDIGO EN UN ARCHIVO ================

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