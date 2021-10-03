package spotify.murari.payload.request;

import java.util.List;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import spotify.murari.entity.Album;

@Data
public class AlbumCategoryRequest {

	//private String id;
	@NotBlank(message = "{albumcategory.title.absent}")
	private String title;
	//private List<Album> albums;
}
