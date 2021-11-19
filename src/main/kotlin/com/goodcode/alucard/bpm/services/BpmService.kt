package com.goodcode.alucard.bpm.services

import org.springframework.stereotype.Service

@Service
class BpmService: IBpmService {
    override fun startInstance() {
        println("Starting BPM...")
    }
}