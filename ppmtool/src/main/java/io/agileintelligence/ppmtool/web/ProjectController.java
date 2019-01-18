package io.agileintelligence.ppmtool.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import io.agileintelligence.ppmtool.domain.Project;
import io.agileintelligence.ppmtool.services.ProjectService;

@RestController
@RequestMapping("api/project")
public class ProjectController {

  @Autowired
  private ProjectService projectService;

  // shortcut for @RequestMapping(method = RequestMethod.GET)
  @PostMapping("")
  public ResponseEntity<?> creaateNewProject(@Valid @RequestBody Project project, BindingResult result){
    if( result.hasErrors()){
      return new ResponseEntity<String>("Invalid object", HttpStatus.BAD_REQUEST);
    }
    Project project1 = projectService.saveOrUpdateProject(project);
    return new ResponseEntity<Project>(project, HttpStatus.CREATED);
  }

}