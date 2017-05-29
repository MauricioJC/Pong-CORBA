import Pong.ServidorPong;
import Pong.ServidorPongHelper;
import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.PortableServer.POAManagerPackage.AdapterInactive;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;


public class Servidor {
    public static void main(String[] args) throws InvalidName, AdapterInactive, ServantNotActive, WrongPolicy, org.omg.CosNaming.NamingContextPackage.InvalidName, NotFound, CannotProceed {
        ORB orb = ORB.init(args, null);
        POA root = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
        root.the_POAManager().activate();

        ServidorPongImpl chatServidorImpl = new ServidorPongImpl();
        org.omg.CORBA.Object ref = root.servant_to_reference(chatServidorImpl);
        ServidorPong href = ServidorPongHelper.narrow(ref);

        org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
        NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
        NameComponent path[] = ncRef.to_name("ServidorPONG");
        ncRef.rebind(path,href);

        System.out.println("Listo...");
        orb.run();
    }
}