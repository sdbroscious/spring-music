package org.cloudfoundry.samples.music.repositories;

import org.cloudfoundry.samples.music.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends JpaRepository<Album, String> {

}
