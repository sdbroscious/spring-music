package org.cloudfoundry.samples.music.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApplicationInfo {
    
	private String[] profiles;
    private String[] services;

}
