package rol;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.*;
import rol.Personaje.Raza;

public class Juego extends Canvas implements KeyListener {

    // Mapa
    final int FILAS = 10;
    final int COLUMNAS = 20;
    int[][] mapaTerreno = {
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1},
        {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},};

    // Posición inicial
    int posF = 1;
    int posC = 1;

    private static JFrame ventana;
    protected JPanel jpanel;

    public Juego() {
        // Crea y configura la ventana del juego
        ventana = new JFrame();
        ventana.addKeyListener(this);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setMinimumSize(new Dimension(300, 300));

        jpanel = new JPanel();
        ventana.add(jpanel);

        //Dibuja el mapa y hace visible la ventana
        dibujarMapa();
        ventana.pack();
        ventana.setVisible(true);
    }

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
    
    
    
    
    
         



    /*
        Juego juego = new Juego();


//IAGO
        p[0] = new Personaje("Radanh", Personaje.Raza.ORCO, 20, 5, 0, 1, 5, 8, 3, 0, 400);
        p[1] = new Personaje("Rennala", Personaje.Raza.HUMANO, 5, 10, 0, 13, 4, 15, 5, 1, 200);
        p[2] = new Personaje("Godfrey", Personaje.Raza.ELFO, 5, 2, 4, 6, 5, 7, 2, 2, 250);
        p[3] = new Personaje("Rykard", Personaje.Raza.HOBBIT, 8, 12, 3, 4, 8, 7, 10, 3, 275);
        p[4] = new Personaje("Malenia", Personaje.Raza.HUMANO, 100, 100, 5, 5, 15, 20, 15, 4, 400);
        p[5] = new Personaje("Morgott", Personaje.Raza.TROLL, 12, 8, 5, 7, 4, 15, 11, 5, 300);
// DIEGO T
        personajes[0] = new Personaje("Darwf",Raza.ORCO,24,35,26,75,46,100,2,7,100);
        personajes[1] = new Personaje("Vitril",Raza.ELFO,24,35,26,75,46,34,2,5,34);
        personajes[2] = new Personaje("Juan",Raza.HUMANO,24,35,26,75,46,90,56,7,90);
        personajes[3] = new Personaje("Fodo",Raza.HOBBIT,24,35,26,75,46,12,63,25,12);
        personajes[4] = new Personaje("Sa",Raza.HOBBIT,24,35,26,75,46,87,23,34,43);
        personajes[5] = new Personaje("Groul",Raza.TROLL,24,35,26,75,46,87,54,34,945);
// EDUARDO
        personaje[0] = new Personaje("Ude", Raza.HUMANO);
        personaje[1] = new Personaje("Nird", Raza.TROLL);
        personaje[2] = new Personaje("Ugid", Raza.ELFO);
        personaje[3] = new Personaje("Lrbag", Raza.HOBBIT);
        personaje[4] = new Personaje("Ciam", Raza.ENANO);
        personaje[5] = new Personaje("Eppes", Raza.ORCO);        

        
        
        new Personaje("Kratos", Raza.ORCO, 10, -2, 7, -1, 5, 8, 5, 14, 28);
        new Personaje("Heimerdinger", Raza.ENANO, 3, 6, 2, 13, 4, -1);
        new Personaje("Frodo", Raza.HOBBIT);
        new Personaje("Iago");
        
                
//    Personaje pj3 = new Personaje("Gimli", Raza.ENANO, 55, 55, 55, 55, 55, -3);
//    Personaje pj4 = new Personaje("Frodo", Raza.HOBBIT, 77, 77, 77, 77, 77, 77, 4, 4705, 5);


    Personaje pj1 = new Personaje("Aragorn"); 
    Personaje pj2 = new Personaje("Legolas", Raza.ELFO);

    pj1.mostrar();
    pj2.mostrar();

    System.out.println("EMPIEZA EL COMBATE!!");
    int i = 0;
    while (pj1.estaVivo() && pj2.estaVivo()) {       
        i++;
        System.out.println("TURNO " + i);
        System.out.println("========");
        pj1.atacar(pj2);
        if (pj2.estaVivo())
            pj2.atacar(pj1);
    }
    System.out.println("FIN DEL COMBATE!! \n\n");   

    pj1.mostrar();
    pj2.mostrar();

    
    Monstruo m1 = new Orco("Groch");

    /*m1.mostrar();*/
 /*    pj2.mostrar();
    pj3.mostrar();        
    pj4.mostrar();  
    pj4.curar();
    pj4.subirNivel();
    pj4.subirNivel();
    pj4.mostrar(); 
     */   
    
    
    
    
    
    
    
    
    
    
    
    public final void dibujarMapa() {
        jpanel.removeAll();

        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                JLabel casilla = new JLabel();
                casilla.setOpaque(true);

                switch (mapaTerreno[i][j]) {
                    case 0:
                        casilla.setBackground(Color.WHITE);
                        break;
                    case 1:
                        casilla.setBackground(Color.DARK_GRAY);
                        break;
                }
                if (i == posF && j == posC) {
                    casilla.setText("P");
                    casilla.setBackground(Color.GREEN);
                }
                jpanel.add(casilla);
            }
        }
        jpanel.setLayout(new GridLayout(FILAS, COLUMNAS, 1, 1));

        jpanel.validate();
        jpanel.repaint();
    }

    
    @Override
        public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                if (mapaTerreno[posF - 1][posC] == 0) {
                    posF--;
                    dibujarMapa();
                    ventana.pack();
                }
                System.out.println("arriba");
                break;
            case KeyEvent.VK_S:
                if (mapaTerreno[posF + 1][posC] == 0) {
                    posF++;
                    dibujarMapa();
                    ventana.pack();
                }
                System.out.println("abajo");
                break;
            case KeyEvent.VK_A:
                if (mapaTerreno[posF][posC - 1] == 0) {
                    posC--;
                    dibujarMapa();
                    ventana.pack();
                }
                System.out.println("izquierda");
                break;
            case KeyEvent.VK_D:
                if (mapaTerreno[posF][posC + 1] == 0) {
                    posC++;
                    dibujarMapa();
                    ventana.pack();
                }
                System.out.println("derecha");
                break;
        }
    }

    @Override
        public void keyReleased(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
        public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
