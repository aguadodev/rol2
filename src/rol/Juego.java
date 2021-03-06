package rol;

import java.util.Arrays;
import java.util.Scanner;
import rol.Personaje.Raza;

public class Juego {

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);
        int opcion;

        Personaje[] personajes = new Personaje[6];
        Monstruo monstruo = null;

        personajes[0] = new Personaje("Aragorn", Raza.HOBBIT, 77, 77, 77, 77, 77, 77, 42, 43, 24);
        personajes[1] = new Personaje("Gimil", Raza.ENANO, 77, 77, 77, 77, 77, 77, 42, 46, 44);
        personajes[2] = new Personaje("Roberto", Raza.HOBBIT, 77, 77, 77, 77, 77, 77, 66, 56, 43);
        personajes[3] = new Personaje("Ana", Raza.HOBBIT, 77, 77, 77, 77, 77, 77, 7, 23, 54);
        personajes[4] = new Personaje("Anxo", Raza.HOBBIT, 77, 77, 77, 77, 77, 77, 35, 45, 55);
        personajes[5] = new Personaje("Julio", Raza.HOBBIT, 77, 77, 77, 77, 77, 77, 28, 12, 67);

        do {
        // MENÚ
        System.out.println("MENÚ:\n=====\n"
                + "1.- Mostrar Personajes ordenados de mayor a menor nivel y experiencia\n"
                + "2.- Mostrar Personajes ordenados por PV ascendentemente\n"
                + "3.- Mostrar Personajes ordenados por PV descendentemente\n"
                + "4.- Generar Monstruo\n"
                + "5.- Combate singular\n"
                + "0.- SALIR\n");
        
        opcion = entrada.nextInt();

        switch (opcion) {
            case 1:
                Arrays.sort(personajes);
                mostrarPersonajes(personajes);
                break;
            case 2:
                Arrays.sort(personajes, new ComparatorPuntosVida());
                mostrarPersonajes(personajes);                
                break;
            case 3:
                Arrays.sort(personajes, new ComparatorPuntosVida().reversed());
                mostrarPersonajes(personajes);                
                break;
            case 4:
                monstruo = generaMonstruoAleatorio();
                monstruo.mostrar();
                break;
            case 5:
                combateSingular(personajes[0], monstruo);
        }    
        } while (opcion != 0);
        
}

    public static void mostrarPersonajes(Personaje[] personajes) {
        for (Personaje p : personajes) {
            System.out.println(p);
        }
    }    
    
public static Monstruo generaMonstruoAleatorio(){
        double numero = Math.random() * 100;

        if (numero < 40) {
            return new Orco();
        } else if (numero < 70) {
            return new Aranha();
        } else if (numero < 90) {
            return new Troll();
        } else {
            return new Dragon();
        }
    }
    
    
    public static void combateSingular(Personaje p, Monstruo m) {
        // Decidir iniciativa - Quién ataca primero?
        p.mostrar();
        m.mostrar();
        if (p.agilidad >= m.velocidad) {
            do {
                p.atacar(m);
                if (m.estaVivo()) {
                    m.atacar(p);
                }
            } while (p.estaVivo() && m.estaVivo());
            
        } else {
            do {
                m.atacar(p);
                if (p.estaVivo()) {
                    p.atacar(m);
                }
            } while (p.estaVivo() && m.estaVivo());
        }
        p.mostrar();
        m.mostrar();
    }    

}
