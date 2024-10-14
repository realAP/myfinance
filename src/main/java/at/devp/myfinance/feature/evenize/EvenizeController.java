package at.devp.myfinance.feature.evenize;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/fe/features/evenize")
@RequiredArgsConstructor
public class EvenizeController {

    private final EvenizerService evenizerService;

    @GetMapping("/{id}")
    public BigDecimal evenize(@PathVariable Long id) {
        return evenizerService.evenize(id);
    }
}
