package org.cloudfoundry.samples.music.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.cloudfoundry.samples.music.model.ApplicationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.Cloud;
import org.springframework.cloud.service.ServiceInfo;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {
    
	@Autowired(required = false)
    private Cloud cloud;

    private Environment springEnvironment;

    @Autowired
    public InfoController(Environment springEnvironment) {
        this.springEnvironment = springEnvironment;
    }

    @RequestMapping(value = "/info")
    public ApplicationInfo info() {
        return new ApplicationInfo(springEnvironment.getActiveProfiles(), getServiceNames());
    }

    @RequestMapping(value = "/env")
    public Map<String, String> showEnvironment() {
        return System.getenv();
    }

    @RequestMapping(value = "/service")
    public List<ServiceInfo> showServiceInfo() {
    	
    	List<ServiceInfo> serviceInfos = new ArrayList<ServiceInfo>();
    	
        if (cloud != null) {
        	serviceInfos = cloud.getServiceInfos();
        }
        
        return serviceInfos;
    }

    private String[] getServiceNames() {
    	
    	String[] serviceNames = new String[] {};
    	
        final List<ServiceInfo> serviceInfos = this.showServiceInfo();

        List<String> names = new ArrayList<String>();
        for (ServiceInfo serviceInfo : serviceInfos) {
            names.add(serviceInfo.getId());
        }
        serviceNames = names.toArray(new String[names.size()]);

        return serviceNames;
    }
}