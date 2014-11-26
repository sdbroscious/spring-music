package org.cloudfoundry.samples.music.steps;

import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static org.cloudfoundry.samples.music.test.AlbumResources.album;
import static org.cloudfoundry.samples.music.test.AlbumResources.albums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.cloudfoundry.samples.music.model.Album;
import org.cloudfoundry.samples.music.test.AlbumResources;

import com.jayway.restassured.response.Response;

public class AlbumSteps {

	public static Album givenAlbum(Album album) {
		Album givenAlbum = album;
		
		if (!existsAlbum(album)) {
			givenAlbum = createNewAlbum(album);
		}
		
		return givenAlbum;
	}
	
	public static boolean existsAlbum(Album album) {
		return findAlbumById(album.getId()) != null;
	}
	
	public static Album createNewAlbum(Album album) {
		return
				
		given().
	    	contentType(AlbumResources.CONTENT_TYPE).
	    	body(album).
	    when().
	    	post(albums()).
	    as(Album.class);
	}
	
	public static void deleteAlbum(Album album) {
		
		when().
			delete(album(album.getId())).
		then().
			assertThat().statusCode(200);
		
	}
	
	public static Album updateAlbum(Album album) {
		return
		
		given().
	    	contentType(AlbumResources.CONTENT_TYPE).
	    	body(album).
	    when().
	    	post(album(album.getId())).
	    as(Album.class);
	}
	
	public static Album findAlbumById(String id) {
		
		Album album = null;
		
		if (id != null) {
			Response response = get(album(id)); 
			
			if (response.statusCode() == 200) {
				album = response.as(Album.class);
			}
		}
		
		return album;
	}
	
	public static List<Album> findAllAlbums() {
		return new ArrayList<Album>(Arrays.asList(
				get(albums()).as(Album[].class)));
	}

}
