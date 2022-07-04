package it.jobrapido.knightboard.interfaces.mapper;

import it.jobrapido.knightboard.model.Coordinate;
import it.jobrapido.knightboard.model.dto.ObstacleDTO;
import org.mapstruct.Mapper;
import org.springframework.validation.annotation.Validated;

@Mapper
@Validated
public interface ObstaclesMapper {
    Coordinate obstacleDTOToCoordinate(ObstacleDTO dto);
}
