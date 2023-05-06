package com.simplejava.customer;

import com.simplejava.util.masking.MaskedService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * Description :
 * User: Tanveer Haider
 * Date: 5/3/2023
 * Time: 7:37 PM
 */

@Slf4j
@RestController
@RequestMapping("/api/v1/customers")
@AllArgsConstructor
public class CustomerController {

    private final CustomerRepository repository;

    private final ModelMapper modelMapper;


    @GetMapping("/{id}")
    @MaskedService
    public ResponseEntity<CustomerDto> findById(@PathVariable Long id) {
        log.info("Inside id :" + id);
        CustomerEntity customerById = repository.findById(id);
        CustomerDto customerDto = modelMapper.map(customerById,CustomerDto.class);
        return ResponseEntity.ok().body(customerDto);
    }

    @GetMapping
    @MaskedService
    public ResponseEntity<List<CustomerDto>> findBAll() {
        List<CustomerEntity> customerEntities= repository.findAll();
        List<CustomerDto> customerDtoList = Arrays.asList(modelMapper.map(customerEntities, CustomerDto[].class));
        return ResponseEntity.ok().body(customerDtoList);
    }

}
