import Pong.ClientePong;
import Pong.ServidorPongPOA;

public class ServidorPongImpl extends ServidorPongPOA {
    public static ClientePong jugadorUno;
    public static ClientePong jugadorDos;
    public static int jugadorUnoY=200;
    public static int jugadorDosY=200;
    public static int x=430;
    public static int y=200;
    public static int scoreJ1=0;
    public static int scoreJ2=0;
    public static final int ANCHO = 100;
    public static final int LARGO = 200;

    public void inicializar(){
        new Thread(new Pelota()).start();
    }

    public static void actualizar(){
        jugadorUno.actualizarTablero(jugadorUnoY, jugadorDosY, x, y, scoreJ1, scoreJ2);
        jugadorDos.actualizarTablero(jugadorUnoY, jugadorDosY, x, y, scoreJ1, scoreJ2);
    }

    @Override
    public int conectar(ClientePong cliente) {
        if (jugadorUno==null){
            jugadorUno=cliente;
            return 1;
        }else{
          if (jugadorDos==null){
              jugadorDos=cliente;
              inicializar();
              return 2;
          }
        }
        actualizar();
        return 0;
    }

    @Override
    public void up(int no) {
        if(no==1){
            jugadorUnoY -=10;
        }else {
            jugadorDosY -=10;
        }
        actualizar();
    }

    @Override
    public void down(int no) {
        if(no==1){
            jugadorUnoY +=10;
        }else {
            jugadorDosY +=10;
        }
        actualizar();
    }

}
