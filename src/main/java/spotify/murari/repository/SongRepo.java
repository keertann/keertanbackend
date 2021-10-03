package spotify.murari.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spotify.murari.entity.Song;

@Repository
public interface SongRepo extends JpaRepository<Song, String>{

}
