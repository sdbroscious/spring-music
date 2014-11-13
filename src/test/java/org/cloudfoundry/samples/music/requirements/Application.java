package org.cloudfoundry.samples.music.requirements;

import net.thucydides.core.annotations.Feature;

public class Application {
	
    @Feature
    public class ManageAlbums {
        public class AddNewAlbum {}
        public class DeleteAlbum {}
        public class LookupAlbum {}
        public class ListAlbums {}
    }
    
}
