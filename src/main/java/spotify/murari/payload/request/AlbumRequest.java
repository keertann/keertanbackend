package spotify.murari.payload.request;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class AlbumRequest {

	private String id;
	@NotBlank(message = "{album.albumName.absent}")
	private String albumName;
	private String uploadedBy;
	
	private Integer numberOfLikes;
	@NotBlank(message = "{album.imageUri.absent}")
	private String imageUri;
	@NotBlank(message = "{album.categoryid.absent}")
	private String categoryId;
}
