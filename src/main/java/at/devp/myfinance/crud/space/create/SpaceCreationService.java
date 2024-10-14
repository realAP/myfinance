package at.devp.myfinance.crud.space.create;

import at.devp.myfinance.entity.Space;
import at.devp.myfinance.repositories.SpaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpaceCreationService {

    private final SpaceRepository spaceRepository;

    public void createSpace(final SpaceCreationDto spaceCreationDto) {
        final var space = new Space();
        space.setName(spaceCreationDto.getName());

        spaceRepository.save(space);
    }

}
