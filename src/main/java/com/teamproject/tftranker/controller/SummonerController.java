package com.teamproject.tftranker.controller;

import com.teamproject.tftranker.service.PuuIdToMatchId;
import com.teamproject.tftranker.service.MatchIdToResult;
import com.teamproject.tftranker.service.PuuIdToSummonerName;
import com.teamproject.tftranker.service.SummonerNameToPuuId;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequiredArgsConstructor
public class SummonerController {

  private final SummonerNameToPuuId summonerNameToPuuId;
  private final PuuIdToMatchId puuIdToMatchId;
  private final MatchIdToResult matchIdToResult;
  private final PuuIdToSummonerName puuIdToSummonerName;

  @GetMapping("/api/match/{summonerName}")
  public String matchResult(@PathVariable String summonerName) {

    /** 소환사 이름으로 puuId 가져오는 부분* */
    JSONObject jsonObject = new JSONObject(summonerNameToPuuId.summonerNameToPuuId(summonerName));
    String puuId = jsonObject.getString("puuid");

    /** puuId로 해당 소환사의 최근 matchId 가져오는 부분 * */
    JSONArray jsonArray = new JSONArray(puuIdToMatchId.puuIdToMatchId(puuId));

    ArrayList<String> resultList = new ArrayList<>();
    JSONObject resultJson = new JSONObject();

    for (int i = 0; i < 7; i++) {
      String matchId = jsonArray.getString(i);
      /** matchId로 해당 match 정보 가져오는 부분 * */
      JSONObject matchResult = new JSONObject(matchIdToResult.matchIdToResult(matchId));
      JSONObject info = matchResult.getJSONObject("info");
      JSONArray participantsArray = info.getJSONArray("participants");

      /** puuId를 summonerName으로 바꾸는 부분* */
      for (int j = 0; j < 8; j++) {
        JSONObject participants = participantsArray.getJSONObject(j);
        String temp = participants.getString("puuid");
        JSONObject nameJson = new JSONObject(puuIdToSummonerName.puuIdToSummonerName(temp));
        String name = nameJson.getString("name");

        participants.put("puuid", name);
      }
      resultList.add(i, (i+1) + " : " + info.toString());
    }

    return resultList.toString();
  }
}
