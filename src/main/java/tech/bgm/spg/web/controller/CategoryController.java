package tech.bgm.spg.web.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.bgm.spg.domain.Category;
import tech.bgm.spg.domain.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping("/list")
    @ApiOperation("Return all Category")
    @ApiResponse(code=200, message = "Ok")
    public ResponseEntity<List<Category>> getAll(){
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @PostMapping("/new")
    @ApiOperation("Save a Category")
    @ApiResponse(code=200, message = "Ok")
    public ResponseEntity<Category> save(@RequestBody Category category){
        return new ResponseEntity<>(service.save(category), HttpStatus.OK);
    }
}
