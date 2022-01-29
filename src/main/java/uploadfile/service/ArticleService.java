package uploadfile.service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import uploadfile.dto.ArticleAdminViewDTO;
import uploadfile.dto.ArticleCreateDTO;
import uploadfile.exception.NotFoundException;
import uploadfile.model.Article;
import uploadfile.repository.ArticleRepository;

@Service
@RequiredArgsConstructor
public class ArticleService {

	private final ArticleRepository articleRepository;
	private final ModelMapper modelMapper;

	
	
	public void articleCreate(ArticleCreateDTO articleCreateDTO) {
			     
	       			articleRepository.save(new Article(
	        		articleCreateDTO.getNewsTitle()
	        		,articleCreateDTO.getNewsBody(),
	        		articleCreateDTO.isDeploy(),
	        		LocalDate.now(),
	        		articleCreateDTO.getImageName()));
	}


	public ArticleAdminViewDTO getArticleById(String id) {
		final Article article=articleRepository.findById(id).orElseThrow(() -> new NotFoundException("Yazı Bulunamadı"));
		
		return modelMapper.map(article, ArticleAdminViewDTO.class);
	}


	public List<ArticleAdminViewDTO> getAllArticle() {
	
		return articleRepository.findAll().stream().map(article->modelMapper.map(article, ArticleAdminViewDTO.class)).collect(Collectors.toList());
	}
	
	
}
