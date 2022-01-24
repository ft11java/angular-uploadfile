package uploadfile.model;



import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
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
@Table(name = "news")
public class News {

	@Id
    @GeneratedValue(generator = "UUID")
	@GenericGenerator(name="UUID",strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
	 private String id;
	
	@Column(name="news_title")
	private String newsTitle;
	
	@Lob
	@Column(name="news_body")
	private String newsBody;
		
	@Column(name="deploy")
	private boolean deploy;
	
	@Column(name="news_registration_date")
	private LocalDate newsRegistrationDate;
	
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name="databaseFile_id")
	private DatabaseFile databaseFile;

		public News(String news_title, String news_body, boolean deploy, LocalDate newsRegistrationDate,
			DatabaseFile dabaseFile) {
			this.newsTitle = news_title;
			this.newsBody = news_body;
			this.deploy = deploy;
			this.newsRegistrationDate = newsRegistrationDate;
			this.databaseFile = dabaseFile;
		}
	 	
	 	
}
