package org.jp.reima.dl_dota_replay.domain.dota.replay.controller;

import org.jp.reima.dl_dota_replay.domain.dota.replay.DownloadDotaReplayApi;
import org.jp.reima.dl_dota_replay.domain.dota.replay.service.DownloadDotaReplayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DownloadDotaReplayController implements DownloadDotaReplayApi {

    @Autowired
    DownloadDotaReplayService service;

    @Override
    public ResponseEntity<String> getReplayDownloadUrl(String matchId) {
       return ResponseEntity.ok(service.createUrl(matchId));
    }

}
