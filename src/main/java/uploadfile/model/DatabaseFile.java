package uploadfile.model;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

import javax.persistence.*;
@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "files")
public class DatabaseFile {
	@Id
    @GeneratedValue(generator = "UUID")
	@GenericGenerator(name="UUID",strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
	 private String id;

	@Column(name="file_name")
	private String fileName;

	@Column(name="file_type")
	private String fileType;
	
	@Column(name="image_registration_date")
	private LocalDate imageRegistrationDate;

	@Lob
	private byte[] data;

	public DatabaseFile(String fileName, String fileType, LocalDate date, byte[] data) {
		this.fileName = fileName;
		this.fileType = fileType;
		this.imageRegistrationDate = date;
		this.data = data;
		
	}
}
