package net.kerfuffle.utilities.network;

import java.io.IOException;

public abstract class MyCode {

	/**
	 * Only really meant for this network library
	 * @param p
	 * @throws IOException
	 */
	public abstract void run(Packet p) throws IOException;
	
}
