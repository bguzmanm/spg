package tech.bgm.spg.web.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.bgm.spg.domain.Product;
import tech.bgm.spg.domain.service.ProductService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/list")
    @ApiOperation("Return all Products")
    @ApiResponse(code = 200, message = "Ok")
    public ResponseEntity<List<Product>> getAll(){
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation("Return a Product with an ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 404, message = "Product not found")
    })
    public ResponseEntity<Product> getProduct(@ApiParam(value = "The id of the product", required = true, example = "7") @PathVariable("id") int productId){
        return service.getProduct(productId)
                .map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/cat/{id}")
    @ApiOperation("Return all Products with an Category ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 404, message = "Products not found")
    })
    public ResponseEntity<List<Product>> getByCategory(@ApiParam(value = "The id of category",
            required = false) @PathVariable("id") int categoryId){
        return service.getByCategory(categoryId)
                .map(products -> new ResponseEntity<>(products, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/new")
    @ApiOperation("Create a new Product")
    @ApiResponse(code = 200, message = "Created")
    public ResponseEntity<Product> save(@ApiParam(value = "The Product") @RequestBody Product product){
        return new ResponseEntity<>(service.save(product), HttpStatus.CREATED);
    }

    @DeleteMapping("/del/{id}")
    @ApiOperation("Delete a Product")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Ok"),
        @ApiResponse(code = 404, message = "Product not found")
    })
    public ResponseEntity delete(@ApiParam(value = "the product id") @PathVariable("id") int productId){
        if (service.delete(productId)){
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
