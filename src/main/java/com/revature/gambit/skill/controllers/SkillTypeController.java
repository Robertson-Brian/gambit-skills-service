package com.revature.gambit.skill.controllers;

import java.io.UnsupportedEncodingException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.gambit.skill.beans.SkillType;
import com.revature.gambit.skill.services.SkillTypeServiceImpl;
import org.springframework.web.bind.annotation.*;

import com.revature.gambit.skill.services.SkillTypeService;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;

/**
 * Controller that will handle requests for the skill type service.
 */
@RestController
public class SkillTypeController {

}
