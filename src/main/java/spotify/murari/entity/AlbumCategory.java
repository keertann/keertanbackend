package spotify.murari.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "album_category")
public class AlbumCategory extends BaseEntity{

//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private Long categoryId;
	private String title;
	//1..*
	@JsonManagedReference
	@OneToMany(targetEntity = Album.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id",referencedColumnName = "id")
	private List<Album> albums;
}
