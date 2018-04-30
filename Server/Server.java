import HangmanGame.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;
import java.util.*;
import java.io.*;

public class Server {
	public static void main(String[] args) {
		String name_service="hangman";
		try {

			ORB orb = ORB.init(args, null);
			POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootpoa.the_POAManager().activate();
			HangmanImpl converter = new HangmanImpl();
			org.omg.CORBA.Object service_ref =  rootpoa.servant_to_reference(converter);
    	Hangman service_href= HangmanHelper.narrow(service_ref);
    	org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
    	NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
    	NameComponent nc = new NameComponent(name_service, "");
  		NameComponent path[] = {nc};
   		ncRef.rebind(path, service_href);

   		System.out.println("The Server is now running!");
   		Scanner kbd = new Scanner(System.in);

			while(true) {
   			kbd.nextLine();
			}

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
