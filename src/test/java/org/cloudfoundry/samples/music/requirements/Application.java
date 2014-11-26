package org.cloudfoundry.samples.music.requirements;

import net.thucydides.core.annotations.Feature;

public class Application {
	
    @Feature
    public class ManageAlbums {
        public class CreateAlbum {}
        public class UpdateAlbum {}
        public class DeleteAlbum {}
        public class FindAlbums {}
    }
    
    @Feature
    public class AppInspection {
    	public class InspectEnvironment {}
    	public class InspectResiliency {}
    }
}
