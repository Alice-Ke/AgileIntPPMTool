package io.agileintelligence.ppmtool.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import io.agileintelligence.ppmtool.domain.Project;
import io.agileintelligence.ppmtool.services.MapValidationService;
import io.agileintelligence.ppmtool.services.ProjectService;

@RestController
@RequestMapping("api/project")
public class ProjectController {

  @Autowired
  private ProjectService projectService;

  @Autowired
  private MapValidationService mapValidationService;

  // shortcut for @RequestMapping(method = RequestMethod.GET)
  @PostMapping("")
  public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult
      result) {
    ResponseEntity<?> errorMap = mapValidationService.MapValidation(result);
    if (errorMap != null) return errorMap;
    Project project1 = projectService.saveOrUpdateProject(project);
    return new ResponseEntity<Project>(project, HttpStatus.CREATED);


  }

}
