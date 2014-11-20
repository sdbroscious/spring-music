package org.cloudfoundry.samples.music.web;

import lombok.Data;

import org.cloudfoundry.samples.music.model.Album;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import com.jayway.jsonpath.JsonPath;

public class AlbumArtworkHelper {

	private static final Logger log = LoggerFactory.getLogger(AlbumArtworkHelper.class);
	
	private static final String artworkQueryUrlTemplate = "https://itunes.apple.com/search?term=%s %s&entity=album&limit=1";

	public static String artworkUrlFor(Album album) {
		String artworkUrl = null;
		
		String iTunesSearchUrl = String.format(artworkQueryUrlTemplate, album.getArtist(), album.getTitle());
		
		log.info("Loading album artwork info from {}", iTunesSearchUrl);
		String iTunesResult = new RestTemplate().getForObject(iTunesSearchUrl, String.class);
		
		log.debug("iTunes Search Result: {}", iTunesResult);
		artworkUrl = extractArtworkUrl(iTunesResult);
		
		log.info("Using artworkURL: {}", artworkUrl);
		return artworkUrl;

	}
	
	static String extractArtworkUrl(String searchResults) {
		String url = null;
		
		if (searchResults != null && 
				JsonPath.parse(searchResults).read("$.resultCount", Integer.class) > 0) {
		
			url = JsonPath.parse(searchResults).read("$.results[0].artworkUrl100");
		}
		
		return url;
	}
	
	@Data
	static class ArtworkUrlData {
		String artistName;
		String collectionName;
		String artworkUrl100;
	}
	
}
