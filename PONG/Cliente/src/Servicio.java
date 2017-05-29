import org.omg.CORBA.ORB;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

public class Servicio implements Runnable {
    private ORB orb;

    public Servicio(ORB orb) {
        this.orb = orb;
    }

    @Override
    public void run() {
        try {
            POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootPOA.the_POAManager().activate();
            orb.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
