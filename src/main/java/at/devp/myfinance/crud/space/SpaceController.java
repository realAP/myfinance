package at.devp.myfinance.crud.space;

import at.devp.myfinance.crud.space.create.SpaceCreationDto;
import at.devp.myfinance.crud.space.create.SpaceCreationService;
import at.devp.myfinance.crud.space.read.SpaceDto;
import at.devp.myfinance.crud.space.read.SpaceReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/fe/crud/spaces")
public class SpaceController {

    private final SpaceCreationService spaceCreationService;
    private final SpaceReadService spaceReadService;

    @PostMapping()
    public ResponseEntity<?> createSpace(@RequestBody final SpaceCreationDto spaceCreationDto) {
        spaceCreationService.createSpace(spaceCreationDto);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @GetMapping()
    public List<SpaceDto> getSpaces() {
        return spaceReadService.getSpaces();
    }

}
