package beans;

public class Bean02TablaMultiplicar {

    //METODO PARA DEVOLVER UN STRING CON EL DIBUJO DE
    //LA TABLA DE MULTIPLICAR
    public String getTablaMultiplicar(int numero) {
        String html = "<table border='1'>";
        html += "<tr>";
        html += "<th>Operación</th>";
        html += "<th>Resultado</th>";
        html += "</tr>";
        for (int i = 1; i <= 10; i++) {
            String operacion = numero + " * " + i;
            int resultado = numero * i;
            html += "<tr>";
            html += "<td>" + operacion + "</td>";
            html += "<td>" + resultado + "</td>";
            html += "</tr>";
        }
        html += "</table>";
        return html;
    }
}
