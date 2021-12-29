package com.teamproject.tftranker.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SummonerDTO {

  // Encrypted account ID. Max length 56 characters.
  private String accountId;
  // ID of the summoner icon associated with the summoner.
  private int profileIconId;
  // Date summoner was last modified specified as epoch milliseconds. The following events will
  // update this timestamp: summoner name change, summoner level change, or profile icon change.
  private long revisionDate;
  // Summoner name
  private String name;
  //Encrypted summoner ID. Max length 63 characters.
  private String id;
  //Encrypted PUUID. Exact length of 78 characters.
  private String puuid;
  //Summoner level associated with the summoner.
  private long summonerLevel;
}
