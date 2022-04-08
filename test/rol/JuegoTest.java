package rol;


import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alberto, Brais, Pablo Raúl, Roberto
 */
public class JuegoTest {

    @Test
    public void personajeAtaca() {

        //Creación de perosnajes y monstruos
        Personaje p1 = new Personaje("Raul", Personaje.Raza.ELFO);
        Personaje p2 = new Personaje("Carlos", Personaje.Raza.ENANO);
        Monstruo m1 = new Monstruo("Puerto", 100, 50, 50, 50);

        int expAtacante = p1.experiencia;
        int vidaDefensor = p2.puntosVida;
        p1.atacar(p2);
        //La experiencia ganada del atacante tiene que ser equivalente a la vida perdida del defensor
        assertTrue((p1.experiencia - expAtacante) == (vidaDefensor - p2.puntosVida));

        expAtacante = p1.experiencia;
        vidaDefensor = m1.puntosVida;
        p1.atacar(m1);
        assertTrue((p1.experiencia - expAtacante) == (vidaDefensor - m1.puntosVida));
    }

    enum TipoMonstruo {
        Aranha, Orco, Troll, Dragon
    }

    @Test
    public void ComprobarMonstruos() {

        //Definición de variables
        TipoMonstruo[] tipoMonstruo = {TipoMonstruo.Aranha, TipoMonstruo.Orco, TipoMonstruo.Troll, TipoMonstruo.Dragon};
        Monstruo monstruo;

        int ataqueMin = 0, ataqueMax = 0, defensaMin = 0, defensaMax = 0, velocidadMin = 0, velocidadMax = 0, puntosVidaMin = 0, puntosVidaMax = 0;
        boolean minAtaque = false;
        boolean maxAtaque = false;
        boolean minDefensa = false;
        boolean maxDefensa = false;
        boolean minVelocidad = false;
        boolean maxVelocidad = false;
        boolean minPuntosVida = false;
        boolean maxPuntosVida = false;

        for (TipoMonstruo tipoMonstruo1 : tipoMonstruo) {

            //Asignación de rangos a los monstruos
            switch (tipoMonstruo1) {
                case Aranha:
                    ataqueMin = 10;
                    ataqueMax = 50;
                    defensaMin = 20;
                    defensaMax = 40;
                    velocidadMin = 40;
                    velocidadMax = 70;
                    puntosVidaMin = 30;
                    puntosVidaMax = 80;
                    break;
                case Orco:
                    ataqueMin = 30;
                    ataqueMax = 80;
                    defensaMin = 30;
                    defensaMax = 50;
                    velocidadMin = 30;
                    velocidadMax = 60;
                    puntosVidaMin = 30;
                    puntosVidaMax = 100;
                    break;
                case Troll:
                    ataqueMin = 60;
                    ataqueMax = 120;
                    defensaMin = 50;
                    defensaMax = 70;
                    velocidadMin = 20;
                    velocidadMax = 40;
                    puntosVidaMin = 100;
                    puntosVidaMax = 200;
                    break;
                case Dragon:
                    ataqueMin = 100;
                    ataqueMax = 200;
                    defensaMin = 60;
                    defensaMax = 100;
                    velocidadMin = 80;
                    velocidadMax = 120;
                    puntosVidaMin = 120;
                    puntosVidaMax = 250;
                    break;
            }

            for (int i = 0; i < 1000; i++) {
                //Generamos monstruos y comprobamos que los atributos están en el rango permitido
                monstruo = generarMonstruo(tipoMonstruo1);

                assertTrue(monstruo.ataque >= ataqueMin && monstruo.ataque <= ataqueMax);
                assertTrue(monstruo.defensa >= defensaMin && monstruo.defensa <= defensaMax);
                assertTrue(monstruo.velocidad >= velocidadMin && monstruo.velocidad <= velocidadMax);
                assertTrue(monstruo.puntosVida >= puntosVidaMin && monstruo.puntosVida <= puntosVidaMax);

                assertFalse(monstruo.ataque < ataqueMin && monstruo.ataque > ataqueMax);
                assertFalse(monstruo.defensa < defensaMin && monstruo.defensa > defensaMax);
                assertFalse(monstruo.velocidad < velocidadMin && monstruo.velocidad > velocidadMax);
                assertFalse(monstruo.puntosVida < puntosVidaMin && monstruo.puntosVida > puntosVidaMax);

                if (monstruo.ataque == ataqueMin) {
                    minAtaque = true;
                }
                if (monstruo.ataque == ataqueMax) {
                    maxAtaque = true;
                }
                if (monstruo.defensa == defensaMin) {
                    minDefensa = true;
                }
                if (monstruo.defensa == defensaMax) {
                    maxDefensa = true;
                }
                if (monstruo.velocidad == velocidadMin) {
                    minVelocidad = true;
                }
                if (monstruo.velocidad == velocidadMax) {
                    maxVelocidad = true;
                }
                if (monstruo.puntosVida == puntosVidaMin) {
                    minPuntosVida = true;
                }
                if (monstruo.puntosVida == puntosVidaMax) {
                    maxPuntosVida = true;
                }

            }
        }
        //Verificamos que se han tocado todos los valores límite
        assertTrue(minAtaque && maxAtaque && minDefensa && maxDefensa && minVelocidad && maxVelocidad && minPuntosVida && maxPuntosVida);
    }

    //Generar monstruo
    private Monstruo generarMonstruo(TipoMonstruo tipoMonstruo1) {
        switch (tipoMonstruo1) {
            case Aranha:
                return new Aranha();
            case Orco:
                return new Orco();
            case Troll:
                return new Troll();
            case Dragon:
                return new Dragon();
        }
        return null;
    }

}

