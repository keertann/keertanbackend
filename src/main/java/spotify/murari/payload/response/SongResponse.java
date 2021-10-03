package spotify.murari.payload.response;

import java.util.List;

import lombok.Data;

@Data
public class SongResponse {

	private String Id;
	private String imageUri;
	private String uri;
	private String title;
	private List<String> artists;
	private String albumId;
}
