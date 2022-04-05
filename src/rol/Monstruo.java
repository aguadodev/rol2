
package rol;


public class Monstruo {
    String nombre;
    int ataque;
    int defensa;
    int velocidad;
    int puntosVida;


    // CONSTRUCTORES    
    public Monstruo(){        
    }
    
    public Monstruo(String nombre, int ataque, int defensa, int velocidad, int puntosVida) {
        this.nombre = nombre;
        // Asegura que los atributos no sean negativos
        if (ataque > 0) this.ataque = ataque;
        if (defensa > 0) this.defensa = defensa;
        if (velocidad > 0) this.velocidad = velocidad;
        if (puntosVida > 0) this.puntosVida = puntosVida;
    }

    public Monstruo(int ataque, int defensa, int velocidad, int puntosVida) {
        this(null, ataque, defensa, velocidad, puntosVida);
    }
    
    // MÉTODOS PÚBLICOS    
    public void mostrar() {
        System.out.println("MONSTRUO");
        System.out.println("=======");        
        if (nombre != null) System.out.println("Nombre: " + nombre);
        System.out.println("Clase: " + getClass().getSimpleName());
        System.out.println("Ataque: " + ataque);
        System.out.println("Defensa: " + defensa);
        System.out.println("Velocidad: " + velocidad);
        System.out.println("Puntos de Vida: " + puntosVida);
        System.out.println("");
    }    
    
    public boolean estaVivo(){
        return puntosVida >= 0;
    }
 
    public boolean perderVida(int puntos){
        puntosVida -= puntos;
        return puntosVida < 0;
    }

    public void atacar(Personaje p){
        System.out.println(nombre + "("+ puntosVida +") ataca a " + 
                p.nombre + "(" + p.puntosVida + "): ");
        int dadosPj = random100();
        int ataque = this.ataque + dadosPj;
        System.out.print("* Ataque = (ataque + random100) = ");
        System.out.println("(" + this.ataque + " + " + dadosPj + ") = " + ataque);
        int dadosM = random100();
        int defensa = p.agilidad + dadosM;        
        System.out.print("* Defensa = (defensa + random100) = ");
        System.out.println("(" + p.agilidad + " + " + dadosM + ") = " + defensa);
        
        System.out.print("* Resultado: ");
        int resultado = ataque - defensa;
        
        if (resultado > 0) {
            // El personaje suma experiencia también por las heridas recibidas.
            p.sumarExperiencia(resultado);
            System.out.println(p.nombre + " suma " + resultado + " puntos de experiencia.");
            if (p.perderVida(resultado))
                System.out.println(nombre + " mata a " + p.nombre + "!!! (-" + resultado + " PV)");
            else
                System.out.println(nombre + " hiere a " + p.nombre + " (-" + resultado + " PV)");                                   
        } else {
            System.out.println(p.nombre + " esquiva o para el ataque.");
        }                
    }   

    
    // REDEFINICIÓN DE MÉTODOS
    @Override
    public String toString() {
        String cad = "";
        if (nombre != null) cad += nombre + "-";
        cad += this.getClass().getSimpleName() + " (PV=" + puntosVida + ") ";
        return cad;
    }
    
    
    // OTROS MÉTODOS
    protected static int random100(){
        return (int)(Math.random() * 100 + 1);
    }
    
    // OTROS MÉTODOS
    protected static int randomRango(int inicio, int fin){
        return (int)(Math.random() * (fin - inicio + 1) + inicio);
    }    
    
}
