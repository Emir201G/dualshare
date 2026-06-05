package com.app.dualshare.mapper;

import com.app.dualshare.dto.StoryRequestDTO;
import com.app.dualshare.dto.StoryResponseDTO;
import com.app.dualshare.model.Story;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StoryMapper {

    Story toEntity(StoryRequestDTO storyRequestDTO);

    StoryResponseDTO toDTO(Story story);

    List<StoryResponseDTO> toDTOList(List<Story> stories);
}
