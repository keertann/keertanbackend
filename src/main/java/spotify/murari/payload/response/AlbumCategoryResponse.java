package spotify.murari.payload.response;

import java.util.List;

import lombok.Data;
import spotify.murari.entity.Album;

@Data
public class AlbumCategoryResponse {

	private String id;
	private String title;
	private List<Album> albums;
}
