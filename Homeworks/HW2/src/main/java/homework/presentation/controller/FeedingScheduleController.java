package homework.presentation.controller;

import homework.application.AnimalTreatService;
import homework.application.FeedingOrganizationService;
import homework.application.ZooStatisticsService;
import homework.presentation.request.FeedingScheduleRequest;
import homework.domain.entities.FeedingSchedule;
import homework.domain.value_objects.Time;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@RequestMapping("/api/feeding_schedule")
@RequiredArgsConstructor
@Tag(name = "Расписание кормления", description = "Управление расписанием кормления в зоопарке")
public class FeedingScheduleController {
    @Autowired
    private FeedingOrganizationService feedingOrganizationService;
    @Autowired
    private ZooStatisticsService zooStatisticsService;

    @Autowired
    private AnimalTreatService animalTreatService;

    @GetMapping("/all")
    @Operation(summary = "Получить информации о всех существующих расписаниях кормления")
    public List<FeedingSchedule> getAllFeedingSchedule() {
        return zooStatisticsService.getAllFeedingSchedule();
    }

    @GetMapping("/animal/{id}")
    @Operation(summary = "Получить информации о всех существующих расписаниях кормления у животного с id")
    public List<FeedingSchedule> getAllFeedingSchedule(@PathVariable int id) {
        return zooStatisticsService.getAnimalFeedingSchedule(id);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить информации о расписании кормления по id")
    public ResponseEntity<FeedingSchedule> getFeedingSchedule(@PathVariable int id) {
        return zooStatisticsService.getFeedingSchedule(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    @Operation(summary = "Добавить новое время для кормеления животного")
    public ResponseEntity<FeedingSchedule> createFeedingSchedule(
            @Valid @RequestBody FeedingScheduleRequest request,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    bindingResult.getAllErrors().get(0).getDefaultMessage());
        }


        String[] parts = request.timeToFeed().split("[/]");
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH/mm/ss");
            LocalTime.parse(request.timeToFeed(), formatter);
        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().build();
        }
        int hour   = Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);
        int second  = Integer.parseInt(parts[2]);

        var feedingSchedule = feedingOrganizationService.makeNewTime(request.id(),
                new Time(hour, minute, second));

        return ResponseEntity.status(HttpStatus.CREATED).body(feedingSchedule);
    }

    @PatchMapping("/doSkip/{id}")
    @Operation(summary = "Пропустить кормление с id")
    public ResponseEntity<Boolean> doSkipById(@PathVariable int id) {


        return ResponseEntity.ok(feedingOrganizationService.doSkip(id));
    }
    @GetMapping("/undoSkip/{id}")
    @Operation(summary = "Вернуть в расписание кормление с id")
    public ResponseEntity<Boolean> undoSkipById(@PathVariable int id) {


        return ResponseEntity.ok(feedingOrganizationService.undoSkip(id));
    }

    @PatchMapping("/doSkip")
    @Operation(summary = "Пропустить все расписание")
    public ResponseEntity<Boolean> doAllSkip() {


        return ResponseEntity.ok(feedingOrganizationService.doAllSkip());
    }

    @PatchMapping("/undoSkip")
    @Operation(summary = "Вернуть все пропущенное из расписания")
    public ResponseEntity<Boolean> undoAllSkip() {


        return ResponseEntity.ok(feedingOrganizationService.undoAllSkip());
    }

    @PatchMapping("/doSkip/animal/{id}")
    @Operation(summary = "Пропустить все расписание у животного с id")
    public ResponseEntity<Boolean> doAnimalAllSkip(@PathVariable int id) {


        return ResponseEntity.ok(feedingOrganizationService.doAllSkipForAnimal(id));
    }

    @PatchMapping("/undoSkip/animal/{id}")
    @Operation(summary = "Вернуть все пропущенное из расписания у животного с id")
    public ResponseEntity<Boolean> undoAnimalAllSkip(@PathVariable int id) {
        return ResponseEntity.ok(feedingOrganizationService.undoAllSkipForAnimal(id));
    }

    @GetMapping("/feed")
    @Operation(summary = "Покормить всех животных согласно плану")
    public ResponseEntity<Boolean> feedAllAnima() {

        return ResponseEntity.ok(feedingOrganizationService.feedAll());
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Удалить расписание кормления по id")
    public ResponseEntity<Boolean> deleteFeedingScheduleById(@PathVariable int id) {

        return ResponseEntity.ok(feedingOrganizationService.deleteById(id));
    }


}