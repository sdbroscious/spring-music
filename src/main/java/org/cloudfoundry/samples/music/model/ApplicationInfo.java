package org.cloudfoundry.samples.music.model;

import lombok.Data;

@Data
public class ApplicationInfo {
    
	private String[] profiles;
    private String[] services;
	public ApplicationInfo(String[] profiles, String[] services) {
		super();
		this.profiles = profiles;
		this.services = services;
	}

    
}
