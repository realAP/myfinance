package at.devp.myfinance.controller_fe.read;

import at.devp.myfinance.services.space.read.SpaceDto;
import at.devp.myfinance.services.space.read.SpaceReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class SpaceReadController {

    private final SpaceReadService spaceReadService;


    @GetMapping("/fe/read/spaces")
    public List<SpaceDto> getSpaces() {
        return spaceReadService.getSpaces();
    }
}
