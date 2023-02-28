package com.fastcampus.pass.repository.packaze;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@SpringBootTest
@Transactional
class PackageRepositoryTest {

    @Autowired
    private PackageRepository packageRepository;

    @Test
    void test_save() {
        PackageEntity packageEntity = new PackageEntity();
        packageEntity.setPackageName("바디 챌린지 PT 12주");
        packageEntity.setPeriod(84);

        packageRepository.save(packageEntity);

        Assertions.assertNotNull(packageEntity.getPackageSeq());
    }


    @Test
    void test_findByCreatedAtAfter() {
        LocalDateTime dateTime = LocalDateTime.now().minusMinutes(1);

        PackageEntity packageEntity1 = new PackageEntity();
        packageEntity1.setPackageName("학생 전용 3개월");
        packageEntity1.setPeriod(90);
        packageRepository.save(packageEntity1);

        PackageEntity packageEntity2 = new PackageEntity();
        packageEntity2.setPackageName("학생 전용 6개월");
        packageEntity2.setPeriod(90);
        packageRepository.save(packageEntity2);

        List<PackageEntity> packageEntities = packageRepository.findByCreatedAtAfter(dateTime, PageRequest.of(0, 1, Sort.by("packageSeq").descending()));

        Assertions.assertEquals(1, packageEntities.size());
        Assertions.assertEquals(packageEntity2.getPackageSeq(), packageEntities.get(0).getPackageSeq());
    }

    @Test
    void test_updateCountAndPeriod() {
        PackageEntity packageEntity = new PackageEntity();
        packageEntity.setPackageName("학생 전용 6개월");
        packageEntity.setPeriod(90);
        packageRepository.save(packageEntity);

        int updatedCount = packageRepository.updateCountAndPeriod(packageEntity.getPackageSeq(), 30, 120);
        PackageEntity updatedPackageEntity = packageRepository.findById(packageEntity.getPackageSeq()).get();

        Assertions.assertEquals(1, updatedCount);
    }

    @Test
    void test_delete() {
        PackageEntity packageEntity = new PackageEntity();
        packageEntity.setPackageName("학생 전용 6개월 - delete");
        packageEntity.setCount(1);
        packageRepository.save(packageEntity);

        packageRepository.deleteById(packageEntity.getPackageSeq());

        Assertions.assertTrue(packageRepository.findById(packageEntity.getPackageSeq()).isEmpty());
    }
}