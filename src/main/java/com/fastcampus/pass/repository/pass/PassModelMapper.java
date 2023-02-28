package com.fastcampus.pass.repository.pass;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

// ReportingPolicy.IGNORE: 일치하지 않은 필드를 무시합니다.
@Mapper(componentModel = "spring")
public interface PassModelMapper {
    PassModelMapper INSTANCE = Mappers.getMapper(PassModelMapper.class);

    @Mappings({
            @Mapping(target = "status", qualifiedByName = "defaultStatus"),
            @Mapping(target = "remainingCount", source = "bulkPassEntity.count")
    })
    PassEntity toPassEntity(BulkPassEntity bulkPassEntity, String userId);

    // BulkPassStatus와 관계 없이 PassStatus값을 설정합니다.
    @Named("defaultStatus")
    default PassStatus status(BulkPassStatus status) {
        return PassStatus.READY;
    }
}