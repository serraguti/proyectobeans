package beans;

public class Bean01PrimerEjemplo {

    //METODOS PARA DIBUJAR EN LA PAGINA/S
    public String getSaludo() {
        //DEVOLVEMOS EL CODIGO QUE DESEAMOS DIBUJAR
        //POR EJEMPLO, VAMOS A DEVOLVER UN TITULO
        return "<h1 style='color:red'>Saludo desde Java Beans</h1>";
    }

    public String getSaludoPersonal(String nombre) {
        return "<h1 style='color:blue'>Bienvenido, "
                + nombre + "</h1>";
    }
}
