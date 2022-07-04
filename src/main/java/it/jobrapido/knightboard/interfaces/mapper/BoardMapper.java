package it.jobrapido.knightboard.interfaces.mapper;

import it.jobrapido.knightboard.model.Board;
import it.jobrapido.knightboard.model.dto.BoardDTO;
import org.mapstruct.Mapper;

import javax.validation.Valid;

@Mapper
public interface BoardMapper {
    Board boardDTOtoBoard(@Valid BoardDTO dto);
}
