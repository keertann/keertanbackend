package spotify.murari.entity;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "song")
public class Song extends BaseEntity{

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long songId;
	private String imageUri;
	private String uri;
	private String title;
	
	@ElementCollection(targetClass = String.class)
	private List<String> artists;
	
	@JsonBackReference
	@ManyToOne(targetEntity = Album.class)
	@JoinColumn(name = "album_id",referencedColumnName = "id")
	private Album album;
}
