package uploadfile.dto;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Data;
import uploadfile.model.DatabaseFile;

@Data
public class NewsAdminViewDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String newsTitle;
	private String newsBody;
	private boolean deploy;
	private LocalDate newsRegistrationDate;
	private DatabaseFile databaseFile;

}
