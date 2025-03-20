package com.gualteros.weaponsStore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gualteros.weaponsStore.models.ItemFactura;
import com.gualteros.weaponsStore.repository.ItemFacturaRepository;

@Service
public class ItemFacturaService {
    @Autowired
    public ItemFacturaRepository itemRepository;

    public void insertarItem(ItemFactura item){
        itemRepository.save(item);
    }
}
