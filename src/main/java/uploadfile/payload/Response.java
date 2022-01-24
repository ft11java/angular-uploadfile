package uploadfile.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Response {
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;  
}
