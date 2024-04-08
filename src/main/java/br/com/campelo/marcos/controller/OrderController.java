package br.com.campelo.marcos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;

import br.com.campelo.marcos.dto.request.CreateOrderDTO;
import br.com.campelo.marcos.dto.request.filter.FilterOrderListQueryParamDTO;
import br.com.campelo.marcos.service.OrderService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Validated
@RestController
@RequestMapping("/v1/pedido")
@AllArgsConstructor(onConstructor_ = { @Autowired })
public class OrderController {

    private final OrderService service;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, 
    produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> createOrder(@Valid @RequestBody CreateOrderDTO createOrderDTO) {
        service.createOrder(createOrderDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, 
    produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> listOrder(FilterOrderListQueryParamDTO filterOrder){
        return ResponseEntity.ok(service.listOrder(filterOrder));
    }

}