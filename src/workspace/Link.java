package workspace;

import javax.swing.JOptionPane;

public class Link {
	public static void main(String[] args) {
		String[][] carrierData = { { "Verizon", "@vtext.com" },
				{ "T-Mobile", "@tmomail.net" }, { "AT&T", "@txt.att.net" },
				{ "Sprint", "@messaging.sprintpcs.com" },
				{ "Virgin Mobile", "@vmobl.com" } };
		String[] carriers = { "Verizon", "T-Mobile", "AT&T", "Sprint",
				"Virgin Mobile" };

		String carrierSMSAddr = "";

		String selectedCarrier = (String) JOptionPane.showInputDialog(null,
				"Which carrier would you like to use?", "Select Carrier", -1,
				null, carriers, "Verizon");

		if (!(selectedCarrier == null) && !selectedCarrier.isEmpty()) {
			for (String[] carrier : carrierData) {
				if (carrier[0].equals(selectedCarrier)) {
					carrierSMSAddr = carrier[1];
				}
			}
		}

		if (!carrierSMSAddr.isEmpty()) {
			String sendAddr = JOptionPane
					.showInputDialog(null,
							"Input the phone number that you'd like to send your text to.");

			if ((!sendAddr.isEmpty()) && (sendAddr.length() == 10)) {
				String message = JOptionPane
						.showInputDialog(null,
								"Input the text message that you'd like to send to the phone.");

				carrierSMSAddr = sendAddr.concat(carrierSMSAddr);

				SendMessage sendHelper = new SendMessage();

				sendHelper.Send(message, carrierSMSAddr);
			}
		}
	}
}