package org.cloudfoundry.samples.music.test;

import org.springframework.http.MediaType;

public final class AlbumResources {

	public static String CONTENT_TYPE = MediaType.APPLICATION_JSON_VALUE;
	
	static String ALBUMS_PATH = "/albums";
	
	static String ALBUM_PATH = "/albums/%s";
	
	public static String albums() {
		return ALBUMS_PATH;
	}
	
	public static String album(String id) {
		return String.format(ALBUM_PATH, id);
	}

}
