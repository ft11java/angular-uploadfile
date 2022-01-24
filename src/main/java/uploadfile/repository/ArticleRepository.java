package uploadfile.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uploadfile.model.Article;

public interface ArticleRepository extends JpaRepository<Article, String> {

}
