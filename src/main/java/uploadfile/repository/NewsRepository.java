package uploadfile.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uploadfile.model.News;

public interface NewsRepository extends JpaRepository<News, String>{

}
