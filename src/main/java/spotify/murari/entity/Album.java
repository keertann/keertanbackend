package spotify.murari.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "album")
public class Album extends BaseEntity{

	private String albumName;
	private String uploadedBy;
	private Integer numberOfLikes;
	private String imageUri;
	
	@ElementCollection(targetClass=String.class)
	private List<String> artistHeadlines;
	//1..*
	@JsonManagedReference
	@OneToMany(targetEntity = Song.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "album_id",referencedColumnName = "id")
	private List<Song> songs;
	
	//*..1
	@JsonBackReference
	@ManyToOne(targetEntity = AlbumCategory.class,fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id",referencedColumnName = "id")
	private AlbumCategory category;
}

