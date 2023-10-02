package org.organizer.demo.controller;

import org.organizer.demo.model.response.Response;
import org.organizer.demo.model.response.TrackResponse;
import org.organizer.demo.service.TrackService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/track")
@Api(value = "Track Service")
@RequiredArgsConstructor
public class TrackController extends BaseController {
	
	private final TrackService trackService;
	
	@GetMapping
	@ApiOperation(value = "This endpoint try to organize your conference with pre-given presentations")
	public Response<TrackResponse> organizeConference(@RequestParam Long conferenceId) throws Exception {
		TrackResponse response = trackService.generateTracks(conferenceId);
		return success(response);
	}
	
	@GetMapping("/existing")
	@ApiOperation(value = "This endpoint retrieve existing tracks for your conference with given conference Id")
	public Response<TrackResponse> retrieveTracks(@RequestParam Long conferenceId) throws Exception {
		TrackResponse response = trackService.retrieveTracks(conferenceId);
		return success(response);
	}

}
