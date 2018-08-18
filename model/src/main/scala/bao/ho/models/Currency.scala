package bao.ho.models

import play.api.libs.json.Json

case class Currency(id: Int,
                    currency: String,
                    abbreviation: String,
                    currencyType: String)

object Currency {
  implicit val currencyJsonFormat = Json.format[Currency]
  implicit val currencyJsonWrite = Json.writes[Currency]
}