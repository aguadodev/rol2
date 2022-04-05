
package rol;


public class Mapa {
    private int FILAS = 20;
    private int COLUMNAS = 20;
    
    enum Terreno {PRADERA, BOSQUE, DESIERTO, COLINA, MONTAÑA, RIO, MAR, VACIO};
    
    protected Terreno mapaFisico[][] = new Terreno[FILAS][COLUMNAS];
    
    public Mapa(){
        for(int i = 0; i < FILAS; i++)
            for (int j = 0; j < COLUMNAS; j++){
                int rnd = (int) (Math.random() * 8);
                mapaFisico[i][j] = Terreno.values()[rnd];
            }
    }

    @Override
    public String toString() {
        String mapa = "";
        
        for(int i = 0; i < FILAS; i++){
             for (int j = 0; j < COLUMNAS; j++){
               /*  switch(mapaFisico[i][j]){
                     
                 }*/
                mapa += mapaFisico[i][j] + " ";
            }         
             mapa += "\n";
        }
       
        return mapa;
    }
    
    
    
}
