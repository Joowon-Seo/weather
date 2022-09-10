package zerobase.weather.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.*;
import zerobase.weather.domain.Diary;
import zerobase.weather.service.DiaryService;

import java.time.LocalDate;
import java.util.List;

@RestController
public class DiaryController {

    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @ApiOperation(value = "일기 텍스트와 날씨를 이용해서 DB에 일기 저장", notes = "이것은 노트")
    @PostMapping("/create/diary")
    void createDiary(@RequestParam @DateTimeFormat(iso = ISO.DATE) @ApiParam(value = "생성할 일기의 날짜",
			example = "2022-09-09") LocalDate date,
                     @RequestBody @ApiParam(value = "일기 내용",
							 example = "일기 내용 예시 입니다.")String text) {
        diaryService.createDiary(date, text);
    }

    @ApiOperation("선택한 날짜의 모든 일기 데이터를 가져옵니다.")
    @GetMapping("/read/diary")
    List<Diary> readDiary(@RequestParam @DateTimeFormat(iso = ISO.DATE) @ApiParam(value = "조회할 날짜",
			example = "2022-09-09") LocalDate date) {
        return diaryService.readDiary(date);
    }

    @ApiOperation("선택한 기간의 모든 일기 데이터를 가져옵니다.")
    @GetMapping("/read/diaries")
    List<Diary> readDiaries(@RequestParam @DateTimeFormat(iso = ISO.DATE) @ApiParam(value = "조회할 기간의 첫번째날",
            					example = "2022-09-09") LocalDate startDate,
                            @RequestParam @DateTimeFormat(iso = ISO.DATE) @ApiParam(value = "조회할 기간의 마지막날",
								example = "2022-09-09") LocalDate endDate) {

        return diaryService.readDiaries(startDate, endDate);
    }

    @ApiOperation("선택한 날짜의 일기 데이터 입력한 텍스트로 수정합니다.")
    @PutMapping("/update/diary")
    void updateDiary(@RequestParam @DateTimeFormat(iso = ISO.DATE) @ApiParam(value = "수정할 날짜",
			example = "2022-09-09") LocalDate date,
                     @RequestBody @ApiParam(value = "수정할 일기 내용 텍스트",
							 example = "수정할 일기 내용 텍스트 입니다.") String text) {
        diaryService.update(date, text);
    }

    @ApiOperation("선택한 날짜의 모든 일기 데이터를 삭제합니다.")
    @DeleteMapping("/delete/diary")
    void deleteDiary(@RequestParam @DateTimeFormat(iso = ISO.DATE) @ApiParam(value = "삭제할 날짜",
			example = "2022-09-09") LocalDate date) {
        diaryService.deleteDiary(date);
    }

}