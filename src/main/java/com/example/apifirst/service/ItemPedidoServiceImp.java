package com.example.apifirst.service;

import com.example.apifirst.dto.ItemPedidoDto;
import com.example.apifirst.dto.ItemPedidoToSaveDto;
import com.example.apifirst.dto.PagoDto;
import com.example.apifirst.entities.ItemPedido;
import com.example.apifirst.entities.Pago;
import com.example.apifirst.entities.Pedido;
import com.example.apifirst.entities.Product;
import com.example.apifirst.mapper.ItemPedidoMapper;
import com.example.apifirst.repository.ItemPedidoRepository;
import com.example.apifirst.repository.PedidoRepository;
import com.example.apifirst.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ItemPedidoServiceImp implements ItemPedidoService{

    private final ItemPedidoRepository itemPedidoRepository;
    private final PedidoRepository pedidoRepository;
    private final ProductRepository productRepository;
    private final ItemPedidoMapper itemPedidoMapper;

    public ItemPedidoServiceImp(ItemPedidoRepository itemPedidoRepository, ItemPedidoMapper itemPedidoMapper, PedidoRepository pedidoRepository, ProductRepository productRepository){
        this.itemPedidoRepository=itemPedidoRepository;
        this.itemPedidoMapper=itemPedidoMapper;
        this.pedidoRepository=pedidoRepository;
        this.productRepository=productRepository;
    }

    @Override
    public ItemPedidoDto guardarItemPedido(ItemPedidoToSaveDto itemPedido) {
        ItemPedido itemPedidoSaved=itemPedidoMapper.itemPedidoToSaveDtoToEntity(itemPedido);
        Optional<Pedido> pedidoFound=pedidoRepository.findById(itemPedido.pedido().id());
        Optional<Product> productFound=productRepository.findById(itemPedido.producto().id());
        itemPedidoSaved.setPedido(pedidoFound.get());
        itemPedidoSaved.setProducto(productFound.get());
        itemPedidoSaved=itemPedidoRepository.save(itemPedidoSaved);
        return itemPedidoMapper.entityToItemPedidoDto(itemPedidoSaved);
    }

    @Override
    public ItemPedidoDto actualizarItemPedido(UUID id, ItemPedidoToSaveDto itemPedido) {
        Optional<ItemPedido> itemPedidoFound = itemPedidoRepository.findById(id);

        if (itemPedido.cantidad()!= null) itemPedidoFound.get().setCantidad(itemPedido.cantidad());
        if (itemPedido.precio() != null) itemPedidoFound.get().setPrecio(itemPedido.precio());

        ItemPedido itemPedidoUpdated = itemPedidoRepository.save(itemPedidoFound.get());

        return itemPedidoMapper.entityToItemPedidoDto(itemPedidoUpdated);
    }

    @Override
    public ItemPedidoDto buscarItemPedidoById(UUID id) {
        Optional<ItemPedido> itemPedidoFound=itemPedidoRepository.findById(id);
        return itemPedidoMapper.entityToItemPedidoDto(itemPedidoFound.get());
    }

    @Override
    public void removerItemPedido(UUID id) {
        itemPedidoRepository.deleteById(id);
    }

    @Override
    public List<ItemPedidoDto> getAllItems() {
        List<ItemPedido> itemPedidoFound=itemPedidoRepository.findAll();
        List<ItemPedidoDto> foundToDto=new ArrayList<>();
        itemPedidoFound.forEach(itemPedido -> {
            ItemPedidoDto c=itemPedidoMapper.entityToItemPedidoDto(itemPedido);
            foundToDto.add(c);
        });
        return foundToDto;
    }

    @Override
    public List<ItemPedidoDto> findByPedido_Id(UUID idPedido) {
        List<ItemPedido> itemPedidoFound=itemPedidoRepository.findByPedido_Id(idPedido);
        List<ItemPedidoDto> foundToDto=new ArrayList<>();
        itemPedidoFound.forEach(itemPedido -> {
            ItemPedidoDto c=itemPedidoMapper.entityToItemPedidoDto(itemPedido);
            foundToDto.add(c);
        });
        return foundToDto;
    }

    @Override
    public List<ItemPedidoDto> findByProducto_Id(UUID idProducto) {
        List<ItemPedido> itemPedidoFound=itemPedidoRepository.findByProducto_Id(idProducto);
        List<ItemPedidoDto> foundToDto=new ArrayList<>();
        itemPedidoFound.forEach(itemPedido -> {
            ItemPedidoDto c=itemPedidoMapper.entityToItemPedidoDto(itemPedido);
            foundToDto.add(c);
        });
        return foundToDto;
    }

    @Override
    public Integer findBySumaProd(UUID productoID) {
        return itemPedidoRepository.findBySumaProd(productoID);
    }
}
