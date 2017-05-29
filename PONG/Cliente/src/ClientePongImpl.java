import Pong.ClientePongPOA;

public class ClientePongImpl extends ClientePongPOA {
    @Override
    public void actualizarTablero(int r1, int r2, int x, int y, int m1, int m2) {
        Game.r1 = r1;
        Game.r2 = r2;
        Game.x = x;
        Game.y = y;
        Game.scoreJ1 = m1;
        Game.scoreJ2 = m2;
    }
}
