import java.util.ArrayList;
import java.util.List;

/**
 * Abstract base class representing a generic part in a construction project.
 * Can be either a simple part with a fixed price or a composite part that
 * contains other parts.
 */
abstract class ParteAbstracta {
    protected String nombre; // Name of the part

    /**
     * Constructor to initialize a part with a given name.
     *
     * @param nombre The name of the part.
     */
    protected ParteAbstracta(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Gets the name of the part.
     *
     * @return The name of the part.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets a new name for the part.
     *
     * @param nombre The new name of the part.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Abstract method to get the price of the part.
     *
     * @return The price of the part.
     */
    abstract double getPrecio();

    /**
     * Abstract method to print a budget breakdown for the part.
     * 
     * @param tab Indentation used for displaying nested parts.
     */
    abstract void imprimirPresupuesto(String tab);
}

/**
 * Composite part class that can contain multiple other parts, allowing nested
 * parts.
 * Represents complex parts in the project which consist of other parts.
 */
class ParteCompuesta extends ParteAbstracta {
    private List<ParteAbstracta> partes = new ArrayList<>(); // List of subparts

    /**
     * Constructor to initialize a composite part with a given name.
     *
     * @param nombre The name of the composite part.
     */
    public ParteCompuesta(String nombre) {
        super(nombre);
    }

    /**
     * Adds a subpart to the composite part.
     *
     * @param parte The part to be added.
     */
    public void addParte(ParteAbstracta parte) {
        this.partes.add(parte);
    }

    /**
     * Calculates the total price of the composite part by summing the prices of its
     * subparts.
     *
     * @return The total price of the composite part.
     */
    @Override
    public double getPrecio() {
        double precio = 0;
        for (ParteAbstracta parte : partes) {
            precio += parte.getPrecio();
        }
        return precio;
    }

    /**
     * Prints the budget breakdown of the composite part, including all nested
     * subparts.
     *
     * @param tab Indentation used for displaying nested parts.
     */
    @Override
    void imprimirPresupuesto(String tab) {
        System.out.println(tab + this.getNombre() + " " + this.getPrecio());
        for (ParteAbstracta parte : partes) {
            parte.imprimirPresupuesto(tab + "\t");
        }
    }
}

/**
 * Leaf part class that represents a simple, individual part with a fixed price.
 * This class has no nested parts.
 */
class ParteSimple extends ParteAbstracta {
    private double precio; // Price of the simple part

    /**
     * Constructor to initialize a simple part with a given name and price.
     *
     * @param nombre The name of the simple part.
     * @param precio The price of the simple part.
     */
    public ParteSimple(String nombre, double precio) {
        super(nombre);
        this.precio = precio;
    }

    /**
     * Gets the price of the simple part.
     *
     * @return The price of the part.
     */
    @Override
    double getPrecio() {
        return precio;
    }

    /**
     * Prints the budget breakdown for the simple part.
     *
     * @param tab Indentation used for displaying nested parts.
     */
    @Override
    void imprimirPresupuesto(String tab) {
        System.out.println(tab + this.getNombre() + " " + this.getPrecio());
    }
}

/**
 * Main application class to demonstrate the construction project structure.
 * Builds a composite structure for a sample house, calculates the total cost,
 * and prints a detailed breakdown.
 */
public class App {
    public static void main(String[] args) {
        // Define the "finca" section with simple parts
        ParteSimple cierre = new ParteSimple("Cierre finca", 4000);
        ParteSimple jardin = new ParteSimple("jardín", 1000);
        ParteCompuesta finca = new ParteCompuesta("finca");
        finca.addParte(cierre);
        finca.addParte(jardin);

        // Define the "estructura" section with simple parts
        ParteSimple tejado = new ParteSimple("tejado", 10000);
        ParteSimple alturas = new ParteSimple("alturas", 10000);
        ParteSimple sotano = new ParteSimple("sótano", 10000);
        ParteCompuesta estructura = new ParteCompuesta("estructura");
        estructura.addParte(tejado);
        estructura.addParte(alturas);
        estructura.addParte(sotano);

        // Define the "interior" section with nested components
        ParteSimple mobiliario = new ParteSimple("mobiliario", 20000);
        ParteSimple pintura = new ParteSimple("pintura", 10000);
        ParteCompuesta habitaciones = new ParteCompuesta("habitaciones");
        habitaciones.addParte(mobiliario);
        habitaciones.addParte(pintura);

        ParteSimple cables = new ParteSimple("cables", 500);
        ParteSimple operadores = new ParteSimple("operadores", 500);
        ParteCompuesta electricidad = new ParteCompuesta("electricidad");
        electricidad.addParte(cables);
        electricidad.addParte(operadores);

        ParteSimple caldera = new ParteSimple("caldera", 4000);
        ParteSimple radiadores = new ParteSimple("radiadores", 2000);
        ParteCompuesta calefaccion = new ParteCompuesta("calefacción");
        calefaccion.addParte(caldera);
        calefaccion.addParte(radiadores);

        ParteSimple tuberias = new ParteSimple("tuberías", 3000);
        ParteCompuesta fontaneria = new ParteCompuesta("fontanería");
        fontaneria.addParte(tuberias);
        fontaneria.addParte(calefaccion);

        ParteCompuesta interior = new ParteCompuesta("interior");
        interior.addParte(habitaciones);
        interior.addParte(electricidad);
        interior.addParte(fontaneria);

        // Define the "Casa" composite with all components
        ParteCompuesta casa = new ParteCompuesta("Casa");
        casa.addParte(finca);
        casa.addParte(estructura);
        casa.addParte(interior);

        // Calculate and display the total cost of the house
        System.out.println("Total house price: " + casa.getPrecio());

        // Display the detailed budget breakdown of the house
        casa.imprimirPresupuesto("");
    }
}
