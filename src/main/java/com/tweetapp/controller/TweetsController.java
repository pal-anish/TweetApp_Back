

package com.tweetapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.facade.TweetsFacade;
import com.tweetapp.payload.request.TweetRequest;
import com.tweetapp.payload.response.MessageResponse;
import com.tweetapp.payload.response.TweetResponseList;

@CrossOrigin(origins = "http://ec2-18-212-55-150.compute-1.amazonaws.com:3000")
@RestController
@RequestMapping("/")
public class TweetsController {

	private static final Logger LOG = LoggerFactory.getLogger(TweetsController.class);

	@Autowired
	TweetsFacade tweetsFacade;

	private String currentUserName() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth.getName();
	}

	@GetMapping(path = "/all")
	public TweetResponseList getAllTweets() {
		return tweetsFacade.getAllTweets();
	}

	@GetMapping(path = "/{username}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('MODERATOR')")
	public ResponseEntity<?> getAllTweetsByUsername(@PathVariable(required = true) String username) {
		return ResponseEntity.ok(tweetsFacade.getAllTweetsByUsername(username));
	}

	@PostMapping(path = "/{username}/add")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> createTweet(@PathVariable(required = true) String username,
			@RequestBody TweetRequest tweetRequest) {
		LOG.info(currentUserName());
		if (currentUserName().equals(username)) {
			tweetRequest.setUsername(username);
			tweetsFacade.createTweet(tweetRequest);
			return ResponseEntity.ok(new MessageResponse("Tweet created successfully!"));
		} else {
			return ResponseEntity.ok(new MessageResponse("Tweet can not be created!"));
		}
	}

	@PostMapping(path = "/{username}/update/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public void updateTweet(@PathVariable(required = true) String username, @RequestBody TweetRequest tweetRequest,
			@PathVariable(required = true) String id) {
		LOG.info(currentUserName());
		tweetRequest.setId(id);
		tweetRequest.setUsername(username);
		tweetsFacade.updateTweet(tweetRequest);
	}

	@DeleteMapping(path = "/{username}/delete/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<?> deleteTweet(@PathVariable(required = true) String username,
			@PathVariable(required = true) String id) {
		if (tweetsFacade.deleteTweet(username, id)) {
			return ResponseEntity.ok(new MessageResponse("Tweet deleted successfully!"));
		}
		return ResponseEntity.ok(new MessageResponse("Please contact Admin"));
	}

	@PutMapping(path = "/{username}/like/{id}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> likeTweet(@PathVariable(required = true) String username,
			@PathVariable(required = true) String id) {
		if (tweetsFacade.likeTweet(username, id)) {
			return ResponseEntity.ok(new MessageResponse("Liked"));
		}
		return ResponseEntity.ok(new MessageResponse("Not Liked"));
	}

	@PostMapping(path = "/{username}/reply/{id}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> replyToTweet(@PathVariable(required = true) String username,
			@PathVariable(required = true) String id, @RequestBody TweetRequest tweetRequest) {
		if (currentUserName().equals(username)) {
			tweetRequest.setUsername(username);
			tweetsFacade.replyToTweet(tweetRequest, id);
			return ResponseEntity.ok(new MessageResponse("Tweet created successfully!"));
		} else {
			return ResponseEntity.ok(new MessageResponse("Tweet can not be created!"));
		}
	}

}