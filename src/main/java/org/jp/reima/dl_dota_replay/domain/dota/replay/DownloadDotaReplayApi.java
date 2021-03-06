package org.jp.reima.dl_dota_replay.domain.dota.replay;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/dota")
public interface DownloadDotaReplayApi {
    
    @GetMapping("replays/{matchId}/url")
    public ResponseEntity<String> getReplayDownloadUrl(@PathVariable("matchId") String matchId);

}
