package uploadfile.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import lombok.Data;

@Data
public class AlbumAdminViewDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private String albumName;
	private LocalDate albumRegistrationDate;
	private Set<String> imageNames=new HashSet<>();
}
