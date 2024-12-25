package com.auth.Authentication.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth.Authentication.Repository.ResultRepository;
import com.auth.Authentication.dto.ResultDTO;
import com.auth.Authentication.entity.Result;

import java.util.Optional;

@Service
public class ResultService {

    @Autowired
    private ResultRepository resultRepository;

    public Result publishResult(ResultDTO resultDTO) {
        Result result = new Result();
        result.setEventId(resultDTO.getEventId());
        result.setDescription(resultDTO.getDescription());
        return resultRepository.save(result);
    }

    public Optional<Result> getResultByEventId(Long eventId) {
        return resultRepository.findByEventId(eventId);
    }
}
