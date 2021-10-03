package spotify.murari.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spotify.murari.entity.AlbumCategory;

@Repository
public interface AlbumCategoryRepo extends JpaRepository<AlbumCategory, String>{

}
