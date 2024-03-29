package com.tms.api.controller;

import com.tms.api.data.dto.ScenarioDto;
import com.tms.api.model.scenario.CreateScenarioRequest;
import com.tms.api.model.scenario.ScenarioResponse;
import com.tms.api.model.scenario.UpdateScenarioRequest;
import com.tms.api.service.FeatureService;
import com.tms.api.service.ScenarioService;
import com.tms.api.util.Path;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(Path.SCENARIOS)
public class ScenarioController {

    @Autowired
    private ScenarioService service;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private FeatureService featureService;

    @PostMapping
    public ResponseEntity<?> createScenario(@Valid @RequestBody CreateScenarioRequest created,
            @RequestHeader("user-id") String userId) {
        ScenarioDto scenarioDto = service.createScenarioByUser(mapper.map(created, ScenarioDto.class), userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.map(scenarioDto, ScenarioResponse.class));
    }

    @PutMapping
    public ResponseEntity<ScenarioResponse> updateScenario(@Valid @RequestBody UpdateScenarioRequest updated) {
        ScenarioDto dto = service.update(mapper.map(updated, ScenarioDto.class));
        return ResponseEntity.status(HttpStatus.OK).body(mapper.map(dto, ScenarioResponse.class));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScenarioResponse> getScenario(@PathVariable("id") String id) {
        return ResponseEntity.ok(mapper.map(service.getById(id), ScenarioResponse.class));
    }

    @GetMapping
    ResponseEntity<Page<ScenarioResponse>> getScenarios(
            @PageableDefault(sort = {"createdAt"}, direction = Sort.Direction.DESC) Pageable page) {
        Page<ScenarioDto> testDtos = service.findPage(page);
        return ResponseEntity.ok(testDtos.map(dto -> mapper.map(dto, ScenarioResponse.class)));
    }

    @GetMapping("/user/{userId}")
    ResponseEntity<List<ScenarioResponse>> getUserScenarios(@PathVariable("userId") String userId) {
        List<ScenarioDto> dtos = service.getUserScenarios(userId);
        return ResponseEntity.ok(dtos.stream().map(dto -> mapper.map(dto, ScenarioResponse.class)).collect(Collectors.toList()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeature(@PathVariable("id") String id) {
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
