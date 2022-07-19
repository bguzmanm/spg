package tech.bgm.spg.web.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.bgm.spg.domain.Product;
import tech.bgm.spg.domain.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    Log logger = LogFactory.getLog(ProductController.class);
    private ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/list")
    @ApiOperation("Return all Products")
    @ApiResponse(code = 200, message = "Ok")
    public ResponseEntity<List<Product>> getAll(){
        logger.debug("Consulting all Products");
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation("Return a Product with an ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 404, message = "Product not found")
    })
    public ResponseEntity<Product> getProduct(@ApiParam(value = "The id of the product", required = true, example = "7") @PathVariable("id") int productId){
        logger.debug("Consulting product by id");
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
        logger.debug("Consulting products by category id");
        return service.getByCategory(categoryId)
                .map(products -> new ResponseEntity<>(products, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/new")
    @ApiOperation("Create a new Product")
    @ApiResponse(code = 200, message = "Created")
    public ResponseEntity<Product> save(@ApiParam(value = "The Product") @RequestBody Product product){
        logger.debug("Created a new product");
        return new ResponseEntity<>(service.save(product), HttpStatus.CREATED);
    }

    @DeleteMapping("/del/{id}")
    @ApiOperation("Delete a Product")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Ok"),
        @ApiResponse(code = 404, message = "Product not found")
    })
    public ResponseEntity delete(@ApiParam(value = "the product id") @PathVariable("id") int productId){
        logger.debug("Deleted a product by id");
        if (service.delete(productId)){
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
