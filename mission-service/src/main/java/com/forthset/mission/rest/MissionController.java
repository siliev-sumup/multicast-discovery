package com.forthset.mission.rest;

import com.forthset.mission.rest.model.Dto;
import com.forthset.mission.rest.model.MissionDto;
import com.forthset.mission.service.MissionService;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.sql.Date;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@RestController
@Api("Mission")
@RequestMapping("/missions")
public class MissionController {

  private MissionService missionService;

  public MissionController(MissionService missionService) {
    this.missionService = missionService;
  }

  @PostMapping
  public ResponseEntity<MissionDto> create(@Valid @RequestBody MissionDto newMission) {
    return ResponseEntity.ok(newMission);
  }

  @GetMapping
  public ResponseEntity<Dto<List<MissionDto>, ?>> page(@PathParam("pageNum") Integer pageNum, @PathParam("pageSize") Integer pageSize) {
    // TODO remove mock

    final MissionDto mockMission1 = new MissionDto();
    mockMission1.setId(1L);
    mockMission1.setName("Clean my house");
    mockMission1.setDescription("I need someone to clean my house.");
    mockMission1.setDate(Date.from(ZonedDateTime.of(
            2020,
            10,
            10,
            12,
            0,
            0 ,
            0,
            ZoneId.of(ZoneId.SHORT_IDS.get("ECT"))).toInstant()));
    mockMission1.setImageUrl("https://thumbs.dreamstime.com/z/young-woman-skimpy-bikini-posing-beach-bikini-girl-beach-124133138.jpg");

    final MissionDto mockMission2 = new MissionDto();
    mockMission2.setId(2L);
    mockMission2.setName("Feed my cats for two days");
    mockMission2.setDescription("I need someone to feed my cats at my house every day for a week.\n" +
            "Food provided in the apartment.");
    mockMission1.setDate(Date.from(ZonedDateTime.of(
            2020,
            8,
            10,
            12,
            0,
            0 ,
            0,
            ZoneId.of(ZoneId.SHORT_IDS.get("ECT"))).toInstant()));
    mockMission2.setImageUrl("https://images.ams.bg/images/galleries/188516/tova-e-honda-civic-type-r-limited-edition-1582207309_big.jpg");

    return ResponseEntity.ok(new Dto<List<MissionDto>, Void>(List.of(
            mockMission1, mockMission2
    )));
  }
}
