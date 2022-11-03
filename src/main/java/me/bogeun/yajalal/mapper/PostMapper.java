package me.bogeun.yajalal.mapper;

import me.bogeun.yajalal.entity.post.Post;
import me.bogeun.yajalal.payload.post.PostCreateDto;
import me.bogeun.yajalal.payload.post.PostDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper {

    Post createDtoToEntity(PostCreateDto createDto);

    PostDto entityToPostDto(Post post);
}

