package com.shayan.datacentermanagment.service.impl;

import com.shayan.datacentermanagment.model.*;
import com.shayan.datacentermanagment.model.enumration.EquipmentType;
import com.shayan.datacentermanagment.model.enumration.LocationType;
import com.shayan.datacentermanagment.model.enumration.Role;
import com.shayan.datacentermanagment.reposiory.*;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;


@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializerService {

    private final LocationRepository locationRepository;
    private final DataCenterRepository dataCenterRepository;
    private final RackRowRepository rackRowRepository;
    private final RackRepository rackRepository;
    private final EquipmentRepository equipmentRepository;
    private final UserRepository userRepository;

    @PostConstruct
    public void initialize() {
        if (locationRepository.findIranLocation() != null) {
            log.info("✅ Initial data already exists. Skipping initialization.");
            return;
        }

        log.info("🚀 Initializing infrastructure data...");

        Location iran = createLocations();
        createDataCenters(iran);
        if (userRepository.findAll().isEmpty()){
            createUsers();
            log.info("Admin and supports added");
        }

        log.info("✅ Infrastructure initialized successfully.");


    }

    private Location createLocations() {
        Location iran = Location.builder().name("ایران").locationType(LocationType.COUNTRY).build();

        Location tehran = Location.builder().name("تهران").locationType(LocationType.CITY).parent(iran).build();
        Location esfahan = Location.builder().name("اصفهان").locationType(LocationType.CITY).parent(iran).build();
        Location fars = Location.builder().name("فارس").locationType(LocationType.CITY).parent(iran).build();
        Location khorasan = Location.builder().name("خراسان رضوی").locationType(LocationType.CITY).parent(iran).build();
        Location azarbayjanSharghi = Location.builder().name("آذربایجان شرقی").locationType(LocationType.CITY).parent(iran).build();

        iran.setChildren(List.of(tehran, esfahan, fars, khorasan, azarbayjanSharghi));

        locationRepository.saveAll(List.of(iran, tehran, esfahan, fars, khorasan, azarbayjanSharghi));
        return iran;
    }

    private void createDataCenters(Location iran) {
        for (Location city : iran.getChildren()) {

            boolean alreadyExists = dataCenterRepository.existsByLocation(city);
            if (alreadyExists) {
                log.info("✅ Data center for '{}' already exists. Skipping...", city.getName());
                continue;
            }

            DataCenter dataCenter = DataCenter.builder()
                    .name("مرکز داده " + city.getName())
                    .location(city)
                    .build();
            dataCenterRepository.save(dataCenter);

            for (int row = 1; row <= 5; row++) {
                RackRow rackRow = RackRow.builder()
                        .name("ردیف-" + row)
                        .dataCenter(dataCenter)
                        .build();
                rackRowRepository.save(rackRow);

                for (int rackIndex = 1; rackIndex <= 10; rackIndex++) {
                    Rack rack = Rack.builder()
                            .code("Rack-" + row + "-" + rackIndex)
                            .unitCapacity(42)
                            .rackRow(rackRow)
                            .build();
                    rackRepository.save(rack);

                    createEquipmentForRack(rack);
                }
            }
        }
    }


    private void createEquipmentForRack(Rack rack) {
        List<Equipment> equipmentList = new ArrayList<>();
        int currentUnit = 40;

        Equipment switchEq = createEquipment("Switch", EquipmentType.SWITCH, 42, 1, 24, rack);
        equipmentList.add(switchEq);

        // پچ پنل
        Equipment patchPanel = createEquipment("Patch Panel", EquipmentType.PATCH_PANEL, 41, 1, 24, rack);
        equipmentList.add(patchPanel);

        for (int i = 0; i < 5; i++) {
            Equipment server = createEquipment("Server-1U-" + (i + 1), EquipmentType.SERVER, currentUnit--, 1, 4, rack);
            equipmentList.add(server);
        }

        for (int i = 0; i < 5; i++) {
            currentUnit -= 1;
            Equipment server = createEquipment("Server-2U-" + (i + 1), EquipmentType.SERVER, currentUnit, 2, 4, rack);
            currentUnit -= 1;
            equipmentList.add(server);
        }

        for (int i = 0; i < 2; i++) {
            currentUnit -= 3;
            Equipment server = createEquipment("Server-4U-" + (i + 1), EquipmentType.SERVER, currentUnit, 4, 4, rack);
            currentUnit -= 1;
            equipmentList.add(server);
        }

        equipmentRepository.saveAll(equipmentList);

    }

    private Equipment createEquipment(String name, EquipmentType type, int startUnit, int unitSize, int portCount, Rack rack) {
        Equipment equipment = Equipment.builder()
                .name(name)
                .equipmentType(type)
                .startUnit(startUnit)
                .unitSize(unitSize)
                .rack(rack)
                .build();

        List<Port> ports = IntStream.rangeClosed(1, portCount)
                .mapToObj(i -> Port.builder()
                        .portNumber(i)
                        .equipment(equipment)
                        .build())
                .toList();

        equipment.setPorts(ports);

        // 💰 تعیین قیمت
        equipment.setPrice(calculatePrice(type, unitSize, portCount));

        return equipment;
    }

    private BigDecimal calculatePrice(EquipmentType type, int unitSize, int portCount) {
        BigDecimal base;
        BigDecimal unitPrice = BigDecimal.valueOf(150_000);
        BigDecimal portPrice = BigDecimal.valueOf(50_000);

        switch (type) {
            case SERVER -> base = BigDecimal.valueOf(500_000);
            case SWITCH -> base = BigDecimal.valueOf(400_000);
            case PATCH_PANEL -> base = BigDecimal.valueOf(300_000);
            default -> base = BigDecimal.ZERO;
        }

        return base
                .add(unitPrice.multiply(BigDecimal.valueOf(unitSize)))
                .add(portPrice.multiply(BigDecimal.valueOf(portCount)));

    }

    private void createUsers(){
        User admin = User.builder().username("admin").password("admin").role(Role.ROLE_ADMIN).rentedServices(null).build();
        User supporterOne = User.builder().username("sup1").password("sup1").role(Role.ROLE_SUPPORT).rentedServices(null).build();
        User supporterTwo = User.builder().username("sup2").password("sup2").role(Role.ROLE_SUPPORT).rentedServices(null).build();
        User supporterThree = User.builder().username("sup3").password("sup3").role(Role.ROLE_SUPPORT).rentedServices(null).build();
        User supporterFour = User.builder().username("sup4").password("sup4").role(Role.ROLE_SUPPORT).rentedServices(null).build();
        User supporterFive = User.builder().username("sup5").password("sup5").role(Role.ROLE_SUPPORT).rentedServices(null).build();

         userRepository.saveAll(List.of(admin,supporterOne,supporterTwo,supporterThree,supporterFour,supporterFive));

    }

}
