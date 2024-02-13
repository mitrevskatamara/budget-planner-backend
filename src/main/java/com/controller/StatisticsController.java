package com.controller;

import com.model.User;
import com.model.dto.FilterDto;
import com.model.dto.StatisticsDto;
import com.service.StatisticsService;
import com.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/statistics")
public class StatisticsController {

    private final StatisticsService statisticsService;

    private final UserService userService;


    @PostMapping("/byYear")
    public ResponseEntity<List<StatisticsDto>> getStatisticsByYear(@RequestBody FilterDto filterDto) {
        User user = this.userService.findByUsername(filterDto.getUsername());
        List<StatisticsDto> list = this.statisticsService.getStatisticsByYear(user, filterDto.getYear());

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/total")
    public ResponseEntity<StatisticsDto> getTotalForSpecificYear(@RequestBody FilterDto filterDto) {
        User user = this.userService.findByUsername(filterDto.getUsername());
        StatisticsDto statisticsDto = this.statisticsService.sumTotal(user, filterDto.getYear());

        return new ResponseEntity<>(statisticsDto, HttpStatus.OK);
    }

    @PostMapping("/byCategory")
    public ResponseEntity<List<Double>> getStatisticsForExpensesByCategory(@RequestBody StatisticsDto statisticsDto) {
        User user = this.userService.findByUsername(statisticsDto.getUsername());
        List<Double> doubles = this.statisticsService.getStatisticsForExpensesByCategory(user, statisticsDto);

        return new ResponseEntity<>(doubles, HttpStatus.OK);
    }

    @PostMapping("/incomesByCategory")
    public ResponseEntity<List<Double>> getStatisticsForIncomesByCategory(@RequestBody StatisticsDto statisticsDto) {
        User user = this.userService.findByUsername(statisticsDto.getUsername());
        List<Double> doubles = this.statisticsService.getStatisticsForIncomesByCategory(user, statisticsDto);

        return new ResponseEntity<>(doubles, HttpStatus.OK);
    }
}