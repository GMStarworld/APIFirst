package com.example.apifirst.mapper;

import com.example.apifirst.dto.ProductDto;
import com.example.apifirst.dto.ProductToSaveDto;
import com.example.apifirst.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product ProductDtoToEntity(ProductDto productDto);
    @Mapping(target = "item_pedido", expression="java(new ArrayList<>())")
    ProductDto entityToDto(Product product);

    @Mapping(target ="id", ignore = true)
    @Mapping(target="item_pedido", expression="java(new ArrayList<>())")
    Product productToSaveDtoToEntity(ProductToSaveDto productToSaveDto);

    ProductToSaveDto entityToProductToSaveDto(Product product);
}
