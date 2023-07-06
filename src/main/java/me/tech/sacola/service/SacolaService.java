package me.tech.sacola.service;

import me.tech.sacola.model.Item;
import me.tech.sacola.model.Sacola;
import me.tech.sacola.resource.dto.ItemDto;
import org.springframework.stereotype.Service;

public interface SacolaService {
    Sacola verSacola(Long id);
    Sacola fecharSacola(Long id, int formaPagamento);

    Item incluirItemNaSacola(ItemDto itemDto);
}
