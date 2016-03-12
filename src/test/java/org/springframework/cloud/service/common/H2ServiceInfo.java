package org.springframework.cloud.service.common;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.service.ServiceInfo.ServiceLabel;

@ServiceLabel("h2")
public class H2ServiceInfo extends RelationalServiceInfo {
	
    public static final String JDBC_URL_TYPE = "h2";

    public static final String URI_SCHEME = JDBC_URL_TYPE;

    
    public H2ServiceInfo(String id, String cloudUri) {
        super(id, cloudUri, convertToH2Url(cloudUri), URI_SCHEME);
        
    }
	private static String convertToH2Url(String cloudUri) {
		return new StringBuilder("jdbc:h2:").append(StringUtils.substringAfter(cloudUri, "h2://")).toString();
	}
	
}
