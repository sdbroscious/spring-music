package org.springframework.cloud.service.relational;

import org.springframework.cloud.service.common.H2ServiceInfo;

public class H2DataSourceCreator extends DataSourceCreator<H2ServiceInfo> {
	
	private static final String[] DRIVERS = new String[]{"org.h2.Driver"};
	
	private static final String VALIDATION_QUERY = "/* ping */ SELECT 1";

	public H2DataSourceCreator() {
	    super("spring-cloud.h2.driver", DRIVERS, VALIDATION_QUERY);
	}

}
