package uploadfile.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
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
import uploadfile.dto.ArticleAdminViewDTO;
import uploadfile.dto.ArticleCreateDTO;
import uploadfile.exception.FileStorageException;
import uploadfile.exception.NotFoundException;
import uploadfile.model.Article;
import uploadfile.repository.ArticleRepository;

@Service
@RequiredArgsConstructor
public class ArticleService {

	private final ArticleRepository articleRepository;
	private final ModelMapper modelMapper;
	private final ServletContext context;
	
	
	public void articleCreate(ArticleCreateDTO articleCreateDTO, MultipartFile multipartFile) throws IOException {
		
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

		if (fileName.contains(".."))
			throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
		LocalDate date = LocalDate.now();
		
		final String uid = UUID.randomUUID().toString();
		String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
		System.out.println(extension);
		fileName=uid+"."+extension;
		String uploadDir = "article-photos/";
		 Path uploadPath = Paths.get(uploadDir);
		   
	        try (InputStream inputStream = multipartFile.getInputStream()) {
	            Path filePath = uploadPath.resolve(fileName);
	            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
	        } catch (IOException ioe) {        
	            throw new IOException("Could not save image file: " + fileName, ioe);
	        }
	        
	        Article article=articleRepository.save(new Article(
	        		articleCreateDTO.getNewsTitle()
	        		,articleCreateDTO.getNewsBody(),
	        		articleCreateDTO.isDeploy(),
	        		date,
	        		fileName));
	}


	public ArticleAdminViewDTO getArticleById(String id) {
		final Article article=articleRepository.findById(id).orElseThrow(() -> new NotFoundException("Yazı Bulunamadı"));
		
		return modelMapper.map(article, ArticleAdminViewDTO.class);
	}


	public List<ArticleAdminViewDTO> getAllArticle() {
	
		return articleRepository.findAll().stream().map(article->modelMapper.map(article, ArticleAdminViewDTO.class)).collect(Collectors.toList());
	}
	
	
}
