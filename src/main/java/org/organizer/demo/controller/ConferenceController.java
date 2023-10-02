package org.organizer.demo.controller;

import org.organizer.demo.exception.RequestValidationException;
import org.organizer.demo.model.request.ConferenceRequest;
import org.organizer.demo.model.response.ConferenceResponse;
import org.organizer.demo.model.response.Response;
import org.organizer.demo.service.ConferenceService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/conference")
@Api(value = "Conference Service")
@RequiredArgsConstructor
public class ConferenceController extends BaseController{
	
	private final ConferenceService conferenceService;
	
	@PostMapping
	@ApiOperation(value = "This endpoint creates new Conference object")
	public Response<ConferenceResponse> createConference(@RequestBody ConferenceRequest request) throws RequestValidationException {
		request.validate();
		ConferenceResponse response = conferenceService.createConference(request);
		return success(response);
	}

}
