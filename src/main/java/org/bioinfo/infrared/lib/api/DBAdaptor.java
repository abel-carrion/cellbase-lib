package org.bioinfo.infrared.lib.api;

import org.bioinfo.commons.log.Logger;

public abstract class DBAdaptor {

	protected Logger logger;
	
	public DBAdaptor() {
		logger = new Logger();
		logger.setLevel(Logger.DEBUG_LEVEL);
	}
	
	public Logger getLogger() {
		return logger;
	}
}
