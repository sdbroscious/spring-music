package org.cloudfoundry.samples.music.web;

import java.net.URL;

import lombok.Data;

import org.cloudfoundry.samples.music.model.Album;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AlbumArtworkHelper {

	private static final Logger log = LoggerFactory.getLogger(AlbumArtworkHelper.class);
	
	private static final String artworkQueryUrlTemplate = "http://playground.bendodson.com/itunes-artwork-finder/?query=%s %s&entity=album&country=us";

	public static String artworkThumbnailUrlFor(Album album) {
		
		return convertToThumbnail(artworkUrlFor(album));

	}
	
	public static String artworkUrlFor(Album album) {
		String artworkUrl = null;
		
		String artworkQueryUrl = String.format(artworkQueryUrlTemplate,
				album.getArtist(), album.getTitle()).replace(" ", "+");

		log.info("Loading album artwork URL from '{}'", artworkQueryUrl);

		ArtworkUrlData[] urlData = new ArtworkUrlData[] {};
		try {
			urlData = new ObjectMapper()
					.readValue(new URL(artworkQueryUrl), ArtworkUrlData[].class);
		} catch (Exception e) {
			log.error("Error loading artwork data: ", e);
		}

		artworkUrl = urlData.length > 0 ? urlData[0].getUrl() : null;
		
		log.info("artworkUrl = {}", artworkUrl);
		
		return artworkUrl;

	}

	public static String convertToThumbnail(String imageUrl) {
		String urlWithSmallPic = null;

		if (imageUrl != null) {
			urlWithSmallPic = imageUrl.replace(".600x600-", ".100x100-");
		}

		return urlWithSmallPic;
	}
	
	@Data
	static class ArtworkUrlData {
		String url;
		String hires;
		String title;
		int width;
		int height;
		String warning;
	}
	
}
