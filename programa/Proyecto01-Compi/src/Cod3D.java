
import java.io.FileWriter;
import java.io.IOException;
import java.lang.StringBuffer;

public class Cod3D {
    
    // Buffer de código como en los ejemplos del profe
    public StringBuffer codigo3D = new StringBuffer();
    
    // Contador de temporales (empieza en 1 como en los ejemplos: t1, t2, t3...)
    private int temptActual = 1;
    private int tempfActual = 1;
    private int labelActual = 1; // Para etiquetas de control (if, while, etc.)

    // contadores estructuras de control

    private int contIf = 0;
    private int contDo = 1;
    private int contSwitch = 1;
    private int contCaso = 1;

    private int contParam = 1;
    
    /**
     * Genera un nuevo temporal secuencial
     * Ejemplo: t1, t2, t3... o f1, f2, f3...
     * Si tipo es "", devuelve solo el número
     */
    public String nuevoTemp(String tipo) {
        String var, temp;
        if (tipo.equals("int")) {
            var = "t";
            temp = var + temptActual;
            temptActual++;
        } else if (tipo.equals("float")) {
            var = "f";
            temp = var + tempfActual;
            tempfActual++;
        } else {
            var = "t";  // Por defecto usamos "t" para otros tipos
            temp = var + temptActual;
            temptActual++;
        }
        return temp;
    }

    /**
     * Versión con tipo específico
     */
    public String genTemporal(String valor, String tipo) {
        String temp = nuevoTemp(tipo);
        crearCodigo(temp + " = " + valor);
        return temp;
    }

    /**
     * Versión sobrecargada sin tipo (para bool y otros tipos)
     * Genera temporales sin prefijo de tipo
     */
    public String genTemporal(String valor) {
        return genTemporal(valor, "");
    }
    
    /**
     * Emite una línea de código 3D con salto de línea
     * Como en: codigo3D.append("\\n"+temp+" = "+intL.toString());
     */
    public void crearCodigo(String linea) {
        codigo3D.append(linea).append("\n");
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
        temptActual = 1;
        tempfActual = 1;
        labelActual = 1;
        contIf = 0;
        contDo = 1;
        contSwitch = 1;
        contCaso = 1;
        contParam = 1;
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

    public String genOperacion(String izquierda, String operador, String derecha, String tipo) {
        String temp = nuevoTemp(tipo);
        crearCodigo(temp + " = " + izquierda + " " + operador + " " + derecha);
        return temp;
    }

    /**
     * Versión sobrecargada sin tipo para operaciones booleanas
     */
    public String genOperacion(String izquierda, String operador, String derecha) {
        return genOperacion(izquierda, operador, derecha, "");
    }

    public String genNegacion(String valor, String tipo) {
        String temp = nuevoTemp(tipo);
        crearCodigo(temp + " = $" + valor);
        return temp;
    }

    /**
     * Versión sobrecargada sin tipo para negación booleana
     */
    public String genNegacion(String valor) {
        return genNegacion(valor, "");
    }

    public String genOpRelacionales(String operador, String izquierda, String derecha, String tipo) {
        String temp = nuevoTemp(tipo);
        crearCodigo(temp + " = " + operador + "," + izquierda + "," + derecha);
        return temp;
    }

    /**
     * Versión sobrecargada sin tipo para operaciones relacionales
     */
    public String genOpRelacionales(String operador, String izquierda, String derecha) {
        return genOpRelacionales(operador, izquierda, derecha, "");
    }
    
    public void genReturn(String valor) {
        crearCodigo("return " + valor);
    }


    // ============ MÉTODOS PARA ESTRUCTURAS DE CONTROL ============

    public String nuevoIf() {
        return "if_" + ++contIf;
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

    public String genLlamada(String funcion, int numParams, String tipo) {
        String temp = nuevoTemp(tipo);
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

    public void genAsignacionArray(String nombre, String fila, String columna, String valor) {
        crearCodigo(nombre + " " + fila + "," + columna + " = " + valor);
    }

    public String genAccesoArray(String nombre, String fila, String columna, String tipo) {
        String temp = nuevoTemp(tipo);
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