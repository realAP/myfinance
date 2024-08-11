package at.devp.myfinance.controller_fe.write;

import at.devp.myfinance.services.space.create.SpaceCreationDto;
import at.devp.myfinance.services.space.create.SpaceCreationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class SpaceWriteController {

    private final SpaceCreationService spaceCreationService;


    @PostMapping("/fe/write/spaces")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> createSpace(@RequestBody final SpaceCreationDto spaceCreationDto) {
        spaceCreationService.createSpace(spaceCreationDto);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

}
