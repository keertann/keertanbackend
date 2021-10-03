package spotify.murari.payload.response;

import java.util.List;
import lombok.Data;
import spotify.murari.entity.AlbumCategory;
import spotify.murari.entity.Song;

@Data
public class AlbumResponse {

	private String albumId;
	private String albumName;
	private String uploadedBy;
	private Integer numberOfLikes;
	private String imageUri;
	private List<String> artistHeadlines;
	private List<Song> songs;
	private String categoryId;
}
