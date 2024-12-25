package com.auth.Authentication.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.Authentication.Services.ResultService;
import com.auth.Authentication.dto.ResultDTO;
import com.auth.Authentication.entity.Result;

@RestController
@RequestMapping("/api/results")
public class ResultController {

    @Autowired
    private ResultService resultService;

    // POST: Admin publishes result for an event
    @PostMapping("/publish")
    public ResponseEntity<Result> publishResult(@RequestBody ResultDTO resultDTO) {
        Result result = resultService.publishResult(resultDTO);
        return ResponseEntity.ok(result);
    }

    // GET: Players and Coaches can view results by eventId
    @GetMapping("/{eventId}")
    public ResponseEntity<Result> getResult(@PathVariable Long eventId) {
        Optional<Result> result = resultService.getResultByEventId(eventId);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}