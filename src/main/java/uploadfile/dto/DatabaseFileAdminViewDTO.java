package uploadfile.dto;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Data;

@Data
public class DatabaseFileAdminViewDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String fileName;
	private String fileType;
	private LocalDate imageRegistrationDate;
	private byte[] data;
}
