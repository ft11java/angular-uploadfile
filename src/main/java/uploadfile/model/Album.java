package uploadfile.model;

import java.time.LocalDate;

import java.util.HashSet;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "album")
public class Album {
	
	@Id
    @GeneratedValue(generator = "UUID")
	@GenericGenerator(name="UUID",strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
	 private String id;
	
	@Column(name="album_name", nullable = false)
	private String albumName;
	
	@Column(name="album_registration_date", nullable = false)
	private LocalDate albumRegistrationDate;
	
	@Column(name="image_name")
	@ElementCollection(targetClass=String.class)
	private Set<String> imageNames=new HashSet<>();

	public Album(String albumName, LocalDate albumRegistrationDate) {
		
		this.albumName = albumName;
		this.albumRegistrationDate = albumRegistrationDate;
		
	}

}
