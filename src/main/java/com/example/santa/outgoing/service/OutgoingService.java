package com.example.santa.outgoing.service;

import com.example.santa.outgoing.dao.OutgoingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OutgoingService {

    private final OutgoingMapper oerderMapper;
}
