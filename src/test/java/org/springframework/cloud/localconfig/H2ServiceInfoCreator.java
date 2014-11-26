package org.springframework.cloud.localconfig;

import org.springframework.cloud.service.common.H2ServiceInfo;

public class H2ServiceInfoCreator extends LocalConfigServiceInfoCreator<H2ServiceInfo> {

	public H2ServiceInfoCreator() {
		super(H2ServiceInfo.URI_SCHEME);
	}

	@Override
	public H2ServiceInfo createServiceInfo(String id, String uri) {
		return new H2ServiceInfo(id, uri);
	}

}
