package bao.ho.models

import com.fasterxml.jackson.annotation.{JsonCreator, JsonProperty}
import play.api.libs.json.Json

case class CurrencyInfo @JsonCreator()(@JsonProperty("timestamp") timestamp: Long,
                                       @JsonProperty("currency_id") currencyId: Int,
                                       @JsonProperty("market_cap_by_available_supply") marketCapByAvailableSupply: Long,
                                       @JsonProperty("price_usd") priceUsd: Double,
                                       @JsonProperty("volume") volume: Long)

object CurrencyInfo {
  implicit val currencyInfoJsonFormat = Json.format[CurrencyInfo]
  implicit val currencyInfoJsonWrite = Json.writes[CurrencyInfo]

}