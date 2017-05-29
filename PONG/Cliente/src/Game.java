import Pong.ClientePong;
import Pong.ServidorPong;
import Pong.ServidorPongHelper;
import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import javax.swing.*;

public class Game extends JFrame {
    public static String nombre;
    private final int WIDTH = 900, HEIGHT = 500;
    public static ServidorPong servidor;
    public static Tablero tablero;
    public static int jugador=1, x=430,y=200, r1=200,r2=200, scoreJ1=0, scoreJ2=0;

    public Game() {
        this.setTitle("PONG - "+nombre);
        this.setSize(WIDTH, HEIGHT);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(tablero);
        this.addKeyListener(new Mando());
        this.setVisible(true);
    }

    public static void main(String[] args) throws InvalidName, CannotProceed, org.omg.CosNaming.NamingContextPackage.InvalidName, NotFound {
        Game.nombre = args[0];
        Game.tablero = new Tablero();
        new Game();

        new Thread() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Game.tablero.repaint();
                }
            }
        }.start();

        ORB orb = ORB.init(args, null);
        ClientePongImpl cci = new ClientePongImpl();
        ClientePong cc = cci._this(orb);
        org.omg.CORBA.Object rootRef = orb.resolve_initial_references("NameService");
        NamingContextExt ncRef = NamingContextExtHelper.narrow(rootRef);
        Game.servidor = ServidorPongHelper.narrow(ncRef.resolve_str("ServidorPONG"));
        new Thread(new Servicio(orb)).start();
        Game.jugador = servidor.conectar(cc);

    }
}
