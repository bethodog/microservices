package crosinfo.com.passin.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import crosinfo.com.passin.dto.attendee.AttendeeBadgeResponseDTO;
import crosinfo.com.passin.service.AttendeeService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/attendees")
public class AttendeeController {
	
	private final AttendeeService attendeeService;
	
	@GetMapping("/{attendeeId}/badge")
	public ResponseEntity<AttendeeBadgeResponseDTO> getAttendeeBadge(@PathVariable String attendeeId, UriComponentsBuilder builder) {
		
		AttendeeBadgeResponseDTO response = attendeeService.getAttendeeBadge(attendeeId, builder);
		
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/{attendeeId}/check-in")
	public ResponseEntity registerCheckIn(@PathVariable String attendeeId, UriComponentsBuilder uriComponentsBuilder) {
		
		attendeeService.checkInAttendee(attendeeId);
		
		var uri = uriComponentsBuilder.path("/attendees/{attendeeId}/badge").buildAndExpand(attendeeId).toUri();
		
		return ResponseEntity.created(uri).build();
	}

}