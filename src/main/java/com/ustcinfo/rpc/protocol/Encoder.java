package com.ustcinfo.rpc.protocol;

public interface Encoder {

	/**
	 * Encode Object to byte[]
	 */
	public byte[] encode(Object object) throws Exception;
	
}
