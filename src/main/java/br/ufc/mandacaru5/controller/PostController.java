package br.ufc.mandacaru5.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufc.mandacaru5.model.Post;
import br.ufc.mandacaru5.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Post")
@RestController
@RequestMapping(path = "/api/posts")
public class PostController {

	@Autowired
	PostService service;

	@Operation(summary = "Get all posts")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Found the posts", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Post.class))) }) })
	@GetMapping
	public ResponseEntity<List<Post>> findAll() {
		return new ResponseEntity<List<Post>>(service.findAllPostsByRequest(), HttpStatus.OK);
	}

	@Operation(summary = "Get one post by id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Found the post"),
			@ApiResponse(responseCode = "404", description = "Not Found the post", content = @Content) })
	@GetMapping(path = "{id}")
	public ResponseEntity<Post> find(@Parameter(description = "id of post to be searched") @PathVariable("id") int id) {
		Post post = service.findPostByRequest(id);

		if (post != null) {
			return new ResponseEntity<Post>(post, HttpStatus.OK);
		} else {
			return new ResponseEntity<Post>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	public ResponseEntity<Post> save(@RequestBody Post post) {
		return new ResponseEntity<Post>(service.saveByRequest(post), HttpStatus.OK);
	}

	@PutMapping(path = "{id}")
	public ResponseEntity<Post> update(@PathVariable("id") int id, @RequestBody Post post) {
		return new ResponseEntity<Post>(service.updateByRequest(id, post), HttpStatus.OK);
	}

	@DeleteMapping(path = "{id}")
	public void delete(@PathVariable("id") int id) {
		service.deleteByRequest(id);
	}
}
