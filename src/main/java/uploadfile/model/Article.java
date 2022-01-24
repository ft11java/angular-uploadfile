package uploadfile.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name ="article")
public class Article {
	@Id
    @GeneratedValue(generator = "UUID")
	@GenericGenerator(name="UUID",strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
	 private String id;
	
	@Column(name="news_title")
	private String newsTitle;
	
	
	@Column(name="news_body")
	private String newsBody;
		
	@Column(name="deploy")
	private boolean deploy;
	
	@Column(name="news_registration_date")
	private LocalDate newsRegistrationDate;
	
	@Column(name="image_name")
	private String imageName;

	public Article(String newsTitle, String newsBody, boolean deploy, LocalDate newsRegistrationDate,
			String imageName) {
		this.newsTitle = newsTitle;
		this.newsBody = newsBody;
		this.deploy = deploy;
		this.newsRegistrationDate = newsRegistrationDate;
		this.imageName = imageName;
	}
	
	
	
}
