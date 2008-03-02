import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import de.businesslogics.bcs.appetizer.HIALetter;
import de.businesslogics.bcs.appetizer.INILetter;
import de.businesslogics.ebics.client.EbicsBank;
import de.businesslogics.ebics.client.EbicsBankImpl;
import de.businesslogics.ebics.client.EbicsPartner;
import de.businesslogics.ebics.client.EbicsPartnerImpl;
import de.businesslogics.ebics.client.EbicsSession;
import de.businesslogics.ebics.client.EbicsUser;
import de.businesslogics.ebics.client.KeyManagement;
import de.businesslogics.ebics.schema.response.EbicsException;

public class Transfer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		EbicsBank bank;
		try {
			bank = new EbicsBankImpl("dresdner", new URL(
					"https://ebics.dresdner-bank.de/cs/CS"),
					EbicsSession.ANY_DIGEST, EbicsSession.ANY_DIGEST);

			EbicsPartner partner = new EbicsPartnerImpl(bank, "dresdner");

			EbicsUserImpl user = new EbicsUserImpl(partner, "U651440Z", null);

			EbicsSession s = new EbicsSession(user);
            KeyManagement kmg = new KeyManagement(s);
            
            if(!user.isInitialized())
            {
                kmg.sendINI(user.getA004PublicKey());
                user.setInitialized(true);
            }
            if(!user.isInitializedHIA())
            {
                kmg.sendHIA(user.getE001PublicKey(), user.getX001PublicKey());
                user.setInitializedHIA(true);
            }
            //INILetter.printINI(new FileOutputStream(new File(bankDir, (new StringBuilder()).append(params[6]).append(".INI.pdf").toString())), params[4], params[5], params[6], "A004", user.getA004PublicKey(), new Date());
            //HIALetter.printHIA(new FileOutputStream(new File(bankDir, (new StringBuilder()).append(params[6]).append(".HIA.pdf").toString())), params[4], params[5], params[6], user.getE001PublicKey(), user.getX001PublicKey(), new Date());
      
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EbicsException ebics) {
			System.out.println(ebics.getLocalizedMessage());
		} catch (IOException io) {
			io.printStackTrace();
		}

	}

}
