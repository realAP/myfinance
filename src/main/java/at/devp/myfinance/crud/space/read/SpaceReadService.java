package at.devp.myfinance.crud.space.read;

import at.devp.myfinance.repositories.SpaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpaceReadService {

    private final SpaceRepository spaceRepository;

    public List<SpaceDto> getSpaces() {
        final var spaces = spaceRepository.findAll();
        return spaces.stream().map(space -> {
            final var spaceDto = new SpaceDto();
            spaceDto.setId(space.getId());
            spaceDto.setName(space.getName());
            return spaceDto;
        }).toList();
    }

}
