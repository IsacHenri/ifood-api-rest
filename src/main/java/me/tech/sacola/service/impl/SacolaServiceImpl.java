package me.tech.sacola.service.impl;

import lombok.RequiredArgsConstructor;
import me.tech.sacola.model.Item;
import me.tech.sacola.model.Sacola;
import me.tech.sacola.repository.SacolaRepository;
import me.tech.sacola.resource.dto.ItemDto;
import me.tech.sacola.service.SacolaService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SacolaServiceImpl implements SacolaService {
    private final SacolaRepository SacolaRepository;
    @Override
    public Sacola verSacola(Long id) {
        return SacolaRepository.findById(id).orElseThrow(
                () -> {
                    throw new RuntimeException("Essa sacola não existe");
                }
        );
    }

    @Override
    public Sacola fecharSacola(Long id, int formaPagamento) {
        return null;
    }

    @Override
    public Item incluirItemNaSacola(ItemDto itemDto) {
        return null;
    }
}
