package com.example.workshopmongospring.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.example.workshopmongospring.domain.Post;
import com.example.workshopmongospring.domain.User;
import com.example.workshopmongospring.dto.AuthorDTO;
import com.example.workshopmongospring.dto.CommentDTO;
import com.example.workshopmongospring.repository.PostRepository;
import com.example.workshopmongospring.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone(("GMT")));

		userRepository.deleteAll();
		postRepository.deleteAll();

		User maria = new User(null, "Maria Brown", "maria@email.com");
		User alex = new User(null, "Alex Green", "alex@email.com");
		User bob = new User(null, "Bob Grey", "bob@email.com");

		userRepository.saveAll(Arrays.asList(maria, alex, bob));

		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu Viagem", "Vou viajar para SP... Good bye!",
				new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom dia", "Acordei feliz hoje!", new AuthorDTO(maria));
		
		CommentDTO c1  = new CommentDTO("Boa viagem mano!", sdf.parse("21/03/2018"), new AuthorDTO(alex));
		CommentDTO c2  = new CommentDTO("Aproveite", sdf.parse("22/03/2018"), new AuthorDTO(bob));
		CommentDTO c3  = new CommentDTO("Tenha uma boa viagem...", sdf.parse("23/03/2018"), new AuthorDTO(alex));
		
		post1.getComennts().addAll(Arrays.asList(c1,c2));
		post2.getComennts().addAll(Arrays.asList(c3));
		

		postRepository.saveAll(Arrays.asList(post1, post2));

		maria.setPosts(Arrays.asList(post1, post2));
		userRepository.save(maria);
	}

}
