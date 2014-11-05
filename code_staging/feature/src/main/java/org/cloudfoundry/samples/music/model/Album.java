package org.cloudfoundry.samples.music.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
public class Album {
    
	@Id
	@GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String title;
    private String artist;
    private String releaseYear;
    private String genre;
    private Integer rating;
    private String artworkUrl;

    public Album() {
    }

    public Album(String title, String artist, String releaseYear, String genre) {
        this.title = title;
        this.artist = artist;
        this.releaseYear = releaseYear;
        this.genre = genre;
    }

}
