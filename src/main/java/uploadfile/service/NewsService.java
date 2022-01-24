package uploadfile.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;

import org.apache.commons.io.FilenameUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import uploadfile.dto.NewsAdminViewDTO;
import uploadfile.dto.NewsCreateeDTO;
import uploadfile.exception.FileStorageException;
import uploadfile.exception.NotFoundException;
import uploadfile.model.DatabaseFile;
import uploadfile.model.News;
import uploadfile.repository.NewsRepository;

@Service
@RequiredArgsConstructor
public class NewsService {
	private final NewsRepository newsRepository;
	private final ModelMapper modelMapper;
	private final ServletContext context;

	public NewsAdminViewDTO getNewsById(String id) {
		final News news = newsRepository.findById(id).orElseThrow(() -> new NotFoundException("Haber Bulunamadı"));

		return modelMapper.map(news, NewsAdminViewDTO.class);

	}
	
	public List<NewsAdminViewDTO> getAllNews(){
		return newsRepository.findAll().stream().map(news->modelMapper.map(news,NewsAdminViewDTO.class)).collect(Collectors.toList());
		
	
	}

	public NewsAdminViewDTO createNews(NewsCreateeDTO newsCreateeDTO, MultipartFile multipartFile) throws IOException {

		
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

		if (fileName.contains(".."))
			throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);

		LocalDate date = LocalDate.now();
		final DatabaseFile databaseFile = new DatabaseFile(fileName, multipartFile.getContentType(),
				date,  multipartFile.getBytes());

		final News news = new News(newsCreateeDTO.getNewsTitle(), newsCreateeDTO.getNewsBody(),
				newsCreateeDTO.isDeploy(), date, databaseFile);
		newsRepository.save(news);
		
		final String uid = UUID.randomUUID().toString();
		String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
		System.out.println(extension);
		fileName=uid+"."+extension;
		String uploadDir = "article-photos/";
		 Path uploadPath = Paths.get(uploadDir);
		   if (!Files.exists(uploadPath)) {
	            Files.createDirectories(uploadPath);
	        
		   }
	        try (InputStream inputStream = multipartFile.getInputStream()) {
	            Path filePath = uploadPath.resolve(fileName);
	            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
	        } catch (IOException ioe) {        
	            throw new IOException("Could not save image file: " + fileName, ioe);
	        }  
		
		return modelMapper.map(news,NewsAdminViewDTO.class) ;

	}

	public String getimage(String imagename) {
		String image=null;
		String filesPath=context.getRealPath("article-photos/"+imagename);
		File fileimage=new File("article-photos/"+imagename);
		String encodeBase64=null;
		try {
			String extension=FilenameUtils.getExtension(imagename);
			FileInputStream fileInputStream=new FileInputStream(fileimage);
			byte[] bytes=new byte[(int)fileimage.length()];
			fileInputStream.read(bytes);
			encodeBase64=Base64.getEncoder().encodeToString(bytes);
			image=("data:image/jpeg;base64,"+encodeBase64);
			System.out.println(image);
			fileInputStream.close();
			
		}catch(Exception e) {
			
		}
		
		return image;
	}

}
