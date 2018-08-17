package models

import play.api.libs.json.Json

case class CurrencyInfo(timestamp: Long,
                        currencyId: Int,
                        marketCapByAvailableSupply: Long,
                        priceUsd: Double,
                        volume: Long)


object CurrencyInfo {
  implicit val currencyInfoJsonFormat = Json.format[CurrencyInfo]
  implicit val currencyInfoJsonWrite = Json.writes[CurrencyInfo]

}