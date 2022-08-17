package seu.list.client.bz;

import seu.list.common.IConstant;

public class ClientMainFrame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Client cliThd = new Client(IConstant.SERVER_ADDRESS, IConstant.SERVER_PORT);
		cliThd.run();
	}

}
