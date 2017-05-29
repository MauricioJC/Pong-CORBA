import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Mando extends KeyAdapter {
    @Override
    public void keyPressed(KeyEvent e) {
        int y;
        if (Game.jugador == 1) {
            y = Game.r1;
        } else {
            y = Game.r2;
        }
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                if (y > 5) {
                    Game.servidor.up(Game.jugador);
                }
                break;
            case KeyEvent.VK_DOWN:
                if (y < 470 - 80) {
                    Game.servidor.down(Game.jugador);
                }
                break;
            default:
        }
    }
}
