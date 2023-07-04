package me.tech.sacola.resource;

import lombok.RequiredArgsConstructor;
import me.tech.sacola.model.Item;
import me.tech.sacola.model.Sacola;
import me.tech.sacola.resource.dto.ItemDto;
import me.tech.sacola.service.SacolaService;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/ifood-devweek/sacolas")
public class SacolaResource {
    private final SacolaService sacolaService;
    @PostMapping
    public Item incluirItemNaSacola(@RequestBody ItemDto itemDto) { return sacolaService.incluirItemNaSacola(itemDto); }

    @GetMapping("/{id}")
    public Sacola verSacola(@PathVariable("id") Long id) { return sacolaService.verSacola(id); }

    @PatchMapping("/fecharSacola/{sacolaId}")
    public Sacola fecharSAcola(@PathVariable("sacolaId") Long sacolaId,
                               @RequestParam("formaPagamento") int formaPagamento)
    { return sacolaService.fecharSacola(sacolaId, formaPagamento);}

}
