package uploadfile.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import uploadfile.exception.FileStorageException;


@Service
@RequiredArgsConstructor
public class ImageService {
	private final AlbumService albumService;
	private final ServletContext context;
	private final String FILE_PATH_ROOT = "../article-images/";

	public byte[] getImageView(String imageName) {
		byte[] image = new byte[0];
		try {
			image = FileUtils.readFileToByteArray(new File(FILE_PATH_ROOT + imageName));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return image;
	}

	public void uploadImage(String albumId, MultipartFile multipartFile) throws IOException {

		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

		if (fileName.contains(".."))
			throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
		LocalDate date = LocalDate.now();

		final String uid = UUID.randomUUID().toString();
		String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
		fileName = uid + "." + extension;
		String uploadDir = "../article-images/";
		//String uploadDir = "src/main/resources/static/article-images/";
		Path uploadPath = Paths.get(uploadDir);
		try (InputStream inputStream = multipartFile.getInputStream()) {
			Path filePath = uploadPath.resolve(fileName);
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
			albumService.albumAddImage(albumId, fileName);
		} catch (IOException ioe) {
			throw new IOException("Could not save image file: " + fileName, ioe);
		}
	}

}
