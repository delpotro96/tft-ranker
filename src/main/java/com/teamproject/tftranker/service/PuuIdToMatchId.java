package com.teamproject.tftranker.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class PuuIdToMatchId {
  @Value("${riot.key}")
  private String key;

  public String puuIdToMatchId(String puuId) {

    /** 우선 asia 만* */
    String requestURL =
        "https://asia.api.riotgames.com/tft/match/v1/matches/by-puuid/"
            + puuId
            + "/ids?count=7&"
            + "api_key="
            + key;
    BufferedReader br;
    {
      try {
        URL url = new URL(requestURL);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "UTF-8"));
        String line;
        String result = "";
        while ((line = br.readLine()) != null) {
          result += line;
        }
        return result;
      } catch (IOException ignored) {

      }
    }
    /** Todo: 예외처리 해야함 */
    return null;
  }
}
