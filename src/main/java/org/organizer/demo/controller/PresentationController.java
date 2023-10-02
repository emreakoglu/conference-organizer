package org.organizer.demo.controller;

import org.organizer.demo.exception.BusinessException;
import org.organizer.demo.exception.RequestValidationException;
import org.organizer.demo.model.request.PresentationRequest;
import org.organizer.demo.model.response.PresentationResponse;
import org.organizer.demo.model.response.Response;
import org.organizer.demo.service.PresentationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/presentation")
@Api(value = "Presentation Service")
@RequiredArgsConstructor
public class PresentationController extends BaseController {
	
	private final PresentationService presentationService;
	
	@PostMapping
	@ApiOperation(value = "This endpoint creates new Presentation")
	public Response<PresentationResponse> createPresentation(@RequestBody PresentationRequest request) throws BusinessException, RequestValidationException {
		request.validate();
		
		PresentationResponse response = presentationService.createPresentation(request);
		return success(response);
	}

}
