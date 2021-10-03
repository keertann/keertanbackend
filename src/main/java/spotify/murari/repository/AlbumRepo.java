package spotify.murari.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spotify.murari.entity.Album;

@Repository
public interface AlbumRepo extends JpaRepository<Album, String>{

}
