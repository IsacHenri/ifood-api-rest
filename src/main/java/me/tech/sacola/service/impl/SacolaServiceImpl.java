package me.tech.sacola.service.impl;

import lombok.RequiredArgsConstructor;
import me.tech.sacola.enumaration.FormaPagamento;
import me.tech.sacola.model.Item;
import me.tech.sacola.model.Restaurante;
import me.tech.sacola.model.Sacola;
import me.tech.sacola.repository.ItemRepository;
import me.tech.sacola.repository.SacolaRepository;
import me.tech.sacola.repository.ProdutoRepository;
import me.tech.sacola.resource.dto.ItemDto;
import me.tech.sacola.service.SacolaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SacolaServiceImpl implements SacolaService {
    private final SacolaRepository SacolaRepository;
    private final ProdutoRepository ProdutoRepository;
    private final ItemRepository ItemRepository;
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

        Sacola sacola = verSacola(id);
        if (sacola.getItens().isEmpty()) {
            throw new RuntimeException("Essa sacola não possui itens");
        }
        if (formaPagamento == 0){
            sacola.setFormaPagamento(FormaPagamento.DINHEIRO);
        } else{
            sacola.setFormaPagamento(FormaPagamento.MAQUININHA);
        }
        //FormaPagamento formaPagamento = numeroFormaPagamento = 0 ? FormaPagamento.DINHEIRO : FormaPagamento.MAQUININHA;
        //sacola.setFormaPagamento(formaPagamento);
        sacola.setFechada(true);
        return SacolaRepository.save(sacola);
    }

    @Override
    public Item incluirItemNaSacola(ItemDto itemDto) {
        Sacola sacola = verSacola(itemDto.getIdSacola());
        if (sacola.isFechada()){
            throw new RuntimeException("Essa sacola já foi fechada");
        }
        Item itemParaSerInserido = Item.builder()
                                        .sacola(sacola)
                                        .quantidade(itemDto.getQuantidade())
                                        .produto(ProdutoRepository.findById(itemDto.getProdutoId())
                                                .orElseThrow(() -> new RuntimeException("Produto não existe")))
                                        .build();
        List<Item> itensDaSacola = sacola.getItens();
        if (itensDaSacola.isEmpty()){
            itensDaSacola.add(itemParaSerInserido);
        } else{
            Restaurante restauranteAtual = itensDaSacola.get(0).getProduto().getRestaurante();
            Restaurante restauranteAdicionar = itemParaSerInserido.getProduto().getRestaurante();
            if (restauranteAtual.equals(restauranteAdicionar)){
                itensDaSacola.add(itemParaSerInserido);
            } else{
                throw new RuntimeException("Não é possivel incluir itens de diferentes restaurantes");
            }
        }

        SacolaRepository.save(sacola);
        return ItemRepository.save(itemParaSerInserido);
    }
}
