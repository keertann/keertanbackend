package spotify.murari.payload.request;

import java.util.List;

import lombok.Data;

@Data
public class SongRequest {

	//private String songId;
	private String imageUri;
	private String uri;
	private String title;
	private List<String> artists;
	private String albumId;
}
