public class Pelota implements Runnable {
    private final int WIDTH = 892, HEIGHT = 471;

    @Override
    public void run() {
        boolean Y = true, X = true;
        int c = 3, v = 2;
        while (true) {
            if(ServidorPongImpl.y >= ServidorPongImpl.jugadorUnoY-c &&
               ServidorPongImpl.y <= ServidorPongImpl.jugadorUnoY+80+c &&
                ServidorPongImpl.x<=25){
                X = false;
            }
            if(ServidorPongImpl.y >= ServidorPongImpl.jugadorDosY-c &&
               ServidorPongImpl.y <= ServidorPongImpl.jugadorDosY+80+c &&
               ServidorPongImpl.x >= 892-55){
                X = true;
            }
            if (ServidorPongImpl.y <= 0) {
                Y = false;
            }
            if (ServidorPongImpl.y >= HEIGHT-30) {
                Y = true;
            }
            if (ServidorPongImpl.x <=0){
                X = false;
                ServidorPongImpl.scoreJ2++;
            }
            if (ServidorPongImpl.x >= WIDTH-30){
                X = true;
                ServidorPongImpl.scoreJ1++;
            }
            if (Y) {
                ServidorPongImpl.y -= v;
            }else {
                ServidorPongImpl.y +=v;
            }
            if (X) {
                ServidorPongImpl.x -=v;
            }else {
                ServidorPongImpl.x +=v;
            }
            ServidorPongImpl.actualizar();
            try {
                Thread.sleep(1);
            } catch (Exception e) {
            }
        }
    }
}
